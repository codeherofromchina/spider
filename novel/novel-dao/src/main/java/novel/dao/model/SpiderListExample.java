package novel.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpiderListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpiderListExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andListNameIsNull() {
            addCriterion("list_name is null");
            return (Criteria) this;
        }

        public Criteria andListNameIsNotNull() {
            addCriterion("list_name is not null");
            return (Criteria) this;
        }

        public Criteria andListNameEqualTo(String value) {
            addCriterion("list_name =", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotEqualTo(String value) {
            addCriterion("list_name <>", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThan(String value) {
            addCriterion("list_name >", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThanOrEqualTo(String value) {
            addCriterion("list_name >=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThan(String value) {
            addCriterion("list_name <", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThanOrEqualTo(String value) {
            addCriterion("list_name <=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLike(String value) {
            addCriterion("list_name like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotLike(String value) {
            addCriterion("list_name not like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameIn(List<String> values) {
            addCriterion("list_name in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotIn(List<String> values) {
            addCriterion("list_name not in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameBetween(String value1, String value2) {
            addCriterion("list_name between", value1, value2, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotBetween(String value1, String value2) {
            addCriterion("list_name not between", value1, value2, "listName");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNull() {
            addCriterion("thread_num is null");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNotNull() {
            addCriterion("thread_num is not null");
            return (Criteria) this;
        }

        public Criteria andThreadNumEqualTo(Integer value) {
            addCriterion("thread_num =", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotEqualTo(Integer value) {
            addCriterion("thread_num <>", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThan(Integer value) {
            addCriterion("thread_num >", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("thread_num >=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThan(Integer value) {
            addCriterion("thread_num <", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThanOrEqualTo(Integer value) {
            addCriterion("thread_num <=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumIn(List<Integer> values) {
            addCriterion("thread_num in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotIn(List<Integer> values) {
            addCriterion("thread_num not in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumBetween(Integer value1, Integer value2) {
            addCriterion("thread_num between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("thread_num not between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andStartPageIsNull() {
            addCriterion("start_page is null");
            return (Criteria) this;
        }

        public Criteria andStartPageIsNotNull() {
            addCriterion("start_page is not null");
            return (Criteria) this;
        }

        public Criteria andStartPageEqualTo(Integer value) {
            addCriterion("start_page =", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageNotEqualTo(Integer value) {
            addCriterion("start_page <>", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageGreaterThan(Integer value) {
            addCriterion("start_page >", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_page >=", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageLessThan(Integer value) {
            addCriterion("start_page <", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageLessThanOrEqualTo(Integer value) {
            addCriterion("start_page <=", value, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageIn(List<Integer> values) {
            addCriterion("start_page in", values, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageNotIn(List<Integer> values) {
            addCriterion("start_page not in", values, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageBetween(Integer value1, Integer value2) {
            addCriterion("start_page between", value1, value2, "startPage");
            return (Criteria) this;
        }

        public Criteria andStartPageNotBetween(Integer value1, Integer value2) {
            addCriterion("start_page not between", value1, value2, "startPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIsNull() {
            addCriterion("current_page is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIsNotNull() {
            addCriterion("current_page is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPageEqualTo(Integer value) {
            addCriterion("current_page =", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotEqualTo(Integer value) {
            addCriterion("current_page <>", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageGreaterThan(Integer value) {
            addCriterion("current_page >", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_page >=", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageLessThan(Integer value) {
            addCriterion("current_page <", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageLessThanOrEqualTo(Integer value) {
            addCriterion("current_page <=", value, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageIn(List<Integer> values) {
            addCriterion("current_page in", values, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotIn(List<Integer> values) {
            addCriterion("current_page not in", values, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageBetween(Integer value1, Integer value2) {
            addCriterion("current_page between", value1, value2, "currentPage");
            return (Criteria) this;
        }

        public Criteria andCurrentPageNotBetween(Integer value1, Integer value2) {
            addCriterion("current_page not between", value1, value2, "currentPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNull() {
            addCriterion("total_page is null");
            return (Criteria) this;
        }

        public Criteria andTotalPageIsNotNull() {
            addCriterion("total_page is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPageEqualTo(Integer value) {
            addCriterion("total_page =", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotEqualTo(Integer value) {
            addCriterion("total_page <>", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThan(Integer value) {
            addCriterion("total_page >", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_page >=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThan(Integer value) {
            addCriterion("total_page <", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageLessThanOrEqualTo(Integer value) {
            addCriterion("total_page <=", value, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageIn(List<Integer> values) {
            addCriterion("total_page in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotIn(List<Integer> values) {
            addCriterion("total_page not in", values, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageBetween(Integer value1, Integer value2) {
            addCriterion("total_page between", value1, value2, "totalPage");
            return (Criteria) this;
        }

        public Criteria andTotalPageNotBetween(Integer value1, Integer value2) {
            addCriterion("total_page not between", value1, value2, "totalPage");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetIsNull() {
            addCriterion("default_charset is null");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetIsNotNull() {
            addCriterion("default_charset is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetEqualTo(String value) {
            addCriterion("default_charset =", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetNotEqualTo(String value) {
            addCriterion("default_charset <>", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetGreaterThan(String value) {
            addCriterion("default_charset >", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetGreaterThanOrEqualTo(String value) {
            addCriterion("default_charset >=", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetLessThan(String value) {
            addCriterion("default_charset <", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetLessThanOrEqualTo(String value) {
            addCriterion("default_charset <=", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetLike(String value) {
            addCriterion("default_charset like", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetNotLike(String value) {
            addCriterion("default_charset not like", value, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetIn(List<String> values) {
            addCriterion("default_charset in", values, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetNotIn(List<String> values) {
            addCriterion("default_charset not in", values, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetBetween(String value1, String value2) {
            addCriterion("default_charset between", value1, value2, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andDefaultCharsetNotBetween(String value1, String value2) {
            addCriterion("default_charset not between", value1, value2, "defaultCharset");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternIsNull() {
            addCriterion("list_url_pattern is null");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternIsNotNull() {
            addCriterion("list_url_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternEqualTo(String value) {
            addCriterion("list_url_pattern =", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternNotEqualTo(String value) {
            addCriterion("list_url_pattern <>", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternGreaterThan(String value) {
            addCriterion("list_url_pattern >", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternGreaterThanOrEqualTo(String value) {
            addCriterion("list_url_pattern >=", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternLessThan(String value) {
            addCriterion("list_url_pattern <", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternLessThanOrEqualTo(String value) {
            addCriterion("list_url_pattern <=", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternLike(String value) {
            addCriterion("list_url_pattern like", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternNotLike(String value) {
            addCriterion("list_url_pattern not like", value, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternIn(List<String> values) {
            addCriterion("list_url_pattern in", values, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternNotIn(List<String> values) {
            addCriterion("list_url_pattern not in", values, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternBetween(String value1, String value2) {
            addCriterion("list_url_pattern between", value1, value2, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andListUrlPatternNotBetween(String value1, String value2) {
            addCriterion("list_url_pattern not between", value1, value2, "listUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternIsNull() {
            addCriterion("catalog_url_pattern is null");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternIsNotNull() {
            addCriterion("catalog_url_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternEqualTo(String value) {
            addCriterion("catalog_url_pattern =", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternNotEqualTo(String value) {
            addCriterion("catalog_url_pattern <>", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternGreaterThan(String value) {
            addCriterion("catalog_url_pattern >", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternGreaterThanOrEqualTo(String value) {
            addCriterion("catalog_url_pattern >=", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternLessThan(String value) {
            addCriterion("catalog_url_pattern <", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternLessThanOrEqualTo(String value) {
            addCriterion("catalog_url_pattern <=", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternLike(String value) {
            addCriterion("catalog_url_pattern like", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternNotLike(String value) {
            addCriterion("catalog_url_pattern not like", value, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternIn(List<String> values) {
            addCriterion("catalog_url_pattern in", values, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternNotIn(List<String> values) {
            addCriterion("catalog_url_pattern not in", values, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternBetween(String value1, String value2) {
            addCriterion("catalog_url_pattern between", value1, value2, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andCatalogUrlPatternNotBetween(String value1, String value2) {
            addCriterion("catalog_url_pattern not between", value1, value2, "catalogUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternIsNull() {
            addCriterion("content_url_pattern is null");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternIsNotNull() {
            addCriterion("content_url_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternEqualTo(String value) {
            addCriterion("content_url_pattern =", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternNotEqualTo(String value) {
            addCriterion("content_url_pattern <>", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternGreaterThan(String value) {
            addCriterion("content_url_pattern >", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternGreaterThanOrEqualTo(String value) {
            addCriterion("content_url_pattern >=", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternLessThan(String value) {
            addCriterion("content_url_pattern <", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternLessThanOrEqualTo(String value) {
            addCriterion("content_url_pattern <=", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternLike(String value) {
            addCriterion("content_url_pattern like", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternNotLike(String value) {
            addCriterion("content_url_pattern not like", value, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternIn(List<String> values) {
            addCriterion("content_url_pattern in", values, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternNotIn(List<String> values) {
            addCriterion("content_url_pattern not in", values, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternBetween(String value1, String value2) {
            addCriterion("content_url_pattern between", value1, value2, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andContentUrlPatternNotBetween(String value1, String value2) {
            addCriterion("content_url_pattern not between", value1, value2, "contentUrlPattern");
            return (Criteria) this;
        }

        public Criteria andRunStatusIsNull() {
            addCriterion("run_status is null");
            return (Criteria) this;
        }

        public Criteria andRunStatusIsNotNull() {
            addCriterion("run_status is not null");
            return (Criteria) this;
        }

        public Criteria andRunStatusEqualTo(Boolean value) {
            addCriterion("run_status =", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotEqualTo(Boolean value) {
            addCriterion("run_status <>", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThan(Boolean value) {
            addCriterion("run_status >", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("run_status >=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThan(Boolean value) {
            addCriterion("run_status <", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("run_status <=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusIn(List<Boolean> values) {
            addCriterion("run_status in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotIn(List<Boolean> values) {
            addCriterion("run_status not in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("run_status between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("run_status not between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdIsNull() {
            addCriterion("spider_web_id is null");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdIsNotNull() {
            addCriterion("spider_web_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdEqualTo(Integer value) {
            addCriterion("spider_web_id =", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdNotEqualTo(Integer value) {
            addCriterion("spider_web_id <>", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdGreaterThan(Integer value) {
            addCriterion("spider_web_id >", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spider_web_id >=", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdLessThan(Integer value) {
            addCriterion("spider_web_id <", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdLessThanOrEqualTo(Integer value) {
            addCriterion("spider_web_id <=", value, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdIn(List<Integer> values) {
            addCriterion("spider_web_id in", values, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdNotIn(List<Integer> values) {
            addCriterion("spider_web_id not in", values, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdBetween(Integer value1, Integer value2) {
            addCriterion("spider_web_id between", value1, value2, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spider_web_id not between", value1, value2, "spiderWebId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdIsNull() {
            addCriterion("spider_parser_id is null");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdIsNotNull() {
            addCriterion("spider_parser_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdEqualTo(Integer value) {
            addCriterion("spider_parser_id =", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdNotEqualTo(Integer value) {
            addCriterion("spider_parser_id <>", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdGreaterThan(Integer value) {
            addCriterion("spider_parser_id >", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spider_parser_id >=", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdLessThan(Integer value) {
            addCriterion("spider_parser_id <", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdLessThanOrEqualTo(Integer value) {
            addCriterion("spider_parser_id <=", value, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdIn(List<Integer> values) {
            addCriterion("spider_parser_id in", values, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdNotIn(List<Integer> values) {
            addCriterion("spider_parser_id not in", values, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdBetween(Integer value1, Integer value2) {
            addCriterion("spider_parser_id between", value1, value2, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderParserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spider_parser_id not between", value1, value2, "spiderParserId");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameIsNull() {
            addCriterion("spider_web_name is null");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameIsNotNull() {
            addCriterion("spider_web_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameEqualTo(String value) {
            addCriterion("spider_web_name =", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameNotEqualTo(String value) {
            addCriterion("spider_web_name <>", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameGreaterThan(String value) {
            addCriterion("spider_web_name >", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameGreaterThanOrEqualTo(String value) {
            addCriterion("spider_web_name >=", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameLessThan(String value) {
            addCriterion("spider_web_name <", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameLessThanOrEqualTo(String value) {
            addCriterion("spider_web_name <=", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameLike(String value) {
            addCriterion("spider_web_name like", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameNotLike(String value) {
            addCriterion("spider_web_name not like", value, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameIn(List<String> values) {
            addCriterion("spider_web_name in", values, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameNotIn(List<String> values) {
            addCriterion("spider_web_name not in", values, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameBetween(String value1, String value2) {
            addCriterion("spider_web_name between", value1, value2, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderWebNameNotBetween(String value1, String value2) {
            addCriterion("spider_web_name not between", value1, value2, "spiderWebName");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeIsNull() {
            addCriterion("spider_time is null");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeIsNotNull() {
            addCriterion("spider_time is not null");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeEqualTo(Date value) {
            addCriterion("spider_time =", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeNotEqualTo(Date value) {
            addCriterion("spider_time <>", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeGreaterThan(Date value) {
            addCriterion("spider_time >", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("spider_time >=", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeLessThan(Date value) {
            addCriterion("spider_time <", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeLessThanOrEqualTo(Date value) {
            addCriterion("spider_time <=", value, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeIn(List<Date> values) {
            addCriterion("spider_time in", values, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeNotIn(List<Date> values) {
            addCriterion("spider_time not in", values, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeBetween(Date value1, Date value2) {
            addCriterion("spider_time between", value1, value2, "spiderTime");
            return (Criteria) this;
        }

        public Criteria andSpiderTimeNotBetween(Date value1, Date value2) {
            addCriterion("spider_time not between", value1, value2, "spiderTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}