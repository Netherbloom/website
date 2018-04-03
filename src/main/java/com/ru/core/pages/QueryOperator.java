package com.ru.core.pages;

/**
 * Created by Administrator on 2018/3/29.
 * 枚举类
 */
public enum QueryOperator {

    EQUAL("="), NOT_EQUAL("<>"), LIKE("like"), GT(">"), LT("<"), GE(">="), LE("<="), IS_NULL("IS NULL"), IN("in"), NOT_IN("NOT IN");//枚举字段

    private final String value;

    private QueryOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
