<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号注册</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${path}/statics/plugins/layui/css/layui.css"  media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>用户注册</legend>
</fieldset>

<div class="layui-tab">
  <ul class="layui-tab-title">
    <li class="layui-this">手机注册</li>
    <li>邮箱注册</li>

  </ul>
  <!--手机注册-->
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      <form class="layui-form" action="" id="phonefrom">
        <input type="hidden" name="registerSource" value="phone">
        <input type="hidden" name="status" value="1">
        <div class="layui-form-item">
          <label class="layui-form-label"><span style="color: red">*</span>用户账号:</label>
          <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required|number" placeholder="请输入数字账号" autocomplete="off" class="layui-input" style="width: 300px;">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label"><span style="color: red">*</span>密码:</label>
          <div class="layui-input-inline">
            <input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
          </div>
          <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">昵称:</label>
          <div class="layui-input-block">
            <input type="text" name="nickname" lay-verify="title" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 300px;">
          </div>
        </div>

        <div class="layui-form-item">
          <label class="layui-form-label"><span style="color: red">*</span>手机号:</label>
          <div class="layui-input-inline">
            <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" >
          </div>
          <%--<div class="layui-form-mid layui-word-aux"><input type="button" value="获取验证码" style="width: 80px;height: 30px;"> </div>--%>
        </div>
<%--        <div class="layui-form-item">
          <label class="layui-form-label"><span style="color: red">*</span>验证码:</label>
          <div class="layui-input-inline">
            <input type="text" name="code" lay-verify="number" autocomplete="off" class="layui-input" >
          </div>
        </div>--%>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button class="layui-btn" lay-submit  lay-filter="from1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
          </div>
        </div>
      </form>
    </div>
    <!--邮箱注册-->
    <div class="layui-tab-item">
      <div class="layui-tab-item layui-show">
        <form class="layui-form" action="" id="emailfrom">
          <input type="hidden" name="registerSource" value="email">
          <input type="hidden" name="status" value="0">
          <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span>用户账号:</label>
            <div class="layui-input-block">
              <input type="text" name="username" lay-verify="required|number" placeholder="请输入数字账号" autocomplete="off" class="layui-input" style="width: 300px;" onblur="checkAccount(this.value)">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span>密码:</label>
            <div class="layui-input-inline">
              <input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">昵称:</label>
            <div class="layui-input-block">
              <input type="text" name="nickname" lay-verify="title" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 300px;">
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red">*</span>邮箱:</label>
            <div class="layui-input-block">
              <input type="text" name="phone" lay-verify="required|email" autocomplete="off" class="layui-input" style="width: 300px;">
            </div>
          </div>

          <div class="layui-form-item">
            <div class="layui-input-block">
              <button class="layui-btn" lay-submit  lay-filter="from2">立即提交</button>
              <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${path }/statics/jquery-1.9.1.min.js"></script>
<script src="${path}/statics/plugins/layui/layui.js" charset="utf-8"></script>
<script>
  var acount=true;
  layui.use('form', function(){
    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    form.render();
    //监听提交-手机号注册
    form.on('submit(from1)', function(data){
      var flag=false;
      submit("phonefrom",flag);
      return flag;
    });

    //监听提交-邮箱注册
    form.on('submit(from2)', function(data){
      var flag=false;
      submit("emailfrom",flag);
      return flag;
    });

  });
  layui.use('element', function(){
    var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    //触发事件
    var active = {
    tabChange: function(){
        //切换到指定Tab项
        element.tabChange('demo', '22'); //切换到：用户管理
      }
    };

  });

  <!--表单提交-->
  function  submit(data,flag){
    var id="#"+data;
    $.ajax({
      type: "POST",
      url: "${path}/front/user/ajax_register",
      cache: false,
      async: true,//不锁住浏览器
      data: $(id).serialize(),
      success: function(data){
        if(data.code=="200"){
          layer.msg(data.msg);
          window.location.href="${path}/center/login";
          flag=true;
        }else{
          layer.msg(data.msg);
          flag=false;
        }
      },
      error:function(XMLHttpRequest, textStatus){
      }
    });
    return false;
  }

 <!--验证账号-->
  function checkAccount(data){
    if(data=='' || data==null){
      return false;
    }
    $.ajax({
      url : "${path}/front/user/checkAccount",
      type : "POST",
      contentType: "application/json;charset=utf-8",
      data : {username:data},
      dataType : "json",
      success : function(result) {
        if (result != "200") {
          acount=false;
          layer.msg(result.msg);
        }else{
          acount=true;
          layer.msg(result.msg);
        }
      },
      error:function(msg){
        $(".notice").html('Error:'+msg);
      }
    });
  }
</script>
</body>
</html>
