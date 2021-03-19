<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>飒sa青山</title>

    <link rel="stylesheet" type="text/css" href="css/waifu1.css"/>
    <link rel="stylesheet" type="text/css" href="css/flat-ui.min1.css"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="waifu" id="waifu">
    <div class="waifu-tips" style="opacity: 1; font-size: 14px"></div>
    <canvas id="live2d" width="1500" height="1500" class="live2d"></canvas>
    <div class="waifu-tool">
        <span class="fui-home"></span>
        <span class="fui-chat"></span>
        <span class="fui-eye"></span>
        <span class="fui-user"></span>
        <span class="fui-photo"></span>
        <span class="fui-info-circle"></span>
        <span class="fui-cross"></span>
    </div>
</div>
<script src="js/live2d1.js"></script>
<script src="js/waifu-tips1.js"></script>
<script type="text/javascript">initModel()</script>
</body>
</html>