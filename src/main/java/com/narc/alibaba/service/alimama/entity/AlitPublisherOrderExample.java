package com.narc.alibaba.service.alimama.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlitPublisherOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlitPublisherOrderExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andTradeIdIsNull() {
            addCriterion("TRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("TRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(String value) {
            addCriterion("TRADE_ID =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(String value) {
            addCriterion("TRADE_ID <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(String value) {
            addCriterion("TRADE_ID >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_ID >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(String value) {
            addCriterion("TRADE_ID <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(String value) {
            addCriterion("TRADE_ID <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLike(String value) {
            addCriterion("TRADE_ID like", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotLike(String value) {
            addCriterion("TRADE_ID not like", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<String> values) {
            addCriterion("TRADE_ID in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<String> values) {
            addCriterion("TRADE_ID not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(String value1, String value2) {
            addCriterion("TRADE_ID between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(String value1, String value2) {
            addCriterion("TRADE_ID not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdIsNull() {
            addCriterion("TRADE_PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdIsNotNull() {
            addCriterion("TRADE_PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdEqualTo(String value) {
            addCriterion("TRADE_PARENT_ID =", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdNotEqualTo(String value) {
            addCriterion("TRADE_PARENT_ID <>", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdGreaterThan(String value) {
            addCriterion("TRADE_PARENT_ID >", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_PARENT_ID >=", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdLessThan(String value) {
            addCriterion("TRADE_PARENT_ID <", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdLessThanOrEqualTo(String value) {
            addCriterion("TRADE_PARENT_ID <=", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdLike(String value) {
            addCriterion("TRADE_PARENT_ID like", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdNotLike(String value) {
            addCriterion("TRADE_PARENT_ID not like", value, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdIn(List<String> values) {
            addCriterion("TRADE_PARENT_ID in", values, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdNotIn(List<String> values) {
            addCriterion("TRADE_PARENT_ID not in", values, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdBetween(String value1, String value2) {
            addCriterion("TRADE_PARENT_ID between", value1, value2, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andTradeParentIdNotBetween(String value1, String value2) {
            addCriterion("TRADE_PARENT_ID not between", value1, value2, "tradeParentId");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNull() {
            addCriterion("ITEM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andItemNumIsNotNull() {
            addCriterion("ITEM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andItemNumEqualTo(Integer value) {
            addCriterion("ITEM_NUM =", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotEqualTo(Integer value) {
            addCriterion("ITEM_NUM <>", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThan(Integer value) {
            addCriterion("ITEM_NUM >", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUM >=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThan(Integer value) {
            addCriterion("ITEM_NUM <", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_NUM <=", value, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumIn(List<Integer> values) {
            addCriterion("ITEM_NUM in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotIn(List<Integer> values) {
            addCriterion("ITEM_NUM not in", values, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUM between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_NUM not between", value1, value2, "itemNum");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNull() {
            addCriterion("ITEM_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andItemTitleIsNotNull() {
            addCriterion("ITEM_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTitleEqualTo(String value) {
            addCriterion("ITEM_TITLE =", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotEqualTo(String value) {
            addCriterion("ITEM_TITLE <>", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThan(String value) {
            addCriterion("ITEM_TITLE >", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TITLE >=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThan(String value) {
            addCriterion("ITEM_TITLE <", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TITLE <=", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleLike(String value) {
            addCriterion("ITEM_TITLE like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotLike(String value) {
            addCriterion("ITEM_TITLE not like", value, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleIn(List<String> values) {
            addCriterion("ITEM_TITLE in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotIn(List<String> values) {
            addCriterion("ITEM_TITLE not in", values, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleBetween(String value1, String value2) {
            addCriterion("ITEM_TITLE between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andItemTitleNotBetween(String value1, String value2) {
            addCriterion("ITEM_TITLE not between", value1, value2, "itemTitle");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeIsNull() {
            addCriterion("TK_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeIsNotNull() {
            addCriterion("TK_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeEqualTo(Date value) {
            addCriterion("TK_CREATE_TIME =", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeNotEqualTo(Date value) {
            addCriterion("TK_CREATE_TIME <>", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeGreaterThan(Date value) {
            addCriterion("TK_CREATE_TIME >", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TK_CREATE_TIME >=", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeLessThan(Date value) {
            addCriterion("TK_CREATE_TIME <", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("TK_CREATE_TIME <=", value, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeIn(List<Date> values) {
            addCriterion("TK_CREATE_TIME in", values, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeNotIn(List<Date> values) {
            addCriterion("TK_CREATE_TIME not in", values, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeBetween(Date value1, Date value2) {
            addCriterion("TK_CREATE_TIME between", value1, value2, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTkCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("TK_CREATE_TIME not between", value1, value2, "tkCreateTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeIsNull() {
            addCriterion("TB_PAID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeIsNotNull() {
            addCriterion("TB_PAID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeEqualTo(Date value) {
            addCriterion("TB_PAID_TIME =", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeNotEqualTo(Date value) {
            addCriterion("TB_PAID_TIME <>", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeGreaterThan(Date value) {
            addCriterion("TB_PAID_TIME >", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TB_PAID_TIME >=", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeLessThan(Date value) {
            addCriterion("TB_PAID_TIME <", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeLessThanOrEqualTo(Date value) {
            addCriterion("TB_PAID_TIME <=", value, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeIn(List<Date> values) {
            addCriterion("TB_PAID_TIME in", values, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeNotIn(List<Date> values) {
            addCriterion("TB_PAID_TIME not in", values, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeBetween(Date value1, Date value2) {
            addCriterion("TB_PAID_TIME between", value1, value2, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTbPaidTimeNotBetween(Date value1, Date value2) {
            addCriterion("TB_PAID_TIME not between", value1, value2, "tbPaidTime");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIsNull() {
            addCriterion("TOTAL_COMMISSION_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIsNotNull() {
            addCriterion("TOTAL_COMMISSION_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE =", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE <>", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE >", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE >=", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateLessThan(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE <", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COMMISSION_RATE <=", value, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateIn(List<BigDecimal> values) {
            addCriterion("TOTAL_COMMISSION_RATE in", values, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_COMMISSION_RATE not in", values, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_COMMISSION_RATE between", value1, value2, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andTotalCommissionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_COMMISSION_RATE not between", value1, value2, "totalCommissionRate");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIsNull() {
            addCriterion("ALIPAY_TOTAL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIsNotNull() {
            addCriterion("ALIPAY_TOTAL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE =", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <>", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE >", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE >=", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceLessThan(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIPAY_TOTAL_PRICE <=", value, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceIn(List<BigDecimal> values) {
            addCriterion("ALIPAY_TOTAL_PRICE in", values, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("ALIPAY_TOTAL_PRICE not in", values, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIPAY_TOTAL_PRICE between", value1, value2, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andAlipayTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIPAY_TOTAL_PRICE not between", value1, value2, "alipayTotalPrice");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNull() {
            addCriterion("SUBSIDY_RATE is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIsNotNull() {
            addCriterion("SUBSIDY_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateEqualTo(String value) {
            addCriterion("SUBSIDY_RATE =", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotEqualTo(String value) {
            addCriterion("SUBSIDY_RATE <>", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThan(String value) {
            addCriterion("SUBSIDY_RATE >", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_RATE >=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThan(String value) {
            addCriterion("SUBSIDY_RATE <", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLessThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_RATE <=", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateLike(String value) {
            addCriterion("SUBSIDY_RATE like", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotLike(String value) {
            addCriterion("SUBSIDY_RATE not like", value, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateIn(List<String> values) {
            addCriterion("SUBSIDY_RATE in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotIn(List<String> values) {
            addCriterion("SUBSIDY_RATE not in", values, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateBetween(String value1, String value2) {
            addCriterion("SUBSIDY_RATE between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andSubsidyRateNotBetween(String value1, String value2) {
            addCriterion("SUBSIDY_RATE not between", value1, value2, "subsidyRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateIsNull() {
            addCriterion("TK_TOTAL_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateIsNotNull() {
            addCriterion("TK_TOTAL_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateEqualTo(String value) {
            addCriterion("TK_TOTAL_RATE =", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateNotEqualTo(String value) {
            addCriterion("TK_TOTAL_RATE <>", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateGreaterThan(String value) {
            addCriterion("TK_TOTAL_RATE >", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateGreaterThanOrEqualTo(String value) {
            addCriterion("TK_TOTAL_RATE >=", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateLessThan(String value) {
            addCriterion("TK_TOTAL_RATE <", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateLessThanOrEqualTo(String value) {
            addCriterion("TK_TOTAL_RATE <=", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateLike(String value) {
            addCriterion("TK_TOTAL_RATE like", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateNotLike(String value) {
            addCriterion("TK_TOTAL_RATE not like", value, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateIn(List<String> values) {
            addCriterion("TK_TOTAL_RATE in", values, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateNotIn(List<String> values) {
            addCriterion("TK_TOTAL_RATE not in", values, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateBetween(String value1, String value2) {
            addCriterion("TK_TOTAL_RATE between", value1, value2, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andTkTotalRateNotBetween(String value1, String value2) {
            addCriterion("TK_TOTAL_RATE not between", value1, value2, "tkTotalRate");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeIsNull() {
            addCriterion("PUB_SHARE_PRE_FEE is null");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeIsNotNull() {
            addCriterion("PUB_SHARE_PRE_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeEqualTo(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE =", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeNotEqualTo(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE <>", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeGreaterThan(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE >", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE >=", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeLessThan(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE <", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PUB_SHARE_PRE_FEE <=", value, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeIn(List<BigDecimal> values) {
            addCriterion("PUB_SHARE_PRE_FEE in", values, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeNotIn(List<BigDecimal> values) {
            addCriterion("PUB_SHARE_PRE_FEE not in", values, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PUB_SHARE_PRE_FEE between", value1, value2, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andPubSharePreFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PUB_SHARE_PRE_FEE not between", value1, value2, "pubSharePreFee");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateIsNull() {
            addCriterion("ALIMAMA_RATE is null");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateIsNotNull() {
            addCriterion("ALIMAMA_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateEqualTo(BigDecimal value) {
            addCriterion("ALIMAMA_RATE =", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateNotEqualTo(BigDecimal value) {
            addCriterion("ALIMAMA_RATE <>", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateGreaterThan(BigDecimal value) {
            addCriterion("ALIMAMA_RATE >", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIMAMA_RATE >=", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateLessThan(BigDecimal value) {
            addCriterion("ALIMAMA_RATE <", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ALIMAMA_RATE <=", value, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateIn(List<BigDecimal> values) {
            addCriterion("ALIMAMA_RATE in", values, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateNotIn(List<BigDecimal> values) {
            addCriterion("ALIMAMA_RATE not in", values, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIMAMA_RATE between", value1, value2, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andAlimamaRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ALIMAMA_RATE not between", value1, value2, "alimamaRate");
            return (Criteria) this;
        }

        public Criteria andTkStatusIsNull() {
            addCriterion("TK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTkStatusIsNotNull() {
            addCriterion("TK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTkStatusEqualTo(String value) {
            addCriterion("TK_STATUS =", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusNotEqualTo(String value) {
            addCriterion("TK_STATUS <>", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusGreaterThan(String value) {
            addCriterion("TK_STATUS >", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TK_STATUS >=", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusLessThan(String value) {
            addCriterion("TK_STATUS <", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusLessThanOrEqualTo(String value) {
            addCriterion("TK_STATUS <=", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusLike(String value) {
            addCriterion("TK_STATUS like", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusNotLike(String value) {
            addCriterion("TK_STATUS not like", value, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusIn(List<String> values) {
            addCriterion("TK_STATUS in", values, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusNotIn(List<String> values) {
            addCriterion("TK_STATUS not in", values, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusBetween(String value1, String value2) {
            addCriterion("TK_STATUS between", value1, value2, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andTkStatusNotBetween(String value1, String value2) {
            addCriterion("TK_STATUS not between", value1, value2, "tkStatus");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("ITEM_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("ITEM_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("ITEM_PRICE >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("ITEM_PRICE <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ITEM_PRICE <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("ITEM_PRICE in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("ITEM_PRICE not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITEM_PRICE between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITEM_PRICE not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNull() {
            addCriterion("SENDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSenderIdIsNotNull() {
            addCriterion("SENDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSenderIdEqualTo(String value) {
            addCriterion("SENDER_ID =", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotEqualTo(String value) {
            addCriterion("SENDER_ID <>", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThan(String value) {
            addCriterion("SENDER_ID >", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdGreaterThanOrEqualTo(String value) {
            addCriterion("SENDER_ID >=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThan(String value) {
            addCriterion("SENDER_ID <", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLessThanOrEqualTo(String value) {
            addCriterion("SENDER_ID <=", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdLike(String value) {
            addCriterion("SENDER_ID like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotLike(String value) {
            addCriterion("SENDER_ID not like", value, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdIn(List<String> values) {
            addCriterion("SENDER_ID in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotIn(List<String> values) {
            addCriterion("SENDER_ID not in", values, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdBetween(String value1, String value2) {
            addCriterion("SENDER_ID between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderIdNotBetween(String value1, String value2) {
            addCriterion("SENDER_ID not between", value1, value2, "senderId");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNull() {
            addCriterion("SENDER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSenderNameIsNotNull() {
            addCriterion("SENDER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSenderNameEqualTo(String value) {
            addCriterion("SENDER_NAME =", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotEqualTo(String value) {
            addCriterion("SENDER_NAME <>", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThan(String value) {
            addCriterion("SENDER_NAME >", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameGreaterThanOrEqualTo(String value) {
            addCriterion("SENDER_NAME >=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThan(String value) {
            addCriterion("SENDER_NAME <", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLessThanOrEqualTo(String value) {
            addCriterion("SENDER_NAME <=", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameLike(String value) {
            addCriterion("SENDER_NAME like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotLike(String value) {
            addCriterion("SENDER_NAME not like", value, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameIn(List<String> values) {
            addCriterion("SENDER_NAME in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotIn(List<String> values) {
            addCriterion("SENDER_NAME not in", values, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameBetween(String value1, String value2) {
            addCriterion("SENDER_NAME between", value1, value2, "senderName");
            return (Criteria) this;
        }

        public Criteria andSenderNameNotBetween(String value1, String value2) {
            addCriterion("SENDER_NAME not between", value1, value2, "senderName");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNull() {
            addCriterion("DISCOUNT_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("DISCOUNT_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(BigDecimal value) {
            addCriterion("DISCOUNT_RATE >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_RATE in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_RATE not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_RATE between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_RATE not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeIsNull() {
            addCriterion("DISCOUNT_FEE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeIsNotNull() {
            addCriterion("DISCOUNT_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_FEE =", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeNotEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_FEE <>", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeGreaterThan(BigDecimal value) {
            addCriterion("DISCOUNT_FEE >", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_FEE >=", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeLessThan(BigDecimal value) {
            addCriterion("DISCOUNT_FEE <", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_FEE <=", value, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_FEE in", values, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeNotIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_FEE not in", values, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_FEE between", value1, value2, "discountFee");
            return (Criteria) this;
        }

        public Criteria andDiscountFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_FEE not between", value1, value2, "discountFee");
            return (Criteria) this;
        }

        public Criteria andIsDoneIsNull() {
            addCriterion("IS_DONE is null");
            return (Criteria) this;
        }

        public Criteria andIsDoneIsNotNull() {
            addCriterion("IS_DONE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDoneEqualTo(String value) {
            addCriterion("IS_DONE =", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotEqualTo(String value) {
            addCriterion("IS_DONE <>", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThan(String value) {
            addCriterion("IS_DONE >", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DONE >=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThan(String value) {
            addCriterion("IS_DONE <", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLessThanOrEqualTo(String value) {
            addCriterion("IS_DONE <=", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneLike(String value) {
            addCriterion("IS_DONE like", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotLike(String value) {
            addCriterion("IS_DONE not like", value, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneIn(List<String> values) {
            addCriterion("IS_DONE in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotIn(List<String> values) {
            addCriterion("IS_DONE not in", values, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneBetween(String value1, String value2) {
            addCriterion("IS_DONE between", value1, value2, "isDone");
            return (Criteria) this;
        }

        public Criteria andIsDoneNotBetween(String value1, String value2) {
            addCriterion("IS_DONE not between", value1, value2, "isDone");
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