package com.ru.front.controller;

import com.ru.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/4/11.
 */
@Controller
@RequestMapping("/front/**")
public class IndexController  extends BaseController {

    /**
     * 前台首页
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){  return "front/index"; }

}
