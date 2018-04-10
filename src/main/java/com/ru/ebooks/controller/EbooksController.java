package com.ru.ebooks.controller;

import com.ru.core.controller.BaseController;
import com.ru.core.pages.*;
import com.ru.core.vo.LayResultVO;
import com.ru.ebooks.entity.Ebooks;
import com.ru.ebooks.service.EbooksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */
@Controller
@RequestMapping("/admin/ebooks/**")
public class EbooksController extends BaseController{
    @Autowired
    private EbooksService EbooksService;

    /**
     * 书籍列表
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        return "admin/ebooks/index";
    }

    /**
     * 获取书籍数据
     * @param PageQuery
     * @param keywords
     * @param starttime
     * @param endtime
     * @return
     */
    @RequestMapping("/findEbooksPage")
    @ResponseBody
    public  LayResultVO<List<Ebooks>> findEbooksPage(PageQuery PageQuery,String keywords,String starttime,String endtime){
        if(!StringUtils.isBlank(keywords)){
            PageQuery.addQueryCondition(new QueryCondition("name", QueryOperator.LIKE, "%"+keywords+"%"));
        }
        if(!StringUtils.isBlank(starttime)){
            PageQuery.addQueryCondition(new QueryCondition("updatetime", QueryOperator.GE, starttime+" 00:00:00"));
        }
        if(!StringUtils.isBlank(endtime)){
            PageQuery.addQueryCondition(new QueryCondition("updatetime", QueryOperator.LE, endtime+" 23:59:59"));
        }
        PageQuery.addQuerySort(new QuerySort("updatetime", RecordOrder.DESC));
        DataguidPageResult<Ebooks> page= this.EbooksService.findPageResult(PageQuery);
        Map<String,Object> map=new HashMap<String, Object>();
        return  new LayResultVO<List<Ebooks>>(page.getRows(),page.getTotal());
    }

    /**
     * 同步书籍
     * @return
     */
    @RequestMapping("/syncebooks")
    @ResponseBody
    public Map<String,String> syncebooks(){
        Map<String,String> map=new HashMap<String, String>();
        try {
            this.EbooksService.saveEbooks();
            map.put("code", "200");
            map.put("msg","正在更新");
        }catch (Exception e){
            map.put("code","201");
            map.put("msg","更新失败："+e.getMessage());
        }
        return map;
    }
}
