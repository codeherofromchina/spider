package novel.spider.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxiaodan on 2018/7/5.
 */
public class Novel {
    private String name;// 图书名称
    private String alias;
    private String author; // 图书作者
    private String coverPhoto; // 图书封面图片
    private String intro; // 简短介绍
    private String desc;//图书介绍
    private String parentTypes; // 父级分类
    private String sonTypes; // 子分类
    // 图书进度状态true：连载  false：完本
    private boolean stat;
    private String sources;// 图书url
    private String sourceId; // 在原网站中图书的唯一标识
    private String uuid; // 自己生成，用于识别关系
    private int wordCount; // '图书总字数'
    private List<String> labelList;
    private Integer spiderWebId; // 网站

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

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }


    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public Integer getSpiderWebId() {
        return spiderWebId;
    }

    public void setSpiderWebId(Integer spiderWebId) {
        this.spiderWebId = spiderWebId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

}
