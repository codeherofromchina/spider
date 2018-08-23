package novel.dao.model;

import java.util.ArrayList;
import java.util.List;

public class SpiderTypesMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpiderTypesMappingExample() {
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

        public Criteria andOriginalTypesIsNull() {
            addCriterion("original_types is null");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesIsNotNull() {
            addCriterion("original_types is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesEqualTo(String value) {
            addCriterion("original_types =", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesNotEqualTo(String value) {
            addCriterion("original_types <>", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesGreaterThan(String value) {
            addCriterion("original_types >", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesGreaterThanOrEqualTo(String value) {
            addCriterion("original_types >=", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesLessThan(String value) {
            addCriterion("original_types <", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesLessThanOrEqualTo(String value) {
            addCriterion("original_types <=", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesLike(String value) {
            addCriterion("original_types like", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesNotLike(String value) {
            addCriterion("original_types not like", value, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesIn(List<String> values) {
            addCriterion("original_types in", values, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesNotIn(List<String> values) {
            addCriterion("original_types not in", values, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesBetween(String value1, String value2) {
            addCriterion("original_types between", value1, value2, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andOriginalTypesNotBetween(String value1, String value2) {
            addCriterion("original_types not between", value1, value2, "originalTypes");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdIsNull() {
            addCriterion("book_types_id is null");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdIsNotNull() {
            addCriterion("book_types_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdEqualTo(Integer value) {
            addCriterion("book_types_id =", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdNotEqualTo(Integer value) {
            addCriterion("book_types_id <>", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdGreaterThan(Integer value) {
            addCriterion("book_types_id >", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_types_id >=", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdLessThan(Integer value) {
            addCriterion("book_types_id <", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_types_id <=", value, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdIn(List<Integer> values) {
            addCriterion("book_types_id in", values, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdNotIn(List<Integer> values) {
            addCriterion("book_types_id not in", values, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdBetween(Integer value1, Integer value2) {
            addCriterion("book_types_id between", value1, value2, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_types_id not between", value1, value2, "bookTypesId");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameIsNull() {
            addCriterion("book_types_name is null");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameIsNotNull() {
            addCriterion("book_types_name is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameEqualTo(String value) {
            addCriterion("book_types_name =", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameNotEqualTo(String value) {
            addCriterion("book_types_name <>", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameGreaterThan(String value) {
            addCriterion("book_types_name >", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameGreaterThanOrEqualTo(String value) {
            addCriterion("book_types_name >=", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameLessThan(String value) {
            addCriterion("book_types_name <", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameLessThanOrEqualTo(String value) {
            addCriterion("book_types_name <=", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameLike(String value) {
            addCriterion("book_types_name like", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameNotLike(String value) {
            addCriterion("book_types_name not like", value, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameIn(List<String> values) {
            addCriterion("book_types_name in", values, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameNotIn(List<String> values) {
            addCriterion("book_types_name not in", values, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameBetween(String value1, String value2) {
            addCriterion("book_types_name between", value1, value2, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andBookTypesNameNotBetween(String value1, String value2) {
            addCriterion("book_types_name not between", value1, value2, "bookTypesName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("seq not between", value1, value2, "seq");
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