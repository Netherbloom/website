package com.ru.core.pages;

/**
 * Created by Administrator on 2018/3/29.
 */
public enum RecordOrder {
    ASC("ASC"), DESC("DESC");//枚举的字段

    private final String value;

    private RecordOrder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
