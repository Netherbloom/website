package com.ru.core.pages;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 * 分页查询
 */
public class PageQuery {

    /**
     * 当前页，从1开始，值为0时表示不分页
     */
    private int page;

    /**
     * 每页记录数
     */
    private int limit;

    /**
     * 记录偏移数
     */
    private int offset;//从第几条记录开始查询
    /**
     * 结束结束行号 orcle
     */
    private int endNum;

    public int getEndNum() {
        endNum=getLimit()+getOffset();
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    /**
     * 简易参数传递
     */
    private Map<String, Object> queryMap;

    /**
     * 查询条件
     */
    private List<QueryCondition> queryConditions;//字段名

    /**
     * 是否需要知道记录总数
     */
    private boolean needTotalCount = true;

    /**
     * 排序
     */
    private List<QuerySort> querySorts;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Map<String, Object> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    public List<QueryCondition> getQueryConditions() {
        return queryConditions;
    }

    public void setQueryConditions(List<QueryCondition> queryConditions) {
        this.queryConditions = queryConditions;
    }


    public List<QuerySort> getQuerySorts() {
        return querySorts;
    }

    public void setQuerySorts(List<QuerySort> querySorts) {
        this.querySorts = querySorts;
    }


    /**
     * 添加排序
     */
    public void addQuerySort(QuerySort querySort){
        if(this.querySorts == null){
            this.querySorts = new ArrayList<QuerySort>();
        }
        this.querySorts.add(querySort);
    }
    /**
     * 添加 查询条件
     */
    public void addQueryCondition(QueryCondition queryCondition){
        if(this.queryConditions == null){
            this.queryConditions = new ArrayList<QueryCondition>();
        }
        this.queryConditions.add(queryCondition);
    }

    /**
     * 获取记录偏移数
     */
    public int getOffset() {
        if(page > 0){
            return (page - 1) * limit;
        }
        return offset;
    }


    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }

    @Override
    public String toString() {
        return "PageQuery [page=" + page + ", limit=" + limit
                + ", offset=" + offset + ", queryConditions=" + queryConditions
                + ", needTotalCount=" + needTotalCount + ", querySorts="
                + querySorts + "]";
    }


}
