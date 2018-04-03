<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/29
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
  <link rel="stylesheet" href="${path}/statics/plugins/layui/css/layui.css"  media="all">
</head>
<body>
<div class="demoTable" style="margin-top: 15px;">
  <div class="layui-inline">
   &nbsp;&nbsp;状态：
    <select class="layui-select" name="type" id="type">
      <option value ="">--请选择--</option>
      <option value ="1">启用</option>
      <option value ="0">禁用</option>
    </select>
  </div>
  <div class="layui-inline">
    &nbsp;&nbsp; 注册来源：
    <select class="layui-select" name="registerSource" id="registerSource">
      <option value ="">--请选择--</option>
      <option value ="admin">后台添加</option>
      <option value ="user">普通注册</option>
    </select>
  </div>
  <div class="layui-inline">
   <input class="layui-input" name="username" id="username" autocomplete="off" placeholder="请输入账号" style="margin-left: 10px;margin-right:10px;">
  </div>
  <button class="layui-btn" data-type="reload" id="serch" >搜索</button>
  <button class="layui-btn"  style="float:right;" onclick="modif()">新增</button>
</div>
<table class="layui-hide" id="userresult"></table>

<script src="${path}/statics/plugins/layui/layui.js" charset="utf-8"></script>
<script src="${path}/statics/jquery-1.9.1.min.js" ></script>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-mini" lay-event="edit" onclick="modif('{{d.id}}')">编辑</a>
</script>
<script type="text/html" id="switchStatus">
  <input type="checkbox" name="status" value="{{d.id}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="sexDemo" {{ d.status == 1 ? 'checked' : '' }} />
</script>
<script>
  $(function(){
    initDate();
  });
  //初始化数据
  function initDate(){
    layui.use('table', function(){
      var table = layui.table ,form = layui.form;;
      table.render({
        elem: '#userresult'
        ,url:'${path}/admin/user/findUserPage'
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
          {field:'username', width:120, title: '账号',align: 'center'}
          ,{field:'nickname', width:'18%', title: '昵称',align: 'center'}
          ,{field:'type', width:'14%', title: '类型',align: 'center'}
          ,{field:'status',width:150, title: '状态', align: 'center', templet: '#switchStatus', unresize: true}
          ,{field:'registerSource',width:150, title: '注册来源', align: 'center'}
          ,{field:'createtime', width:170, title: '注册时间', align: 'center'}
          ,{field:'right', title: '操作', align: 'center',width:240 ,toolbar:"#barDemo"}
        ]]
      });

      //监听性别操作
      form.on('switch(sexDemo)', function(obj){
       // layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        updateStatus(this.value);

      });
      var $ = layui.$, active = {
        reload: function(){
          var username = $('#username').val();
          var type = $('#type').val();
          var registerSource = $('#registerSource').val();
          //执行重载
          table.reload('testReload', {
            page: {
              curr: 1 //重新从第 1 页开始
            }
            ,where: {
              account:username,
              registerSource:registerSource,
              type:type
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
function modif(id){
  layer.open({
    type: 2,
    area: ['700px', '450px'],
    fixed: false, //不固定
    maxmin: true,
    content: '${path}/admin/user/edit?id='+id
  });
}

  /**
  *更新用户状态
* @param id
   */
function updateStatus(id){
    $.ajax({
      type: "POST",
      url: "${path}/admin/user/ajax_updateStatus",
      cache: false,
      async: false,//不锁住浏览器
      data: {id:id},
      success: function(data){
        if(data.code=="200"){
          layer.msg(data.msg, {
            time: 2000//1s后自动关闭
          });
        }else{
          layer.msg(data.msg, {
            time: 2000//1s后自动关闭
          });
        }
      },
      error:function(XMLHttpRequest, textStatus){
      }
    });
}
</script>
</body>
</html>
