package novel.spider.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class NovelCatalog {
    private String name;
    private String showName;
    private String volumeName; // 卷宗名称
    private Integer volumeNum; // 卷宗数
    private int wordCount;
    private String sourceId; // 章节在网站中的唯一标识
    private String sourceUrl; // 章节在网站中的地址
    private boolean vip;
    private Date pubTime;
    private String bookUuid; // 所在图书的唯一标识,解析时不用设置
    private String uuid; // 内容识别目录的唯一标识
    private Integer catalogNum;


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

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }


    public Integer getVolumeNum() {
        return volumeNum;
    }

    public void setVolumeNum(Integer volumeNum) {
        this.volumeNum = volumeNum;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
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

    public String getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(String bookUuid) {
        this.bookUuid = bookUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(Integer catalogNum) {
        this.catalogNum = catalogNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

}
