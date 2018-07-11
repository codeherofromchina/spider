package novel.spider.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 小说列表模型
 * Created by wangxiaodan on 2018/7/5.
 */
public class NovelList {
    private int totalPages;
    private List<String> bookInfoUrls;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<String> getBookInfoUrls() {
        return bookInfoUrls;
    }

    public void setBookInfoUrls(List<String> bookInfoUrls) {
        this.bookInfoUrls = bookInfoUrls;
    }

    public int getPageSize() {
        return bookInfoUrls == null ? 0 : bookInfoUrls.size();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

}
