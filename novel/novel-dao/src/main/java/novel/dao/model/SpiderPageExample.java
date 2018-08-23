package novel.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpiderPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpiderPageExample() {
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

        public Criteria andPageNameIsNull() {
            addCriterion("page_name is null");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNotNull() {
            addCriterion("page_name is not null");
            return (Criteria) this;
        }

        public Criteria andPageNameEqualTo(String value) {
            addCriterion("page_name =", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotEqualTo(String value) {
            addCriterion("page_name <>", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThan(String value) {
            addCriterion("page_name >", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThanOrEqualTo(String value) {
            addCriterion("page_name >=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThan(String value) {
            addCriterion("page_name <", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThanOrEqualTo(String value) {
            addCriterion("page_name <=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLike(String value) {
            addCriterion("page_name like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotLike(String value) {
            addCriterion("page_name not like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameIn(List<String> values) {
            addCriterion("page_name in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotIn(List<String> values) {
            addCriterion("page_name not in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameBetween(String value1, String value2) {
            addCriterion("page_name between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotBetween(String value1, String value2) {
            addCriterion("page_name not between", value1, value2, "pageName");
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

        public Criteria andRunStatusEqualTo(Integer value) {
            addCriterion("run_status =", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotEqualTo(Integer value) {
            addCriterion("run_status <>", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThan(Integer value) {
            addCriterion("run_status >", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_status >=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThan(Integer value) {
            addCriterion("run_status <", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanOrEqualTo(Integer value) {
            addCriterion("run_status <=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusIn(List<Integer> values) {
            addCriterion("run_status in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotIn(List<Integer> values) {
            addCriterion("run_status not in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusBetween(Integer value1, Integer value2) {
            addCriterion("run_status between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("run_status not between", value1, value2, "runStatus");
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

        public Criteria andStartUrlIsNull() {
            addCriterion("start_url is null");
            return (Criteria) this;
        }

        public Criteria andStartUrlIsNotNull() {
            addCriterion("start_url is not null");
            return (Criteria) this;
        }

        public Criteria andStartUrlEqualTo(String value) {
            addCriterion("start_url =", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotEqualTo(String value) {
            addCriterion("start_url <>", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlGreaterThan(String value) {
            addCriterion("start_url >", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlGreaterThanOrEqualTo(String value) {
            addCriterion("start_url >=", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLessThan(String value) {
            addCriterion("start_url <", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLessThanOrEqualTo(String value) {
            addCriterion("start_url <=", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlLike(String value) {
            addCriterion("start_url like", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotLike(String value) {
            addCriterion("start_url not like", value, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlIn(List<String> values) {
            addCriterion("start_url in", values, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotIn(List<String> values) {
            addCriterion("start_url not in", values, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlBetween(String value1, String value2) {
            addCriterion("start_url between", value1, value2, "startUrl");
            return (Criteria) this;
        }

        public Criteria andStartUrlNotBetween(String value1, String value2) {
            addCriterion("start_url not between", value1, value2, "startUrl");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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