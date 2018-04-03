package com.ru.baseinfo.controller;

import com.ru.baseinfo.entity.Menu;
import com.ru.baseinfo.service.MenuService;
import com.ru.core.controller.BaseController;
import com.ru.core.vo.ResultVo;
import com.ru.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2018/3/28.
 */
@Controller
@RequestMapping("/admin/menu/**")
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("index")
    public String index(){
      return "admin/menu/index";
   }

    /**
     * 获取菜单
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getmenu")
    @ResponseBody
    public Map<String, Object> getmenu(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String parentid=request.getParameter("parentid");
        String level=request.getParameter("level");
        List<Map<String, Object>> list=menuService.getMenus(parentid, Integer.parseInt(level));
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code", 200);
        map.put("data", list);
        return map;
    }

    @RequestMapping("menu")
    public String menu(){
        return "admin/menu/menu";
    }

    @RequestMapping("getmenulist")
    @ResponseBody
    public List<TreeNode> getmenulist(HttpSession session, HttpServletResponse response) throws Exception{
        List<TreeNode> list=new ArrayList<TreeNode>();
        TreeNode firstztree = new TreeNode();
        firstztree.setId("0");
        firstztree.setName("全部");
        firstztree.setIsParent(true);
        firstztree.setOpen(true);
        list.add(firstztree);
        List<Menu> menus=menuService.findAll();
        if(null!=menus && menus.size()>0){
            for(Menu menu:menus){
                TreeNode ztree = new TreeNode();
                ztree.setId(menu.getId());
                ztree.setName(menu.getName());
                if(StringUtils.isBlank(menu.getParentid())){
                    ztree.setpId("0");
                    ztree.setIsParent(true);
                }else{
                    ztree.setpId(menu.getParentid());
                }
                list.add(ztree);
            }
        }
        return list;
    }

    /**
     * 菜单修改
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("menuedit")
    @ResponseBody
    public Menu menuedit(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String id=request.getParameter("id");
        String type=request.getParameter("type");
        Menu  menu=menuService.findById(id);
        if(Integer.parseInt(type)==0){//新增
            int level=menu==null?1:menu.getLevel();
            menu=new Menu();
            if(!id.equals("0")){
                level+=1;
            }
            menu.setParentid(id);
            menu.setLevel(level);
        }
        if(StringUtils.isBlank(menu.getName())){
            menu.setName(" ");
        }
        if(StringUtils.isBlank(menu.getCode())){
            menu.setCode(" ");
        }
        if(StringUtils.isBlank(menu.getIco())){
            menu.setIco(" ");
        }
        if(StringUtils.isBlank(menu.getUrl())){
            menu.setUrl(" ");
        }
         return menu;
    }

    /**
     * 新增修改菜单
     * @param menu
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("ajax_addmenu")
    @ResponseBody
    public ResultVo ajax_add(Menu menu,HttpServletRequest request,HttpServletResponse response) throws Exception{
        ResultVo vo=new ResultVo();
        try {
            if(menu.getLevel()==1 && StringUtils.isBlank(menu.getId())){
                menu.setParentid(null);
            }
            if(menu.getName()!=null){
                menu.setName(menu.getName().trim());
            }
            if(menu.getCode()!=null){
                menu.setCode(menu.getCode().trim());
            }
            if(menu.getUrl()!=null){
                menu.setUrl(menu.getUrl().trim());
            }
		/*	if(request instanceof MultipartHttpServletRequest){
				MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("ico");
				if(file!=null){
				String originalFileName=file.getOriginalFilename(); //文件名 带后缀
				//后缀名
				String suffix=originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
				String filename=DateTimeUtil.getCurrentTime()+suffix;
				String filepath=Const.UPLOAD_FLODER_TMP+filename;
				file.transferTo(new File(filepath));
				FileInputStream fi=null;
				FileChannel in=null;
				fi=new FileInputStream(new File(filepath));
				in=fi.getChannel();
				fi.close();
				in.close();
				activity.setAttchpath(filename);
				activity.setAttchname(originalFileName);
			}
			}*/
            if(menu.getId()!=null){
                menuService.update(menu);
            }else{
                menu.setId(UUID.randomUUID().toString().replace("-",""));
                menuService.saveSelective(menu);
            }

            vo=new ResultVo(true,"操作成功");
        } catch (Exception e) {
            vo=new ResultVo(false,"操作失败");
        }
        return  vo;
    }

    /**
     * 删除菜单
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("ajax_delmenu")
    @ResponseBody
    public ResultVo ajax_delmenu(String id) throws Exception{
        ResultVo vo=new ResultVo();
        try {
            Menu menu=menuService.findById(id);
            menuService.delete(menu.getId());
            vo=new ResultVo(true,"操作成功");
        } catch (Exception e) {
            vo=new ResultVo(false,"操作失败");
        }
        return  vo;
    }
}
