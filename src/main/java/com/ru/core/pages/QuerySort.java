package com.ru.core.pages;

/**
 * Created by Administrator on 2018/3/29.
 * 查询排序
 */
public class QuerySort {
    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序
     */
    private RecordOrder recordOrder;


    public QuerySort(){

    }

    public QuerySort(String sortField, RecordOrder recordOrder) {
        super();
        this.sortField = sortField;
        this.recordOrder = recordOrder;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public RecordOrder getRecordOrder() {
        return recordOrder;
    }

    public void setRecordOrder(RecordOrder recordOrder) {
        this.recordOrder = recordOrder;
    }

    @Override
    public String toString() {
        return "QuerySort [sortField=" + sortField + ", recordOrder="
                + recordOrder + "]";
    }
}
