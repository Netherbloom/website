package com.ru.core.dao;

/**
 * 数据层接口
 * Created by Administrator on 2018/3/20.
 */
public interface BaseMapper<T>{

    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增加一条记录
     * @param record
     * @return
     */
    int insert(T record);//增加一条记录

    /**
     * 选择性增加一条记录
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据主键查询一条记录
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);

    /**
     * 选择性根据主键修改一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键修改一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

}
