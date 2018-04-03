package com.ru.core.pages;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 * 分页返回数据
 */
public class DataguidPageResult<T> {
    /**
     * 符合条件的总记录数
     */

    @JSONField(name="total")
    private long total;

    /**
     * 返回参数，不需要返回的为null
     */
    @JSONField(name="params")
    private Map<String, Object> params;

    /**
     * 返回记录数据
     */
    @JSONField(name="rows")
    private List<T> rows;

    public DataguidPageResult(long total, Map<String, Object> params, List<T> rows) {
        super();
        this.total = total;
        this.params = params;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
