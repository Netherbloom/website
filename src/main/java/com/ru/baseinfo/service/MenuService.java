package com.ru.baseinfo.service;

import com.ru.baseinfo.dao.mapper.MenuMapper;
import com.ru.baseinfo.entity.Menu;
import com.ru.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 *  菜单服务
 */
@Service
public class MenuService extends BaseService<Menu>{
    @Autowired
    private MenuMapper menuDao;

    /**
     * 获取菜单
     * @param parentid
     * @param level
     * @return
     */
    public List<Map<String, Object>> getMenus(String parentid,int level) {
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        List<Menu> list=menuDao.getmenu(parentid, level);
        if(null!=list &&list.size()>0){
            for (Menu menu : list) {
                Map<String, Object> map=new HashMap<String, Object>();
                result.add(map);
                map.put("name", menu.getName());
                map.put("id", menu.getId());
                map.put("ico", menu.getIco());
                map.put("url", menu.getUrl());
                if(level>1){
                    List<Menu> child=menuDao.getmenu(menu.getId(), level+1);
                    map.put("list", child);
                }
            }
        }
        return result;
    }

    /**
     * 查询所有目录
     * @return
     */
    public List<Menu> findAll() {
        return menuDao.selectAll();
    }
}
