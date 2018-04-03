package com.ru.core.service;

import com.ru.core.dao.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  服务层公共类
 * Created by Administrator on 2018/3/20.
 */
public class BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;//自定义的类对象时

    /**
     * 增加一条记录
     * @param object
     * @return
     */
    public boolean add(T object) {
        return this.baseMapper.insert(object) > 0;
    }

    /**
     * 选择性增加一条记录
     * @param object
     * @return
     */
    public boolean saveSelective(T object) {
        return this.baseMapper.insertSelective(object) > 0;
    }

    /**
     * 根据主键删除一条数据
     * @param id
     * @return
     */
    public boolean delete(String id) {
        return this.baseMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 选择性更新一条记录
     * @param object
     * @return
     */
    public boolean update(T object) {
        return this.baseMapper.updateByPrimaryKeySelective(object) > 0;
    }
    /**
     * 根据主键跟新
     * @param object
     * @return
     */
    public boolean updateByPrimaryKey(T object) {
        return this.baseMapper.updateByPrimaryKey(object) > 0;
    }
    /**
     * 根据主键查询一条数据
     * @param id
     * @return
     */
    public T findById(String id) {
        return this.baseMapper.selectByPrimaryKey(id);
    }

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }
}
