package novel.spider;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class PageConfig {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_TOTAL_PAGES = -1;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_THREAD_NUM = 10;

    public PageConfig() {
    }

    public PageConfig(int page) {
        this.page = page;
    }

    /**
     * 是否是最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return page == totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public Parser getParser() {
        assert parser != null;
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public String getCurrentPageUrl() {
        assert (listUrlPattern != null);
        return String.format(listUrlPattern, page, pageSize);
    }

    public void setDefaultCharset(Charset defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public Charset getDefaultCharset() {
        return defaultCharset;
    }

    /**
     * 获取下一页的请求页面
     * 副作用是将当前页码加1
     *
     * @return
     */
    public String getNextPageUrl() {
        assert (listUrlPattern != null);
        return String.format(listUrlPattern, ++page, pageSize);
    }

    public void setListUrlPattern(String listUrlPattern) {
        this.listUrlPattern = listUrlPattern;
    }

    public String getCatalogUrl(String... params) {
        if (StringUtils.isBlank(catalogUrlPattern)) {
            return null;
        }
        return String.format(catalogUrlPattern, params);
    }

    public void setCatalogUrlPattern(String catalogUrlPattern) {
        this.catalogUrlPattern = catalogUrlPattern;
    }

    public String getContentUrl(String... params) {
        if (StringUtils.isBlank(contentUrlPattern)) {
            return null;
        }
        return String.format(contentUrlPattern, params);
    }

    public void setContentUrlPattern(String contentUrlPattern) {
        this.contentUrlPattern = contentUrlPattern;
    }

    // 当前页码
    private int page = DEFAULT_PAGE;
    // 总页码
    private int totalPages = DEFAULT_TOTAL_PAGES;
    // 页大小
    private int pageSize = DEFAULT_PAGE_SIZE;
    // 列表页URL格式
    private String listUrlPattern;
    // 目录页URL格式
    private String catalogUrlPattern;
    // 内容页URL格式
    private String contentUrlPattern;
    // 网站默认字符集
    private Charset defaultCharset;
    // 内容解析器
    private Parser parser;
    // 爬取内容的线程数
    private int threadNum = DEFAULT_THREAD_NUM;
}
