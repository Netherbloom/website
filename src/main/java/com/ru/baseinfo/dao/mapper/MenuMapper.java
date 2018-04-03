package com.ru.baseinfo.dao.mapper;

import com.ru.baseinfo.entity.Menu;
import com.ru.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu>{

    /**
     * 获取菜单
     * @param parentid
     * @param level
     */
    public List<Menu> getmenu(@Param("parentid")String parentid,@Param("level")int level);

    /**
     * 查询所有目录
     * @return
     */
    public List<Menu> selectAll();
}