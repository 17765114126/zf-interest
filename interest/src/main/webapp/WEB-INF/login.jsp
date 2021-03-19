<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>放开会报jar包冲突--%>
<html>
<head>
    <title>系统登录</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="author" content="Dreamer-1">
    <meta name="renderer" content="webkit" />
    <link href="/css/login/login.css" rel="stylesheet">
    <script src="../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <%--<script type="text/javascript" src="/js/login/md5/md5.js"></script>--%>
</head>

<body style="background:url(../img/57dcf45e0e1d12fc336e0aa3c2638368_hd.jpg) ;background-size: revert; ">
<form name="form1" method="post" action="/login" id="form1" onsubmit="return checkLogin();">
    <div id="main" >
        <div class="wrapper">
            <div class="login-hd"></div>
            <div class="login-body" style="text-align: center">
                <div class="logo">
                    <span class="icon-logo"></span>
                </div>
                <div class="box">
                    <div class="login-item">
                        <span class="icon-user"></span>
                        <input name="username" type="text" id="username" class="login-input" tabindex="1" maxlength="50" placeholder="请输入用户名" />
                    </div>
                    <div class="login-item mt35">
                        <span class="icon-pwd"></span>
                        <input type="password" id="password" name="password"  class="login-input" tabindex="2" maxlength="32" placeholder="请输入密码"/>
                    </div>
                    <div class="login-forget">
                        <a href="#">注册账号</a>
                        <a href="#">忘记密码</a>
                    </div>
                    <input type="submit" name="Logon" value="登录" id="Logon" tabindex="3" class="login-btn" />
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    // onsubmit值为true时，提交表单，否则显示错误信息
    // 生成用户名+密码组合的md5值，并设置传给后端的密码为该md5值
    function checkLogin() {
        var name = $("#username").val().toLowerCase();
        var pwd = $("#password").val().toLowerCase();
        if(name.trim()=="" || pwd.trim()=="") {
            alert("请输入用户名和密码");
            return false;
        }
        //var md5info = name + pwd;
        //$('#hidePwd').val(md5(md5info));
        //$("#password").val();
        return true;
    }
</script>
</body>
</html>
