package novel.dao.model;

import java.util.ArrayList;
import java.util.List;

public class SpiderTypesMapping {
    private Integer id;

    private Integer spiderWebId;

    private String originalTypes;

    private Integer bookTypesId;

    private String bookTypesName;

    private Integer parentId;

    private Integer seq;

    private String state = "open";   //closed:表示有子节点   open:表示没有子节点

    private List<SpiderTypesMapping> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpiderWebId() {
        return spiderWebId;
    }

    public void setSpiderWebId(Integer spiderWebId) {
        this.spiderWebId = spiderWebId;
    }

    public String getOriginalTypes() {
        return originalTypes;
    }

    public void setOriginalTypes(String originalTypes) {
        this.originalTypes = originalTypes;
    }

    public Integer getBookTypesId() {
        return bookTypesId;
    }

    public void setBookTypesId(Integer bookTypesId) {
        this.bookTypesId = bookTypesId;
    }

    public String getBookTypesName() {
        return bookTypesName;
    }

    public void setBookTypesName(String bookTypesName) {
        this.bookTypesName = bookTypesName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SpiderTypesMapping> getChildren() {
        return children;
    }

    public void setChildren(List<SpiderTypesMapping> children) {
        this.children = children;
    }

    public void addChildren(SpiderTypesMapping spiderTypesMapping) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(spiderTypesMapping);
    }
}