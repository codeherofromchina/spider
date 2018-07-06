package novel.spider.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class NovelCatalog {
    private String name;
    private String showName;
    private String contentUrl;
    private int wordCount;
    private String sourceId;
    private boolean vip;
    private Date pubTime;
    private boolean mark;// 是否已经爬取 1：已经爬取  0：未爬取
    private Date spiderDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public Date getSpiderDate() {
        return spiderDate;
    }

    public void setSpiderDate(Date spiderDate) {
        this.spiderDate = spiderDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }
}
