package novel.spider;

import novel.spider.domain.Novel;
import novel.spider.domain.NovelCatalog;
import novel.spider.domain.NovelList;
import novel.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class Spider {
    private PageConfig pageConfig;

    public Spider(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }


    public void spider() {
        this.spider(pageConfig);
    }

    /**
     * 抓取内容，从网站列表开始
     *
     * @param pageConfig
     */
    public void spider(PageConfig pageConfig) {
        // 是否是第一次解析
        boolean first = true;
        do {
            // 第一次需要获取页面信息并填充pageInfo的totalPage信息
            String url = first ? pageConfig.getCurrentPageUrl() : pageConfig.getNextPageUrl();
            // 获取解析器并解析内容
            Parser parser = pageConfig.getParser();
            // 解析列表信息
            NovelList novelList = spiderBookList(url, parser);
            if (first) {
                pageConfig.setTotalPages(novelList.getTotalPages());
                pageConfig.setPageSize(novelList.getPageSize());
                first = !first;
            }
            List<String> bookInfoUrls = novelList.getBookInfoUrls();
            if (bookInfoUrls == null || bookInfoUrls.size() == 0) {
                continue;
            }
            // 获取采集线程数并通过多个线程采集
            // TODO 这里先走正常流程不用多线程
            int threadNum = pageConfig.getThreadNum();
            for (String bookInfoUrl : bookInfoUrls) {
                StringBuffer bookInfoResponse = new StringBuffer(); // 先声明，下面代码可能会用到，避免重复请求
                Novel novel = spiderBookInfo(bookInfoUrl, parser, bookInfoResponse);
                //TODO  需要判断数据库中是否采集过，什么时候采集，先略过
                List<NovelCatalog> novelCatalogs = null;
                String catalogUrl = pageConfig.getCatalogUrl(novel.getSourcesId());
                if (catalogUrl == null) {
                    novelCatalogs = parser.parseBookCatalog(bookInfoResponse.toString());
                } else {
                    bookInfoResponse = null;// 图书信息内容已无用，清除
                    novelCatalogs = spiderNovelCatalog(catalogUrl, parser);
                }
                if (novelCatalogs == null || novelCatalogs.size() == 0) {
                    continue;
                }
                for (NovelCatalog catalog : novelCatalogs) {
                    // 获取并解析小说内容
                    String contentUrl = pageConfig.getContentUrl(catalog.getSourceId());
                    if (StringUtils.isBlank(contentUrl)) {
                        contentUrl = catalog.getContentUrl();
                    }
                    String content = spiderBookContent(contentUrl, parser);
                }
            }
        } while (!pageConfig.isLastPage());
    }


    /**
     * 爬取图书说明等详情
     *
     * @param sourcesUrl
     * @param parser
     */
    public Novel spiderBookInfo(String sourcesUrl, Parser parser, StringBuffer sBuffer) {
        String response = HttpUtils.get(sourcesUrl);
        sBuffer.append(response);
        Novel novel = parser.parseBookInfo(response);
        return novel;
    }


    /**
     * 爬取图书列表内容
     *
     * @param sourcesUrl
     * @param parser
     */
    public NovelList spiderBookList(String sourcesUrl, Parser parser) {
        String response = HttpUtils.get(sourcesUrl);
        // 解析列表信息
        NovelList novelList = parser.parseList(response);
        return novelList;
    }

    /**
     * 解析小说目录
     *
     * @param catalogUrl
     * @param parser
     * @return
     */
    public List<NovelCatalog> spiderNovelCatalog(String catalogUrl, Parser parser) {
        String response = HttpUtils.get(catalogUrl);
        List<NovelCatalog> catalogs = parser.parseBookCatalog(response);
        return catalogs;
    }

    public String spiderBookContent(String sourcesUrl, Parser parser) {
        String response = HttpUtils.get(sourcesUrl);
        // 解析信息
        String content = parser.parseBookContent(response);
        return content;
    }
}
