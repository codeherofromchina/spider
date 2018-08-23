package novel.spider;

import novel.comm.util.HttpUtils;
import novel.dao.model.SpiderList;
import novel.dao.model.SpiderPage;
import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.util.SpiderUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
     * @param spiderList
     * @param engine
     */
    public void spider(SpiderList spiderList, final ScriptEngine engine) {
        int threadNum = spiderList.getThreadNum();
        int availProcessors = Runtime.getRuntime().availableProcessors();
        // 设置线程不能小于1，但是也不能大于当前电脑cpu核数
        if (threadNum < 1) {
            threadNum = 1;
        } else if (threadNum > availProcessors) {
            threadNum = availProcessors;
        }
        LOGGER.info("列表爬虫线程数量[{}]", threadNum);
        // 信号量控制线程数
        Semaphore semaphore = new Semaphore(threadNum, true);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        Integer totalPage = spiderList.getTotalPage();
        boolean continueFlag = true;
        for (int currentPage = spiderList.getCurrentPage(); currentPage <= totalPage && continueFlag;) {
            LOGGER.info("列表爬虫爬取[{}]页开始", currentPage);
            String url = SpiderUtils.realTargetUrl(spiderList.getListUrlPattern(), String.valueOf(currentPage));
            // 解析列表信息
            List<String> bookUrlList = spiderBookList(url, engine, SpiderUtils.charsetForName(spiderList.getDefaultCharset()));
            // 获取采集线程数并通过多个线程采集
            for (String bookUrl : bookUrlList) {
                try {
                    semaphore.acquire();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                SpiderPage spiderPage = new SpiderPage();
                                spiderPage.setDefaultCharset(spiderList.getDefaultCharset());
                                spiderPage.setStartUrl(bookUrl);
                                spiderPage.setCatalogUrlPattern(spiderList.getCatalogUrlPattern());
                                spiderPage.setContentUrlPattern(spiderList.getContentUrlPattern());
                                spiderPage.setSpiderWebId(spiderList.getSpiderWebId());
                                spiderSimpleBook(spiderPage, engine);
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
            LOGGER.info("列表爬虫爬取[{}]页结束", currentPage);
            // 更新spiderList页码并获取是否继续
            currentPage++;
            continueFlag = serializationService.spiderListCurrentPageDone(spiderList.getId(), currentPage);
        }
    }


    /**
     * 根据给定信息采集单本图书
     *
     * @param spiderPage
     * @param engine
     */
    public boolean spiderSimpleBook(SpiderPage spiderPage, ScriptEngine engine) {
        long bookStartTime = System.currentTimeMillis();
        StringBuilder bookInfoResponse = new StringBuilder(); // 先声明，下面代码可能会用到，避免重复请求
        Novel novel = spiderBookInfo(spiderPage, engine, bookInfoResponse);
        if (novel == null) {
            return false;
        }
        novel.setSpiderWebId(spiderPage.getSpiderWebId());
        LOGGER.info("图书[{}]开始", novel.getName());
        // 这里存储图书和分类信息
        boolean cFlag = serializationService.novelSerialize(novel);
        if (!cFlag) {
            LOGGER.info("图书[{}]结束:[{}ms]:序列化false", novel.getName(), (System.currentTimeMillis() - bookStartTime));
            return false;
        }
        String catalogUrl = SpiderUtils.realTargetUrl(spiderPage.getCatalogUrlPattern(), novel.getSourceId());
        List<NovelCatalog> novelCatalogs = spiderNovelCatalog(catalogUrl, engine, SpiderUtils.charsetForName(spiderPage.getDefaultCharset()), bookInfoResponse);

        if (novelCatalogs == null || novelCatalogs.size() == 0) {
            LOGGER.info("图书[{}]结束:[{}ms]:目录采集false", novel.getName(), (System.currentTimeMillis() - bookStartTime));
            return false;
        }
        for (NovelCatalog catalog : novelCatalogs) {
            long catalogStartTime = System.currentTimeMillis();
            LOGGER.info("图书[{}]->卷[{}]->目录[{}]开始", novel.getName(), catalog.getVolumeName(), catalog.getName());
            // 序列化小说目录内容
            catalog.setBookUuid(novel.getUuid());
            String sourcesUrl = SpiderUtils.realTargetUrl(spiderPage.getContentUrlPattern(), novel.getSourceId(), catalog.getSourceId());
            if (sourcesUrl != null) {
                catalog.setSourceUrl(SpiderUtils.realTargetUrl(spiderPage.getContentUrlPattern(), catalog.getSourceId()));
            }
            boolean dFlag = serializationService.catalogSerialize(catalog);
            if (!dFlag) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]:序列化false", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStartTime));
                continue;
            }
            // 获取并解析小说内容
            if (catalog.isVip()) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]:内容需VIP,未采集", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStartTime));
                continue;
            }
            String content = spiderBookContent(spiderPage, catalog.getSourceUrl(), engine);
            if (StringUtils.isBlank(content)) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]:内容采集失败", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStartTime));
                continue;
            }
            boolean eFlag = serializationService.contentSerialize(content, catalog.getUuid());
            if (!eFlag) {
                LOGGER.info("图书[{}]->卷[{}]->目录[{}]:序列化内容失败", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStartTime));
            }
            LOGGER.info("图书[{}]->卷[{}]->目录[{}]结束:[{}ms]", novel.getName(), catalog.getVolumeName(), catalog.getName(), (System.currentTimeMillis() - catalogStartTime));
        }
        LOGGER.info("图书[{}]结束:[{}ms]", novel.getName(), (System.currentTimeMillis() - bookStartTime));
        return true;
    }


    /**
     * 爬取图书列表内容
     *
     * @param sourcesUrl
     * @param engine     页面解析引擎
     */
    public List<String> spiderBookList(String sourcesUrl, ScriptEngine engine, Charset charset) {
        try {
            String response = HttpUtils.get(sourcesUrl, charset);
            // 解析列表URL信息
            List<String> bookUrlList = (List<String>) ((Invocable) engine).invokeFunction("parseList", response);
            return bookUrlList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 爬取图书说明等详情
     *
     * @param spiderPage
     * @param engine
     */
    private Novel spiderBookInfo(SpiderPage spiderPage, ScriptEngine engine, StringBuilder sBuffer) {
        try {
            String response = HttpUtils.get(spiderPage.getStartUrl(), SpiderUtils.charsetForName(spiderPage.getDefaultCharset()));
            sBuffer.append(response);
            Novel novel = (Novel) ((Invocable) engine).invokeFunction("parseBookInfo", response);
            return novel;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 解析小说目录
     *
     * @param catalogUrl
     * @param engine
     * @param bookInfoResponse
     * @return
     */
    public List<NovelCatalog> spiderNovelCatalog(String catalogUrl, ScriptEngine engine, Charset charset, StringBuilder bookInfoResponse) {
        List<NovelCatalog> catalogs = null;
        try {
            if (bookInfoResponse != null && bookInfoResponse.length() > 0) {
                catalogs = (List<NovelCatalog>) ((Invocable) engine).invokeFunction("parseBookCatalog", bookInfoResponse.toString());
                if (catalogs != null && catalogs.size() > 0) {
                    return catalogs;
                }
            }
            bookInfoResponse = null; // 释放对象
            if (StringUtils.isNotBlank(catalogUrl)) {
                String response = HttpUtils.get(catalogUrl, charset);
                catalogs = (List<NovelCatalog>) ((Invocable) engine).invokeFunction("parseBookCatalog2", response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return catalogs;
    }


    /**
     * 解析小说内容
     *
     * @param spiderPage
     * @param contentUrl
     * @param engine
     * @return
     */
    private String spiderBookContent(SpiderPage spiderPage, String contentUrl, ScriptEngine engine) {
        try {
            if (StringUtils.isBlank(contentUrl)) {
                return null;
            }
            String response = HttpUtils.get(contentUrl, SpiderUtils.charsetForName(spiderPage.getDefaultCharset()));
            // 解析信息
            String content = (String) ((Invocable) engine).invokeFunction("parseBookContent", response);
            return content;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 采集单页面结束将状态序列化
     *
     * @param flag
     * @param spiderPageId
     */
    public void spiderSimpleBookDone(boolean flag, Integer spiderPageId) {
        serializationService.spiderSimpleBookDone(flag, spiderPageId);
    }

    /**
     * 采集列表页面结束将状态序列化
     *
     * @param spiderListId
     */
    public void spiderListBookDone(Integer spiderListId) {
        serializationService.spiderListBookDone(spiderListId);
    }

    public void stopSinglePageSpider() {
        serializationService.stopSinglePageSpider();
    }

    public void stopListPageSpider() {
        serializationService.stopListPageSpider();
    }
}
