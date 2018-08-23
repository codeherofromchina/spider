package novel.dao.model;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Date;

public class SpiderPage {
    private Integer id;

    private String pageName;

    private Integer runStatus;

    private String defaultCharset;

    private String startUrl;

    private String catalogUrlPattern;

    private String contentUrlPattern;

    private Integer spiderWebId;

    private Integer spiderParserId;

    private String spiderWebName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }


    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
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

    public Integer getSpiderWebId() {
        return spiderWebId;
    }

    public void setSpiderWebId(Integer spiderWebId) {
        this.spiderWebId = spiderWebId;
    }

    public Integer getSpiderParserId() {
        return spiderParserId;
    }

    public void setSpiderParserId(Integer spiderParserId) {
        this.spiderParserId = spiderParserId;
    }

    public String getSpiderWebName() {
        return spiderWebName;
    }

    public void setSpiderWebName(String spiderWebName) {
        this.spiderWebName = spiderWebName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 单页面爬虫运行状态枚举类
     */
    public enum RunStatusEnum {
        WAIT(0, "待运行"), RUNING(1, "运行中"), DONE(2, "采集完成");

        private int state;
        private String msg;

        private RunStatusEnum(int state, String msg) {
            this.state = state;
            this.msg = msg;
        }

        public int getState() {
            return state;
        }

        public String getMsg() {
            return msg;
        }
    }
}