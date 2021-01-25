package com.narc.alibaba.service.alimama.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlitThreadCompetitionInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlitThreadCompetitionInfoExample() {
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

        public Criteria andThreadIdIsNull() {
            addCriterion("THREAD_ID is null");
            return (Criteria) this;
        }

        public Criteria andThreadIdIsNotNull() {
            addCriterion("THREAD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andThreadIdEqualTo(Integer value) {
            addCriterion("THREAD_ID =", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdNotEqualTo(Integer value) {
            addCriterion("THREAD_ID <>", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdGreaterThan(Integer value) {
            addCriterion("THREAD_ID >", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("THREAD_ID >=", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdLessThan(Integer value) {
            addCriterion("THREAD_ID <", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdLessThanOrEqualTo(Integer value) {
            addCriterion("THREAD_ID <=", value, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdIn(List<Integer> values) {
            addCriterion("THREAD_ID in", values, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdNotIn(List<Integer> values) {
            addCriterion("THREAD_ID not in", values, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_ID between", value1, value2, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadIdNotBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_ID not between", value1, value2, "threadId");
            return (Criteria) this;
        }

        public Criteria andThreadNameIsNull() {
            addCriterion("THREAD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andThreadNameIsNotNull() {
            addCriterion("THREAD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andThreadNameEqualTo(String value) {
            addCriterion("THREAD_NAME =", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotEqualTo(String value) {
            addCriterion("THREAD_NAME <>", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameGreaterThan(String value) {
            addCriterion("THREAD_NAME >", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameGreaterThanOrEqualTo(String value) {
            addCriterion("THREAD_NAME >=", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLessThan(String value) {
            addCriterion("THREAD_NAME <", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLessThanOrEqualTo(String value) {
            addCriterion("THREAD_NAME <=", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLike(String value) {
            addCriterion("THREAD_NAME like", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotLike(String value) {
            addCriterion("THREAD_NAME not like", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameIn(List<String> values) {
            addCriterion("THREAD_NAME in", values, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotIn(List<String> values) {
            addCriterion("THREAD_NAME not in", values, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameBetween(String value1, String value2) {
            addCriterion("THREAD_NAME between", value1, value2, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotBetween(String value1, String value2) {
            addCriterion("THREAD_NAME not between", value1, value2, "threadName");
            return (Criteria) this;
        }

        public Criteria andNowIpIsNull() {
            addCriterion("NOW_IP is null");
            return (Criteria) this;
        }

        public Criteria andNowIpIsNotNull() {
            addCriterion("NOW_IP is not null");
            return (Criteria) this;
        }

        public Criteria andNowIpEqualTo(String value) {
            addCriterion("NOW_IP =", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpNotEqualTo(String value) {
            addCriterion("NOW_IP <>", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpGreaterThan(String value) {
            addCriterion("NOW_IP >", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpGreaterThanOrEqualTo(String value) {
            addCriterion("NOW_IP >=", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpLessThan(String value) {
            addCriterion("NOW_IP <", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpLessThanOrEqualTo(String value) {
            addCriterion("NOW_IP <=", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpLike(String value) {
            addCriterion("NOW_IP like", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpNotLike(String value) {
            addCriterion("NOW_IP not like", value, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpIn(List<String> values) {
            addCriterion("NOW_IP in", values, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpNotIn(List<String> values) {
            addCriterion("NOW_IP not in", values, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpBetween(String value1, String value2) {
            addCriterion("NOW_IP between", value1, value2, "nowIp");
            return (Criteria) this;
        }

        public Criteria andNowIpNotBetween(String value1, String value2) {
            addCriterion("NOW_IP not between", value1, value2, "nowIp");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIsNull() {
            addCriterion("LAST_RUN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIsNotNull() {
            addCriterion("LAST_RUN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeEqualTo(Date value) {
            addCriterion("LAST_RUN_TIME =", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotEqualTo(Date value) {
            addCriterion("LAST_RUN_TIME <>", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeGreaterThan(Date value) {
            addCriterion("LAST_RUN_TIME >", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_RUN_TIME >=", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeLessThan(Date value) {
            addCriterion("LAST_RUN_TIME <", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_RUN_TIME <=", value, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeIn(List<Date> values) {
            addCriterion("LAST_RUN_TIME in", values, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotIn(List<Date> values) {
            addCriterion("LAST_RUN_TIME not in", values, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_RUN_TIME between", value1, value2, "lastRunTime");
            return (Criteria) this;
        }

        public Criteria andLastRunTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_RUN_TIME not between", value1, value2, "lastRunTime");
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