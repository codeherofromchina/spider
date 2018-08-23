package novel.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatalogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CatalogExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andShowNameIsNull() {
            addCriterion("show_name is null");
            return (Criteria) this;
        }

        public Criteria andShowNameIsNotNull() {
            addCriterion("show_name is not null");
            return (Criteria) this;
        }

        public Criteria andShowNameEqualTo(String value) {
            addCriterion("show_name =", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotEqualTo(String value) {
            addCriterion("show_name <>", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameGreaterThan(String value) {
            addCriterion("show_name >", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameGreaterThanOrEqualTo(String value) {
            addCriterion("show_name >=", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLessThan(String value) {
            addCriterion("show_name <", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLessThanOrEqualTo(String value) {
            addCriterion("show_name <=", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLike(String value) {
            addCriterion("show_name like", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotLike(String value) {
            addCriterion("show_name not like", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameIn(List<String> values) {
            addCriterion("show_name in", values, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotIn(List<String> values) {
            addCriterion("show_name not in", values, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameBetween(String value1, String value2) {
            addCriterion("show_name between", value1, value2, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotBetween(String value1, String value2) {
            addCriterion("show_name not between", value1, value2, "showName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameIsNull() {
            addCriterion("volume_name is null");
            return (Criteria) this;
        }

        public Criteria andVolumeNameIsNotNull() {
            addCriterion("volume_name is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeNameEqualTo(String value) {
            addCriterion("volume_name =", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameNotEqualTo(String value) {
            addCriterion("volume_name <>", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameGreaterThan(String value) {
            addCriterion("volume_name >", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameGreaterThanOrEqualTo(String value) {
            addCriterion("volume_name >=", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameLessThan(String value) {
            addCriterion("volume_name <", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameLessThanOrEqualTo(String value) {
            addCriterion("volume_name <=", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameLike(String value) {
            addCriterion("volume_name like", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameNotLike(String value) {
            addCriterion("volume_name not like", value, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameIn(List<String> values) {
            addCriterion("volume_name in", values, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameNotIn(List<String> values) {
            addCriterion("volume_name not in", values, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameBetween(String value1, String value2) {
            addCriterion("volume_name between", value1, value2, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNameNotBetween(String value1, String value2) {
            addCriterion("volume_name not between", value1, value2, "volumeName");
            return (Criteria) this;
        }

        public Criteria andVolumeNumIsNull() {
            addCriterion("volume_num is null");
            return (Criteria) this;
        }

        public Criteria andVolumeNumIsNotNull() {
            addCriterion("volume_num is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeNumEqualTo(Integer value) {
            addCriterion("volume_num =", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumNotEqualTo(Integer value) {
            addCriterion("volume_num <>", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumGreaterThan(Integer value) {
            addCriterion("volume_num >", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("volume_num >=", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumLessThan(Integer value) {
            addCriterion("volume_num <", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumLessThanOrEqualTo(Integer value) {
            addCriterion("volume_num <=", value, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumIn(List<Integer> values) {
            addCriterion("volume_num in", values, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumNotIn(List<Integer> values) {
            addCriterion("volume_num not in", values, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumBetween(Integer value1, Integer value2) {
            addCriterion("volume_num between", value1, value2, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andVolumeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("volume_num not between", value1, value2, "volumeNum");
            return (Criteria) this;
        }

        public Criteria andContentUrlIsNull() {
            addCriterion("content_url is null");
            return (Criteria) this;
        }

        public Criteria andContentUrlIsNotNull() {
            addCriterion("content_url is not null");
            return (Criteria) this;
        }

        public Criteria andContentUrlEqualTo(String value) {
            addCriterion("content_url =", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotEqualTo(String value) {
            addCriterion("content_url <>", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlGreaterThan(String value) {
            addCriterion("content_url >", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlGreaterThanOrEqualTo(String value) {
            addCriterion("content_url >=", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLessThan(String value) {
            addCriterion("content_url <", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLessThanOrEqualTo(String value) {
            addCriterion("content_url <=", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlLike(String value) {
            addCriterion("content_url like", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotLike(String value) {
            addCriterion("content_url not like", value, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlIn(List<String> values) {
            addCriterion("content_url in", values, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotIn(List<String> values) {
            addCriterion("content_url not in", values, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlBetween(String value1, String value2) {
            addCriterion("content_url between", value1, value2, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andContentUrlNotBetween(String value1, String value2) {
            addCriterion("content_url not between", value1, value2, "contentUrl");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNull() {
            addCriterion("word_count is null");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNotNull() {
            addCriterion("word_count is not null");
            return (Criteria) this;
        }

        public Criteria andWordCountEqualTo(Integer value) {
            addCriterion("word_count =", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotEqualTo(Integer value) {
            addCriterion("word_count <>", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThan(Integer value) {
            addCriterion("word_count >", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_count >=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThan(Integer value) {
            addCriterion("word_count <", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThanOrEqualTo(Integer value) {
            addCriterion("word_count <=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountIn(List<Integer> values) {
            addCriterion("word_count in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotIn(List<Integer> values) {
            addCriterion("word_count not in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountBetween(Integer value1, Integer value2) {
            addCriterion("word_count between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotBetween(Integer value1, Integer value2) {
            addCriterion("word_count not between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andCatalogNumIsNull() {
            addCriterion("catalog_num is null");
            return (Criteria) this;
        }

        public Criteria andCatalogNumIsNotNull() {
            addCriterion("catalog_num is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogNumEqualTo(Integer value) {
            addCriterion("catalog_num =", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumNotEqualTo(Integer value) {
            addCriterion("catalog_num <>", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumGreaterThan(Integer value) {
            addCriterion("catalog_num >", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("catalog_num >=", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumLessThan(Integer value) {
            addCriterion("catalog_num <", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumLessThanOrEqualTo(Integer value) {
            addCriterion("catalog_num <=", value, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumIn(List<Integer> values) {
            addCriterion("catalog_num in", values, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumNotIn(List<Integer> values) {
            addCriterion("catalog_num not in", values, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumBetween(Integer value1, Integer value2) {
            addCriterion("catalog_num between", value1, value2, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andCatalogNumNotBetween(Integer value1, Integer value2) {
            addCriterion("catalog_num not between", value1, value2, "catalogNum");
            return (Criteria) this;
        }

        public Criteria andPubTimeIsNull() {
            addCriterion("pub_time is null");
            return (Criteria) this;
        }

        public Criteria andPubTimeIsNotNull() {
            addCriterion("pub_time is not null");
            return (Criteria) this;
        }

        public Criteria andPubTimeEqualTo(Date value) {
            addCriterion("pub_time =", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotEqualTo(Date value) {
            addCriterion("pub_time <>", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeGreaterThan(Date value) {
            addCriterion("pub_time >", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pub_time >=", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeLessThan(Date value) {
            addCriterion("pub_time <", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeLessThanOrEqualTo(Date value) {
            addCriterion("pub_time <=", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeIn(List<Date> values) {
            addCriterion("pub_time in", values, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotIn(List<Date> values) {
            addCriterion("pub_time not in", values, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeBetween(Date value1, Date value2) {
            addCriterion("pub_time between", value1, value2, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotBetween(Date value1, Date value2) {
            addCriterion("pub_time not between", value1, value2, "pubTime");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(Boolean value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(Boolean value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(Boolean value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(Boolean value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(Boolean value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<Boolean> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<Boolean> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(Boolean value1, Boolean value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Boolean value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Boolean value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Boolean value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Boolean value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Boolean> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Boolean> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andSourcesIdIsNull() {
            addCriterion("sources_id is null");
            return (Criteria) this;
        }

        public Criteria andSourcesIdIsNotNull() {
            addCriterion("sources_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourcesIdEqualTo(String value) {
            addCriterion("sources_id =", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdNotEqualTo(String value) {
            addCriterion("sources_id <>", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdGreaterThan(String value) {
            addCriterion("sources_id >", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdGreaterThanOrEqualTo(String value) {
            addCriterion("sources_id >=", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdLessThan(String value) {
            addCriterion("sources_id <", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdLessThanOrEqualTo(String value) {
            addCriterion("sources_id <=", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdLike(String value) {
            addCriterion("sources_id like", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdNotLike(String value) {
            addCriterion("sources_id not like", value, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdIn(List<String> values) {
            addCriterion("sources_id in", values, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdNotIn(List<String> values) {
            addCriterion("sources_id not in", values, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdBetween(String value1, String value2) {
            addCriterion("sources_id between", value1, value2, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andSourcesIdNotBetween(String value1, String value2) {
            addCriterion("sources_id not between", value1, value2, "sourcesId");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andSpiderDateIsNull() {
            addCriterion("spider_date is null");
            return (Criteria) this;
        }

        public Criteria andSpiderDateIsNotNull() {
            addCriterion("spider_date is not null");
            return (Criteria) this;
        }

        public Criteria andSpiderDateEqualTo(Date value) {
            addCriterion("spider_date =", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateNotEqualTo(Date value) {
            addCriterion("spider_date <>", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateGreaterThan(Date value) {
            addCriterion("spider_date >", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("spider_date >=", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateLessThan(Date value) {
            addCriterion("spider_date <", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateLessThanOrEqualTo(Date value) {
            addCriterion("spider_date <=", value, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateIn(List<Date> values) {
            addCriterion("spider_date in", values, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateNotIn(List<Date> values) {
            addCriterion("spider_date not in", values, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateBetween(Date value1, Date value2) {
            addCriterion("spider_date between", value1, value2, "spiderDate");
            return (Criteria) this;
        }

        public Criteria andSpiderDateNotBetween(Date value1, Date value2) {
            addCriterion("spider_date not between", value1, value2, "spiderDate");
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