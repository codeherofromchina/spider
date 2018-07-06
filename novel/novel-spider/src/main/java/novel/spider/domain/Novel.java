package novel.spider.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class Novel {
    private String name;// 图书名称
    private String alias;
    private String author; // 图书作者
    private String coverPhoto; // 图书封面图片
    private float score;
    private String intro; // 简短介绍
    private String desc;//图书介绍
    private String parentTypes; // 父级分类
    private String sonTypes; // 子分类
    // 图书进度状态true：连载  false：完本
    private boolean stat;
    private int recommend; // 图书总推荐
    private int recommendWeek; // 图书周推荐
    private String sources;
    private String sourcesId;
    private int wordCount; // '图书总字数'
    private Date spiderDate; // '最新采集时间'


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParentTypes() {
        return parentTypes;
    }

    public void setParentTypes(String parentTypes) {
        this.parentTypes = parentTypes;
    }

    public String getSonTypes() {
        return sonTypes;
    }

    public void setSonTypes(String sonTypes) {
        this.sonTypes = sonTypes;
    }

    public boolean isStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getRecommendWeek() {
        return recommendWeek;
    }

    public void setRecommendWeek(int recommendWeek) {
        this.recommendWeek = recommendWeek;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getSourcesId() {
        return sourcesId;
    }

    public void setSourcesId(String sourcesId) {
        this.sourcesId = sourcesId;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
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
