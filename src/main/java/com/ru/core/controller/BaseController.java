package com.ru.core.controller;

import com.ru.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/28.
 */
public class BaseController {
    /**
     * 获取Context对象
     * @param request
     * @param attrName
     * @return
     */
    protected Object getContextAttr(HttpServletRequest request, String attrName) {
        //request.getServletContext() servlet3.0添加的方法，如果直接获取会报错
        return request.getSession().getServletContext().getAttribute(attrName);
    }
    /**
     * 获取项目路径
     * @param request
     * @return
     */
    protected String getContextPath(HttpServletRequest request) {
        return (String) getContextAttr(request, "path");
    }

    /**
     * 获取session中登录用户
     * @param session
     * @return
     */
    protected UserInfo getusUserinfoBySession(HttpSession session) {
        return  (UserInfo)session.getAttribute("currentUser");
    }
}
