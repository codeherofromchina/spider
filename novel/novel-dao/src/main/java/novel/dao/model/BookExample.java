package novel.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookExample() {
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

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("intro not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("desc is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("desc is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("desc =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("desc <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("desc >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("desc >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("desc <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("desc <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("desc like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("desc not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("desc in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("desc not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("desc between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("desc not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoIsNull() {
            addCriterion("cover_photo is null");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoIsNotNull() {
            addCriterion("cover_photo is not null");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoEqualTo(String value) {
            addCriterion("cover_photo =", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoNotEqualTo(String value) {
            addCriterion("cover_photo <>", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoGreaterThan(String value) {
            addCriterion("cover_photo >", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("cover_photo >=", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoLessThan(String value) {
            addCriterion("cover_photo <", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoLessThanOrEqualTo(String value) {
            addCriterion("cover_photo <=", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoLike(String value) {
            addCriterion("cover_photo like", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoNotLike(String value) {
            addCriterion("cover_photo not like", value, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoIn(List<String> values) {
            addCriterion("cover_photo in", values, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoNotIn(List<String> values) {
            addCriterion("cover_photo not in", values, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoBetween(String value1, String value2) {
            addCriterion("cover_photo between", value1, value2, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andCoverPhotoNotBetween(String value1, String value2) {
            addCriterion("cover_photo not between", value1, value2, "coverPhoto");
            return (Criteria) this;
        }

        public Criteria andSourcesIsNull() {
            addCriterion("sources is null");
            return (Criteria) this;
        }

        public Criteria andSourcesIsNotNull() {
            addCriterion("sources is not null");
            return (Criteria) this;
        }

        public Criteria andSourcesEqualTo(String value) {
            addCriterion("sources =", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesNotEqualTo(String value) {
            addCriterion("sources <>", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesGreaterThan(String value) {
            addCriterion("sources >", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesGreaterThanOrEqualTo(String value) {
            addCriterion("sources >=", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesLessThan(String value) {
            addCriterion("sources <", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesLessThanOrEqualTo(String value) {
            addCriterion("sources <=", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesLike(String value) {
            addCriterion("sources like", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesNotLike(String value) {
            addCriterion("sources not like", value, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesIn(List<String> values) {
            addCriterion("sources in", values, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesNotIn(List<String> values) {
            addCriterion("sources not in", values, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesBetween(String value1, String value2) {
            addCriterion("sources between", value1, value2, "sources");
            return (Criteria) this;
        }

        public Criteria andSourcesNotBetween(String value1, String value2) {
            addCriterion("sources not between", value1, value2, "sources");
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

        public Criteria andStatIsNull() {
            addCriterion("stat is null");
            return (Criteria) this;
        }

        public Criteria andStatIsNotNull() {
            addCriterion("stat is not null");
            return (Criteria) this;
        }

        public Criteria andStatEqualTo(Boolean value) {
            addCriterion("stat =", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotEqualTo(Boolean value) {
            addCriterion("stat <>", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThan(Boolean value) {
            addCriterion("stat >", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThanOrEqualTo(Boolean value) {
            addCriterion("stat >=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThan(Boolean value) {
            addCriterion("stat <", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThanOrEqualTo(Boolean value) {
            addCriterion("stat <=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatIn(List<Boolean> values) {
            addCriterion("stat in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotIn(List<Boolean> values) {
            addCriterion("stat not in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatBetween(Boolean value1, Boolean value2) {
            addCriterion("stat between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotBetween(Boolean value1, Boolean value2) {
            addCriterion("stat not between", value1, value2, "stat");
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

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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