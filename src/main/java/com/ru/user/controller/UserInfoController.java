package com.ru.user.controller;

import com.ru.core.controller.BaseController;
import com.ru.core.pages.*;
import com.ru.core.vo.LayResultVO;
import com.ru.user.entity.UserInfo;
import com.ru.user.service.UserInfoService;
import com.ru.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2018/3/28.
 *  用户管理
 */
@Controller
@RequestMapping(value = "/admin/user/**")
public class UserInfoController  extends BaseController{

    @Autowired
    private UserInfoService UserInfoService;

    @RequestMapping("index")
    public String index(Model Model){
        return "admin/user/index";
    }

    /**
     * 获取用户列表
     * @param PageQuery
     * @param account
     * @param registerSource
     * @param type
     * @return
     */
    @RequestMapping("/findUserPage")
    @ResponseBody
    public LayResultVO<List<UserInfo>> findUserPage(PageQuery PageQuery,String account,String registerSource,Integer type){
        if(!StringUtils.isBlank(account)){
            PageQuery.addQueryCondition(new QueryCondition("username", QueryOperator.LIKE, "%"+account+"%"));
        }
        if(!StringUtils.isBlank(registerSource)){
            PageQuery.addQueryCondition(new QueryCondition("register_source", QueryOperator.EQUAL, registerSource));
        }
        if(type!=null && type>1){
            PageQuery.addQueryCondition(new QueryCondition("type", QueryOperator.EQUAL, type));
        }
        PageQuery.addQueryCondition(new QueryCondition("type", QueryOperator.GT, 1));//超级管理员不显示
        PageQuery.addQuerySort(new QuerySort("creatime", RecordOrder.DESC));
        DataguidPageResult<UserInfo> page= this.UserInfoService.findPageResult(PageQuery);
        Map<String,Object> map=new HashMap<String, Object>();
        return  new LayResultVO<List<UserInfo>>(page.getRows(),page.getTotal());
    }

    /**
     * 进入修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("edit")
    public String edit(Model model,String id){
        UserInfo user=new UserInfo();
        if(!StringUtils.isBlank(id)){
            user=UserInfoService.findById(id);
        }
        model.addAttribute("userinfo",user);
        return "admin/user/edit";
    }

    /**
     * 新增修改
     * @param userInfo
     * @return
     */
    @RequestMapping("ajax_modif")
    @ResponseBody
    public Map<String,String> ajax_modif(UserInfo userInfo){
        Map<String,String> map=new HashMap<String, String>();
        map.put("code","201");
        map.put("msg","操作失败");
        if(userInfo!=null && !StringUtils.isBlank(userInfo.getUsername())){
            try {
                if(!StringUtils.isBlank( userInfo.getId())){//修改
                    if(!StringUtils.isBlank(userInfo.getPassword()) ){
                        userInfo.setPassword(MD5Util.MD5Encode(userInfo.getPassword()));
                    }
                    UserInfoService.update(userInfo);
                }else{//新增
                    userInfo.setId(UUID.randomUUID().toString().replace("-", ""));
                    userInfo.setCreatime(new Date());
                    userInfo.setStatus(1);
                    userInfo.setType(2);
                    userInfo.setRegisterSource("admin");
                    if(!StringUtils.isBlank(userInfo.getPassword())){
                        userInfo.setPassword(MD5Util.MD5Encode(userInfo.getPassword()));
                    }else{
                        userInfo.setPassword(MD5Util.MD5Encode("123456"));
                    }
                    UserInfoService.saveSelective(userInfo);
                }
                map.put("code","200");
                map.put("msg","操作成功");
            }catch (Exception e){
                map.put("msg",e.getMessage());
            }

        }
        return map;
    }

    /**
     * 更新用户状态
     * @param id
     * @return
     */
    @RequestMapping("ajax_updateStatus")
    @ResponseBody
    public Map<String,String> ajax_updateStatus(String id){
        Map<String,String> map=new HashMap<String, String>();
        map.put("code","201");
        map.put("msg","操作失败");
        if(!StringUtils.isBlank(id)){
            try {
                UserInfo user=UserInfoService.findById(id);
                user.setStatus(1-user.getStatus());
                UserInfoService.update(user);
                map.put("code","200");
                map.put("msg","操作成功");
            }catch (Exception e){
                map.put("msg",e.getMessage());
            }

        }
        return map;
    }
}
