<%--
  Created by IntelliJ IDEA.
  User: lpad
  Date: 2019/2/27
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>小码聪聪旅游网-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入angularJS文件-->
    <script src="js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
    <style type="text/css">
        #loginForm .error{
            border: 2px red solid;
        }
    </style>
</head>

<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg" style="background: url(images/login_bg.png);height: 532px;">

    </div>
    <div class="login-box">
        <div class="title">
            <img src="images/login_logo.png" alt="">
            <span>欢迎登录</span>
        </div>
        <div class="login_inner">

            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger" >${msg}</div>
            <form id="loginForm"  method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input id="username" name="username" type="text" placeholder="请输入账号" autocomplete="off" onblur="yzyhm()">
                <input id="password" name="password" type="text" placeholder="请输入密码" autocomplete="off" onblur="yzpas()">
                <div class="verify">
                    <input id="check" name="check" type="text" placeholder="请输入验证码" autocomplete="off" onblur="yzyzm()">
                    <span><img id="vcode" src="/checkCodeServlet" alt="" onclick="refreshCode()"></span>
                </div>
                <div class="submit_btn">
                    <button type="button" onclick="loginyz()">登录</button>
                    <div class="auto_login">
                        <input type="checkbox" name="zdlogin" class="checkbox" >
                        <span>自动登录</span>
                    </div>
                </div>
            </form>
            <div class="reg">没有账户？<a href="register.jsp">立即注册</a></div>
        </div>
    </div>
</section>
<!--引入尾部-->
<div id="footer"></div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--<script src="js/jquery-1.11.0.min.js"></script>--%>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
<script>
    function refreshCode() {
        var element = document.getElementById("vcode");
        element.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
    }
    //验证用户名
    function yzyhm() {
        var $username = $("#username");
        if(typeof $username.val() == "undefined" || $username.val() == null || $username.val() == ""){
            $username.addClass("error");
            $username.attr("placeholder","不能为空");
        }else {
            $username.removeClass("error");
            $username.attr("placeholder","");
            return true;
        }
        return false;
    }

    function yzpas() {
        var $password = $("#password");
        if(typeof $password.val() == "undefined" || $password.val() == null || $password.val() == ""){
            $password.addClass("error");
            $password.attr("placeholder","不能为空");
        }else {
            $password.removeClass("error");
            $password.attr("placeholder","");
            return true;
        }
        return false;
    }
    function yzyzm() {
        var $check = $("#check");
        if(typeof $check.val() == "undefined" || $check.val() == null || $check.val() == ""){
            $check.addClass("error");
            $check.attr("placeholder","不能为空");
        }else {
            $check.removeClass("error");
            $check.attr("placeholder","");
            return true;
        }
        return false;
    }

    function loginyz() {
        if(yzyhm() && yzpas()&&yzyzm()){
         $.ajax({
             url:"/user/login",
             data:$("#loginForm").serialize(),
             type:"post",
             dataType:"json",
             success:function (data) {
                 console.log(data);
                 refreshCode();
                 if(data.flag){
                     window.location.href ="index.jsp";
                 }else {
                     $("#errorMsg").html(data.msg);
                 }

             }
         })
        }
    }
</script>
</body>

</html>
