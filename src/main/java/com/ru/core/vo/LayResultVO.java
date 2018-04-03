package com.ru.core.vo;

/**
 * Created by Administrator on 2018/3/29.
 * lay分页数据返回
 */
public class LayResultVO <T>{
    private int code;//返回编码

    private String msg;//描述

    private long count;

    private T data;//数据

    public LayResultVO(){
        this(0, null, null, 0);
    }

    public LayResultVO(T data,long count){
        this(0, null, data, count);
    }

    public LayResultVO(int code, String msg){
        this(code, msg, null, 0);
    }

    public LayResultVO(int code, String msg, T data, long count ){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response [code=" + code + ", msg="
                + msg + ", data=" + data + ", count="
                + count + "]";
    }
}
