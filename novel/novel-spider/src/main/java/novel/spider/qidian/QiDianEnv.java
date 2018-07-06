package novel.spider.qidian;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public final class QiDianEnv {
    // 列表页URL格式
    protected static final String URL_LIST_PATTERN = "https://www.qidian.com/free/all?page=%d&pageSize=%d";
    // 目录页URL格式
    protected static final String URL_CATALOG_PATTERN = "https://book.qidian.com/ajax/book/category?bookId=%s";
    // 内容页URL格式
    protected static final String URL_CONTENT_PATTERN = "https://read.qidian.com/chapter/%s";
    // 起始页
    protected static final int START_PATE = 1;
}
