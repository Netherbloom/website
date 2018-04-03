<%@include file="/taglib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/30
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>fategod</title>
  <link rel="stylesheet" href="${path}/statics/plugins/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="${path}/statics/plugins/layui/css/modules/layer/default/layer.css"  media="all">
</head>
<body>

<form class="layui-form" action="" id="ajax_submit">
<input type="hidden" name="id" value="${userinfo.id==null?null:userinfo.id}"/>
  <div class="layui-form-item">
    <label class="layui-form-label">账号</label>
    <div class="layui-input-inline">
      <input type="text" name="username" value="${userinfo.username}" lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input" />
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">昵称</label>
    <div class="layui-input-inline">
      <input type="text" name="nickname" value="${userinfo.nickname}" lay-verify="title" placeholder="请输入昵称" autocomplete="off" class="layui-input" />
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
  </div>

  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">手机</label>
      <div class="layui-input-inline">
        <input type="tel" name="phone" value="${userinfo.phone}" lay-verify="phone" autocomplete="off" class="layui-input" />
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">邮箱</label>
      <div class="layui-input-inline">
        <input type="text" name="email" value="${userinfo.email}"  lay-verify="email" autocomplete="off" class="layui-input" />
      </div>
    </div>
  </div>

  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">签名</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入内容" class="layui-textarea" name="signature">${userinfo.signature}</textarea>
    </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" onclick="ajax_submit();" >立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
    </div>
</form>
<script src="${path}/statics/plugins/layui/layui.js" charset="utf-8"></script>
<script src="${path}/statics/plugins/layer/layer.js" charset="utf-8"></script>
<script src="${path}/statics/jquery-1.9.1.min.js" ></script>
<script>
  function ajax_submit(){
    $.ajax({
      type: "POST",
      url: "${path}/admin/user/ajax_modif",
      cache: false,
      async: false,//不锁住浏览器
      data: $("#ajax_submit").serialize(),
      success: function(data){
        if(data.code=="200"){
          layer.msg(data.msg, {
            time: 2000//1s后自动关闭
          });
          var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
          parent.layer.close(index);
        }else{
          layer.msg(data.msg, {
            time: 1000//1s后自动关闭
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
