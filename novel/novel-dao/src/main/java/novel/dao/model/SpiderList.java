package novel.dao.model;

import java.util.Date;

public class SpiderList {
    private Integer id;

    private String listName;

    private Integer threadNum;

    private Integer startPage;

    private Integer currentPage;

    private Integer totalPage;

    private String defaultCharset;

    private String listUrlPattern;

    private String catalogUrlPattern;

    private String contentUrlPattern;

    private Boolean runStatus;

    private Integer spiderWebId;

    private String spiderWebName;

    private Integer spiderParserId;

    private Date spiderTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getListUrlPattern() {
        return listUrlPattern;
    }

    public void setListUrlPattern(String listUrlPattern) {
        this.listUrlPattern = listUrlPattern;
    }

    public String getCatalogUrlPattern() {
        return catalogUrlPattern;
    }

    public void setCatalogUrlPattern(String catalogUrlPattern) {
        this.catalogUrlPattern = catalogUrlPattern;
    }

    public String getContentUrlPattern() {
        return contentUrlPattern;
    }

    public void setContentUrlPattern(String contentUrlPattern) {
        this.contentUrlPattern = contentUrlPattern;
    }

    public Boolean getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getSpiderWebId() {
        return spiderWebId;
    }

    public void setSpiderWebId(Integer spiderWebId) {
        this.spiderWebId = spiderWebId;
    }

    public String getSpiderWebName() {
        return spiderWebName;
    }

    public void setSpiderWebName(String spiderWebName) {
        this.spiderWebName = spiderWebName;
    }

    public Integer getSpiderParserId() {
        return spiderParserId;
    }

    public void setSpiderParserId(Integer spiderParserId) {
        this.spiderParserId = spiderParserId;
    }


    public void setSpiderTime(Date spiderTime) {
        this.spiderTime = spiderTime;
    }

    public Date getSpiderTime() {
        return spiderTime;
    }
}