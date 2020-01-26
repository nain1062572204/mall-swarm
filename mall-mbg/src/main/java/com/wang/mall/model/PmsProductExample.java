package com.wang.mall.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PmsProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsProductExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIsNull() {
            addCriterion("product_attribute_category_id is null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIsNotNull() {
            addCriterion("product_attribute_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdEqualTo(Long value) {
            addCriterion("product_attribute_category_id =", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotEqualTo(Long value) {
            addCriterion("product_attribute_category_id <>", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdGreaterThan(Long value) {
            addCriterion("product_attribute_category_id >", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_attribute_category_id >=", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdLessThan(Long value) {
            addCriterion("product_attribute_category_id <", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("product_attribute_category_id <=", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIn(List<Long> values) {
            addCriterion("product_attribute_category_id in", values, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotIn(List<Long> values) {
            addCriterion("product_attribute_category_id not in", values, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdBetween(Long value1, Long value2) {
            addCriterion("product_attribute_category_id between", value1, value2, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("product_attribute_category_id not between", value1, value2, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdIsNull() {
            addCriterion("pms_product_category_id is null");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdIsNotNull() {
            addCriterion("pms_product_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdEqualTo(Long value) {
            addCriterion("pms_product_category_id =", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdNotEqualTo(Long value) {
            addCriterion("pms_product_category_id <>", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdGreaterThan(Long value) {
            addCriterion("pms_product_category_id >", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pms_product_category_id >=", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdLessThan(Long value) {
            addCriterion("pms_product_category_id <", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("pms_product_category_id <=", value, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdIn(List<Long> values) {
            addCriterion("pms_product_category_id in", values, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdNotIn(List<Long> values) {
            addCriterion("pms_product_category_id not in", values, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdBetween(Long value1, Long value2) {
            addCriterion("pms_product_category_id between", value1, value2, "pmsProductCategoryId");
            return (Criteria) this;
        }

        public Criteria andPmsProductCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("pms_product_category_id not between", value1, value2, "pmsProductCategoryId");
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

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andProductSnIsNull() {
            addCriterion("product_sn is null");
            return (Criteria) this;
        }

        public Criteria andProductSnIsNotNull() {
            addCriterion("product_sn is not null");
            return (Criteria) this;
        }

        public Criteria andProductSnEqualTo(String value) {
            addCriterion("product_sn =", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnNotEqualTo(String value) {
            addCriterion("product_sn <>", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnGreaterThan(String value) {
            addCriterion("product_sn >", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnGreaterThanOrEqualTo(String value) {
            addCriterion("product_sn >=", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnLessThan(String value) {
            addCriterion("product_sn <", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnLessThanOrEqualTo(String value) {
            addCriterion("product_sn <=", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnLike(String value) {
            addCriterion("product_sn like", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnNotLike(String value) {
            addCriterion("product_sn not like", value, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnIn(List<String> values) {
            addCriterion("product_sn in", values, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnNotIn(List<String> values) {
            addCriterion("product_sn not in", values, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnBetween(String value1, String value2) {
            addCriterion("product_sn between", value1, value2, "productSn");
            return (Criteria) this;
        }

        public Criteria andProductSnNotBetween(String value1, String value2) {
            addCriterion("product_sn not between", value1, value2, "productSn");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusIsNull() {
            addCriterion("delete_status is null");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusIsNotNull() {
            addCriterion("delete_status is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusEqualTo(Integer value) {
            addCriterion("delete_status =", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusNotEqualTo(Integer value) {
            addCriterion("delete_status <>", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusGreaterThan(Integer value) {
            addCriterion("delete_status >", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_status >=", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusLessThan(Integer value) {
            addCriterion("delete_status <", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusLessThanOrEqualTo(Integer value) {
            addCriterion("delete_status <=", value, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusIn(List<Integer> values) {
            addCriterion("delete_status in", values, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusNotIn(List<Integer> values) {
            addCriterion("delete_status not in", values, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusBetween(Integer value1, Integer value2) {
            addCriterion("delete_status between", value1, value2, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andDeleteStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_status not between", value1, value2, "deleteStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIsNull() {
            addCriterion("publish_status is null");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIsNotNull() {
            addCriterion("publish_status is not null");
            return (Criteria) this;
        }

        public Criteria andPublishStatusEqualTo(Integer value) {
            addCriterion("publish_status =", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotEqualTo(Integer value) {
            addCriterion("publish_status <>", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusGreaterThan(Integer value) {
            addCriterion("publish_status >", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_status >=", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusLessThan(Integer value) {
            addCriterion("publish_status <", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusLessThanOrEqualTo(Integer value) {
            addCriterion("publish_status <=", value, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusIn(List<Integer> values) {
            addCriterion("publish_status in", values, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotIn(List<Integer> values) {
            addCriterion("publish_status not in", values, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusBetween(Integer value1, Integer value2) {
            addCriterion("publish_status between", value1, value2, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andPublishStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_status not between", value1, value2, "publishStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusIsNull() {
            addCriterion("new_status is null");
            return (Criteria) this;
        }

        public Criteria andNewStatusIsNotNull() {
            addCriterion("new_status is not null");
            return (Criteria) this;
        }

        public Criteria andNewStatusEqualTo(Integer value) {
            addCriterion("new_status =", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusNotEqualTo(Integer value) {
            addCriterion("new_status <>", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusGreaterThan(Integer value) {
            addCriterion("new_status >", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_status >=", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusLessThan(Integer value) {
            addCriterion("new_status <", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusLessThanOrEqualTo(Integer value) {
            addCriterion("new_status <=", value, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusIn(List<Integer> values) {
            addCriterion("new_status in", values, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusNotIn(List<Integer> values) {
            addCriterion("new_status not in", values, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusBetween(Integer value1, Integer value2) {
            addCriterion("new_status between", value1, value2, "newStatus");
            return (Criteria) this;
        }

        public Criteria andNewStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("new_status not between", value1, value2, "newStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusIsNull() {
            addCriterion("recommand_status is null");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusIsNotNull() {
            addCriterion("recommand_status is not null");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusEqualTo(Integer value) {
            addCriterion("recommand_status =", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusNotEqualTo(Integer value) {
            addCriterion("recommand_status <>", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusGreaterThan(Integer value) {
            addCriterion("recommand_status >", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommand_status >=", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusLessThan(Integer value) {
            addCriterion("recommand_status <", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusLessThanOrEqualTo(Integer value) {
            addCriterion("recommand_status <=", value, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusIn(List<Integer> values) {
            addCriterion("recommand_status in", values, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusNotIn(List<Integer> values) {
            addCriterion("recommand_status not in", values, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusBetween(Integer value1, Integer value2) {
            addCriterion("recommand_status between", value1, value2, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andRecommandStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("recommand_status not between", value1, value2, "recommandStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIsNull() {
            addCriterion("verify_status is null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIsNotNull() {
            addCriterion("verify_status is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusEqualTo(Integer value) {
            addCriterion("verify_status =", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotEqualTo(Integer value) {
            addCriterion("verify_status <>", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusGreaterThan(Integer value) {
            addCriterion("verify_status >", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify_status >=", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusLessThan(Integer value) {
            addCriterion("verify_status <", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("verify_status <=", value, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusIn(List<Integer> values) {
            addCriterion("verify_status in", values, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotIn(List<Integer> values) {
            addCriterion("verify_status not in", values, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusBetween(Integer value1, Integer value2) {
            addCriterion("verify_status between", value1, value2, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andVerifyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("verify_status not between", value1, value2, "verifyStatus");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSaleIsNull() {
            addCriterion("sale is null");
            return (Criteria) this;
        }

        public Criteria andSaleIsNotNull() {
            addCriterion("sale is not null");
            return (Criteria) this;
        }

        public Criteria andSaleEqualTo(Integer value) {
            addCriterion("sale =", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotEqualTo(Integer value) {
            addCriterion("sale <>", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleGreaterThan(Integer value) {
            addCriterion("sale >", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale >=", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleLessThan(Integer value) {
            addCriterion("sale <", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleLessThanOrEqualTo(Integer value) {
            addCriterion("sale <=", value, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleIn(List<Integer> values) {
            addCriterion("sale in", values, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotIn(List<Integer> values) {
            addCriterion("sale not in", values, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleBetween(Integer value1, Integer value2) {
            addCriterion("sale between", value1, value2, "sale");
            return (Criteria) this;
        }

        public Criteria andSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("sale not between", value1, value2, "sale");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIsNull() {
            addCriterion("promotion_price is null");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIsNotNull() {
            addCriterion("promotion_price is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceEqualTo(BigDecimal value) {
            addCriterion("promotion_price =", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotEqualTo(BigDecimal value) {
            addCriterion("promotion_price <>", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceGreaterThan(BigDecimal value) {
            addCriterion("promotion_price >", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("promotion_price >=", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceLessThan(BigDecimal value) {
            addCriterion("promotion_price <", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("promotion_price <=", value, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceIn(List<BigDecimal> values) {
            addCriterion("promotion_price in", values, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotIn(List<BigDecimal> values) {
            addCriterion("promotion_price not in", values, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promotion_price between", value1, value2, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andPromotionPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("promotion_price not between", value1, value2, "promotionPrice");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceIsNull() {
            addCriterion("orginal_price is null");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceIsNotNull() {
            addCriterion("orginal_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceEqualTo(BigDecimal value) {
            addCriterion("orginal_price =", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceNotEqualTo(BigDecimal value) {
            addCriterion("orginal_price <>", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceGreaterThan(BigDecimal value) {
            addCriterion("orginal_price >", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("orginal_price >=", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceLessThan(BigDecimal value) {
            addCriterion("orginal_price <", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("orginal_price <=", value, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceIn(List<BigDecimal> values) {
            addCriterion("orginal_price in", values, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceNotIn(List<BigDecimal> values) {
            addCriterion("orginal_price not in", values, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orginal_price between", value1, value2, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andOrginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orginal_price not between", value1, value2, "orginalPrice");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andServicesIdsIsNull() {
            addCriterion("services_ids is null");
            return (Criteria) this;
        }

        public Criteria andServicesIdsIsNotNull() {
            addCriterion("services_ids is not null");
            return (Criteria) this;
        }

        public Criteria andServicesIdsEqualTo(String value) {
            addCriterion("services_ids =", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsNotEqualTo(String value) {
            addCriterion("services_ids <>", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsGreaterThan(String value) {
            addCriterion("services_ids >", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsGreaterThanOrEqualTo(String value) {
            addCriterion("services_ids >=", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsLessThan(String value) {
            addCriterion("services_ids <", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsLessThanOrEqualTo(String value) {
            addCriterion("services_ids <=", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsLike(String value) {
            addCriterion("services_ids like", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsNotLike(String value) {
            addCriterion("services_ids not like", value, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsIn(List<String> values) {
            addCriterion("services_ids in", values, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsNotIn(List<String> values) {
            addCriterion("services_ids not in", values, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsBetween(String value1, String value2) {
            addCriterion("services_ids between", value1, value2, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andServicesIdsNotBetween(String value1, String value2) {
            addCriterion("services_ids not between", value1, value2, "servicesIds");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsIsNull() {
            addCriterion("album_pics is null");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsIsNotNull() {
            addCriterion("album_pics is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsEqualTo(String value) {
            addCriterion("album_pics =", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsNotEqualTo(String value) {
            addCriterion("album_pics <>", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsGreaterThan(String value) {
            addCriterion("album_pics >", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsGreaterThanOrEqualTo(String value) {
            addCriterion("album_pics >=", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsLessThan(String value) {
            addCriterion("album_pics <", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsLessThanOrEqualTo(String value) {
            addCriterion("album_pics <=", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsLike(String value) {
            addCriterion("album_pics like", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsNotLike(String value) {
            addCriterion("album_pics not like", value, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsIn(List<String> values) {
            addCriterion("album_pics in", values, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsNotIn(List<String> values) {
            addCriterion("album_pics not in", values, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsBetween(String value1, String value2) {
            addCriterion("album_pics between", value1, value2, "albumPics");
            return (Criteria) this;
        }

        public Criteria andAlbumPicsNotBetween(String value1, String value2) {
            addCriterion("album_pics not between", value1, value2, "albumPics");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameIsNull() {
            addCriterion("product_category_name is null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameIsNotNull() {
            addCriterion("product_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameEqualTo(String value) {
            addCriterion("product_category_name =", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameNotEqualTo(String value) {
            addCriterion("product_category_name <>", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameGreaterThan(String value) {
            addCriterion("product_category_name >", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_category_name >=", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameLessThan(String value) {
            addCriterion("product_category_name <", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("product_category_name <=", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameLike(String value) {
            addCriterion("product_category_name like", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameNotLike(String value) {
            addCriterion("product_category_name not like", value, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameIn(List<String> values) {
            addCriterion("product_category_name in", values, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameNotIn(List<String> values) {
            addCriterion("product_category_name not in", values, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameBetween(String value1, String value2) {
            addCriterion("product_category_name between", value1, value2, "productCategoryName");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNameNotBetween(String value1, String value2) {
            addCriterion("product_category_name not between", value1, value2, "productCategoryName");
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