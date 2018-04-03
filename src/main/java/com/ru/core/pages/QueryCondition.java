package com.ru.core.pages;

/**
 * Created by Administrator on 2018/3/29.
 * 查询条件
 */
public class QueryCondition {
    /**
     * 查询字段，和数据库字段名匹配
     */
    private String queryField;

    /**
     * 查询操作
     */
    private QueryOperator operation;

    /**
     * 查询对应值，注意对于日期类型需要转换
     */
    private Object queryValue;

    public QueryCondition() {

    }

    public QueryCondition(String queryField, QueryOperator operation,
                          Object queryValue) {
        this.queryField = queryField;
        this.operation = operation;
        this.queryValue = queryValue;
    }

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public QueryOperator getOperation() {
        return operation;
    }

    public void setOperation(QueryOperator operation) {
        this.operation = operation;
    }

    public Object getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(Object queryValue) {
        this.queryValue = queryValue;
    }

    @Override
    public String toString() {
        return "QueryCondition [queryField=" + queryField + ", operation="
                + operation + ", queryValue=" + queryValue + "]";
    }
}
