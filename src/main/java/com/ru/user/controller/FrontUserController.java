package com.ru.user.controller;

import com.ru.core.controller.BaseController;
import com.ru.user.entity.UserInfo;
import com.ru.user.service.UserInfoService;
import com.ru.util.CommArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/3.
 */
@Controller
@RequestMapping("/front/user/**")
public class FrontUserController extends BaseController {

    @Autowired
    private UserInfoService UserInfoService;

    /**
     * 检查账号是否存在
     * @param username
     * @return
     */
    @RequestMapping("checkAccount")
    @ResponseBody
    public Map<String,String> checkAccount(String username){
        Map<String,String> map=new HashMap<String, String>();
        map.put("code","200");
        map.put("msg","账号可用");
        if(!StringUtils.isBlank(username)){
            UserInfo UserInfo=UserInfoService.getUserInfo(username);
            if(UserInfo!=null && !StringUtils.isBlank(UserInfo.getId())){
                map.put("code","201");
                map.put("msg","账号已存在");
            }
        }else{
            map.put("code","202");
            map.put("msg","账号不能为空");
        }
        return map;
    }

    /**
     * 账号注册
     * @param UserInfo
     * @return
     */
    @RequestMapping("ajax_register")
    @ResponseBody
    public Map<String,String> ajax_register(UserInfo UserInfo){
        Map<String,String> map=new HashMap<String, String>();
        if(UserInfo!=null){
            UserInfo.setId(UUID.randomUUID().toString().replace("-",""));
            UserInfo.setCreatime(new Date());
            UserInfo.setType(CommArray.UserType._普通用户.getValue());
            if(UserInfo.getRegisterSource().equals(CommArray.RegisterSource._邮箱注册.getValue())){
                UserInfo UserInfo2=UserInfoService.getUserByPhoneOrEmail(UserInfo.getPhone(),null);
                if(UserInfo2!=null && !StringUtils.isBlank(UserInfo2.getId())){
                    map.put("code", "202");
                    map.put("msg", "该邮箱号已绑定");
                    return  map;
                }
                UserInfo.setUsername(UserInfo.getEmail());
            }else if(UserInfo.getRegisterSource().equals(CommArray.RegisterSource._手机注册.getValue())){
                UserInfo UserInfo2=UserInfoService.getUserByPhoneOrEmail(UserInfo.getPhone(),null);
                if(UserInfo2!=null && !StringUtils.isBlank(UserInfo2.getId())){
                    map.put("code", "202");
                    map.put("msg", "该手机号已注册");
                    return  map;
                }
                UserInfo.setUsername(UserInfo.getPhone());
            }
            UserInfoService.saveSelective(UserInfo);
            map.put("code", "200");
            map.put("msg", "操作成功");
        }else{
            map.put("code", "202");
            map.put("msg", "参数为空");
        }
        return map;
    }
}
