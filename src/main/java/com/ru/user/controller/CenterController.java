package com.ru.user.controller;

import com.ru.core.controller.BaseController;
import com.ru.core.vo.ResultVo;
import com.ru.user.entity.UserInfo;
import com.ru.user.entity.UserLog;
import com.ru.user.service.UserInfoService;
import com.ru.user.service.UserLogService;
import com.ru.util.CommArray;
import com.ru.util.MD5Util;
import com.ru.util.MethodUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/28.
 */
@Controller
@RequestMapping("/center/**")
public class CenterController extends BaseController{

    @Autowired
    private UserInfoService userInfoServices;

    @Autowired
    private UserLogService UserLogService;

    /**
     * 登录页面
     * @param model
     */
    @RequestMapping("login")
    public void login(Model model){
    }

    /**
     * 登录
     * @param session
     * @param response
     * @param request
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("ajax_login")
    @ResponseBody
    public  Map<String, String>  ajax_login(HttpSession session,HttpServletResponse response,HttpServletRequest request,String username,String password) throws Exception{
        Map<String, String> map=new HashMap<String, String>();
        if(StringUtils.isBlank(username)|| StringUtils.isBlank(password)){
            map.put("code", "201");
            map.put("msg", "账号或密码不能为空");
        }
        password= MD5Util.MD5Encode(password);
        UserInfo user=userInfoServices.getUserInfo(username);
        if(user!=null && user.getStatus()!=0 && user.getPassword().equals(password)){
            //添加登录日志
            UserLog log=new UserLog();
            String ip= MethodUtil.getIp(request);
            log.setId(UUID.randomUUID().toString().replace("-",""));
            log.setUserid(user.getId());
            log.setLoginip(ip);
            log.setType(CommArray.UserLogType._登录.getValue());
            log.setCreatime(new Date());
            log.setBrowser(MethodUtil.getBrowserInfo(request).get("browser"));
            log.setUrl(MethodUtil.getBrowserInfo(request).get("url"));
            UserLogService.saveSelective(log);
            session.setAttribute("currentUser", user);
            map.put("code", "200");
            map.put("type",user.getType()+"");
        }else{
            if(user.getStatus()==0){
                map.put("code", "201");
                map.put("msg","该账号已被禁用");
            }else{
                map.put("code", "201");
                map.put("msg", "账号或密码错误");
            }
        }
       return  map;
    }

    /**
     * 登出
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("logout")
    @ResponseBody
    public ResultVo logout(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
        UserInfo user=(UserInfo)session.getAttribute("currentUser");
        if(user!=null){
            UserLog log=new UserLog();
            String ip= MethodUtil.getIp(request);
            log.setId(UUID.randomUUID().toString().replace("-",""));
            log.setUserid(user.getId());
            log.setLoginip(ip);
            log.setType(CommArray.UserLogType._登出.getValue());
            log.setCreatime(new Date());
            log.setBrowser(MethodUtil.getBrowserInfo(request).get("browser"));
            log.setUrl(MethodUtil.getBrowserInfo(request).get("url"));
            UserLogService.saveSelective(log);
            //清除Session
            session.invalidate();
        }
        ResultVo vo=new ResultVo();
        return  vo;
    }

}
