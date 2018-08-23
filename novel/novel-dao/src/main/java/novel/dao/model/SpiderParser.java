package novel.dao.model;

import java.util.Date;

public class SpiderParser {
    private Integer id;

    private Date createTime;

    private String parserName;

    private String parserDesc;

    private String parserContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParserName() {
        return parserName;
    }

    public void setParserName(String parserName) {
        this.parserName = parserName;
    }

    public String getParserDesc() {
        return parserDesc;
    }

    public void setParserDesc(String parserDesc) {
        this.parserDesc = parserDesc;
    }

    public String getParserContent() {
        return parserContent;
    }

    public void setParserContent(String parserContent) {
        this.parserContent = parserContent;
    }
}