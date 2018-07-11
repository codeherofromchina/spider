package novel.spider;

import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
import novel.spider.qidian.QiDianPageConfigBuilder;
import novel.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.provider.ConfigFile;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
@Component
public class Spider {
    private static final Logger LOGGER = LoggerFactory.getLogger(Spider.class);

    @Resource(name = "dbSerialization")
    private SerializationService serializationService;

    /**
     * 抓取内容，从网站列表开始
     *
     * @param pageConfig
     */
    public void spider(PageConfig pageConfig) {
        // 是否是第一次解析
        boolean first = true;
        int threadNum = pageConfig.getThreadNum();
        int availProcessors = Runtime.getRuntime().availableProcessors();
        // 设置线程不能小于1，但是也不能大于当前电脑cpu核数
        if (threadNum < 1) {
            threadNum = 1;
        }
//        else if (threadNum > availProcessors) {
//            threadNum = availProcessors;
//        }
        LOGGER.info("线程总数量[{}]", threadNum);
        // 信号量控制线程数
        Semaphore semaphore = new Semaphore(threadNum, true);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        do {
            LOGGER.info("爬取[{}]页，页大小[{}]", pageConfig.getPage(), pageConfig.getPageSize());
            // 第一次需要获取页面信息并填充pageInfo的totalPage信息
            String url = first ? pageConfig.getCurrentPageUrl() : pageConfig.getNextPageUrl();
            // 解析列表信息
            NovelList novelList = spiderBookList(url, pageConfig);
            if (first && pageConfig.getTotalPages() < 0) {
                pageConfig.setTotalPages(novelList.getTotalPages());
                pageConfig.setPageSize(novelList.getPageSize());
            }
            first = !first;

            List<String> bookInfoUrls = novelList.getBookInfoUrls();
            if (bookInfoUrls == null || bookInfoUrls.size() == 0) {
                continue;
            }
            // 获取采集线程数并通过多个线程采集
            for (String bookInfoUrl : bookInfoUrls) {
                try {
                    semaphore.acquire();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                spiderSimpleBook(bookInfoUrl, pageConfig);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                semaphore.release();
                            }
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } while (!pageConfig.isLastPage());
    }

    /**
     * 采集单本图书
     *
     * @param bookInfoUrl
     * @param pageConfig
     */
    public void spiderSimpleBook(String bookInfoUrl, PageConfig pageConfig) {
        long bookStart = System.currentTimeMillis();
        StringBuffer bookInfoResponse = new StringBuffer(); // 先声明，下面代码可能会用到，避免重复请求
        Novel novel = spiderBookInfo(bookInfoUrl, pageConfig, bookInfoResponse);
        if (novel == null) {
            return;
        }
        LOGGER.info("图书[{}]开始", novel.getName());
        // 这里存储图书和分类信息
        boolean cFlag = serializationService.novelSerialize(novel);
        if (!cFlag) {
            LOGGER.info("图书[{}]结束:[{}ms]:序列化false", novel.getName(), (System.currentTimeMillis() - bookStart));
            return;
        }

        List<NovelCatalog> novelCatalogs = null;
        String catalogUrl = pageConfig.getCatalogUrl(novel.getSourceId());
        if (StringUtils.isBlank(catalogUrl)) {
            novelCatalogs = pageConfig.getParser().parseBookCatalog(bookInfoResponse.toString());
        } else {
            bookInfoResponse = null;// 图书信息内容已无用，清除
            novelCatalogs = spiderNovelCatalog(catalogUrl, pageConfig);
        }
        if (novelCatalogs == null || novelCatalogs.size() == 0) {
            return;
        }
        for (NovelCatalog catalog : novelCatalogs) {
            long catalogStart = System.currentTimeMillis();
            LOGGER.info("图书[{}]->卷[{}]->目录[{}]开始", novel.getName(), catalog.getVolumeName(), catalog.getName());
            // 序列化小说目录内容
            catalog.setBookUuid(novel.getUuid());
            boolean dFlag = serializationService.catalogSerialize(catalog);
            if (!dFlag) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]:序列化false", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStart));
                continue;
            }
            // 获取并解析小说内容
            String contentUrl = pageConfig.getContentUrl(catalog.getSourceId());
            if (catalog.isVip() || StringUtils.isBlank(contentUrl)) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]:内容未采集", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStart));
                continue;
            }
            String content = spiderBookContent(contentUrl, pageConfig);
            if (StringUtils.isBlank(content)) {
                continue;
            }
            boolean eFlag = serializationService.contentSerialize(content, catalog.getUuid());
            if (!eFlag) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]:序列化内容失败", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStart));
            }
            LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStart));
        }
        LOGGER.info("图书[{}]结束:[{}ms]", novel.getName(), (System.currentTimeMillis() - bookStart));
    }

    /**
     * 爬取图书说明等详情
     *
     * @param sourcesUrl
     * @param pageConfig
     */
    private Novel spiderBookInfo(String sourcesUrl, PageConfig pageConfig, StringBuffer sBuffer) {
        try {
            String response = HttpUtils.get(sourcesUrl, pageConfig.getDefaultCharset());
            sBuffer.append(response);
            Novel novel = pageConfig.getParser().parseBookInfo(response);
            return novel;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 爬取图书列表内容
     *
     * @param sourcesUrl
     * @param pageConfig
     */
    private NovelList spiderBookList(String sourcesUrl, PageConfig pageConfig) {
        try {
            String response = HttpUtils.get(sourcesUrl, pageConfig.getDefaultCharset());
            // 解析列表信息
            NovelList novelList = pageConfig.getParser().parseList(response);
            return novelList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解析小说目录
     *
     * @param catalogUrl
     * @param pageConfig
     * @return
     */
    private List<NovelCatalog> spiderNovelCatalog(String catalogUrl, PageConfig pageConfig) {
        try {
            String response = HttpUtils.get(catalogUrl, pageConfig.getDefaultCharset());
            List<NovelCatalog> catalogs = pageConfig.getParser().parseBookCatalog(response);
            return catalogs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解析小说内容
     *
     * @param sourcesUrl
     * @param pageConfig
     * @return
     */
    private String spiderBookContent(String sourcesUrl, PageConfig pageConfig) {
        try {
            String response = HttpUtils.get(sourcesUrl, pageConfig.getDefaultCharset());
            // 解析信息
            String content = pageConfig.getParser().parseBookContent(response);
            return content;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
