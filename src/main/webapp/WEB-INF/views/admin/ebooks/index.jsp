<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/29
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
    <title>书籍列表</title>
  <link rel="stylesheet" href="${path}/statics/plugins/layui/css/layui.css"  media="all">
</head>
<body>

<div class="demoTable">
 <div class="layui-inline">
    <input class="layui-input" name="keywords" id="keywords" autocomplete="off" placeholder="请输入书籍名称">
  </div>
  <button class="layui-btn" data-type="reload" id="serch" >搜索</button>
</div>
<table class="layui-hide" id="ebookresult"></table>

<script src="${path}/statics/plugins/layui/layui.js" charset="utf-8"></script>
<script src="${path}/statics/jquery-1.9.1.min.js" ></script>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script>
  $(function(){
    initDate();
  });
  //初始化数据
  function initDate(){
    layui.use('table', function(){
      var table = layui.table;
      table.render({
        elem: '#ebookresult'
        ,url:'${path}/admin/ebooks/findEbooksPage'
        ,id:'testReload'
        ,method:'post'
        ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
          layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
          //,curr: 5 //设定初始在第 5 页
          ,groups: 1 //只显示 1 个连续页码
          ,first: false //不显示首页
          ,last: false //不显示尾页
        }
        ,cols: [[
          {field:'name', width:120, title: '书名',align: 'center'}
          ,{field:'intro', width:'18%', title: '简介',align: 'center'}
          ,{field:'type', width:'14%', title: '类型',align: 'center'}
          ,{field:'createtime', width:170, title: '创建时间', align: 'center'}
          ,{field:'updatetime',width:150, title: '更新时间', align: 'center'}
          ,{field:'writer', width:140, title: '作者',align: 'center' }
          ,{field:'right', title: '操作', align: 'center',width:240 ,toolbar:"#barDemo"}
        ]]

      });
      var $ = layui.$, active = {
        reload: function(){
          var keywords = $('#keywords').val();
          //执行重载
          table.reload('testReload', {
            page: {
              curr: 1 //重新从第 1 页开始
            }
            ,where: {
              keywords:keywords
            }
          });
        }
      };

      $('#serch').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
      });

    });
  }

</script>
</body>
</html>
