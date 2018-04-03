package com.ru.filter;

import com.ru.user.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/28.
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();

        UserInfo user= (UserInfo)session.getAttribute("currentUser");

        if(user != null && !StringUtils.isBlank(user.getId())){
            return true;
        }else{  //不符合条件的，跳转到登录界面
            String str=(String) request.getSession().getServletContext().getAttribute("path");
            response.sendRedirect(str+"/center/login");
            return false;
        }
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
