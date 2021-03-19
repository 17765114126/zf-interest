<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html lang="en">
<head>
    <script src="../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function setVideo(obj) {//用于视频上传，返回地址
            var f = $(obj).val();
            if (f == null || f == undefined || f == '') {
                return false;
            }
            if (!/\.(?:mp4|flv|ppt|avi|mpg|wmv|3gp|mov|asf|asx|vob|wmv9|rm|rmvb)$/.test(f)) {
                alert("文件类型必须是视频");
                $(obj).val('');
                return false;
            }
            var data = new FormData();
            $.each($(obj)[0].files, function (i, file) {
                data.append('file', file);
            });
            $.ajax({
                type: "POST",
                url: "/fileUpload/upVideo.html",
                data: data,
                cache: false,
                contentType: false,
                processData: false,
                dataType: "json",
                success: function (suc) {
                    if (suc.code == 0) {
                        alert("操作成功")
                        $("#videoUrl").val(suc.model.path);//将地址存储好
                        $("#videoShow").attr("src", suc.model.path);//显示视频
                        $("#thumburlShow").attr("original", suc.model.path);
                    } else {
                        alert("操作失败" + suc.message)
                        $(obj).val('');
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("上传失败，请检查网络后重试");
                    $(obj).val('');
                }
            });
        }
    </script>
</head>
<body style="background:url(../../img/微信图片_20190815090725.png) ;background-size:cover; ">
<table align='center' border='1' cellspacing='0'>
    ${s}
    <tr>
        <td>age==================</td>
        <td>name+++++++++++++++++</td>
    </tr>
    <tr>
        <td>${student.age}</td>
        <td>${student.name}</td>
    </tr>
    <div class="row" id="video" style="display: contents">
        <label class="personattr" id="productVideo">上传视频:</label>
        <input type="hidden" name="shareVideo" id="videoUrl"/>
        <a href="javascript:void(0);" class="btn_addPic" style="margin-left: 40px;">
            <span id="videoTitle">上传视频</span>
            <input id="selectedLogoVideoId" class="selectedLogoImgId" type="file" name="videoFile"
                   onchange="setVideo(this)"
                   style="margin-left:1px;"/>
        </a>
        <div id="logoVideoDiv" style="margin-left:140px; height: 140px;">
            <video width="200px" height="120px"
                   style="margin-top:10px;text-align:center;margin-left:15px;display: block"
                   id="videoShow" controls src='' name="logoVideoId"/>
        </div>
    </div>
</table>
</body>
</html>


<!--live2d-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>飒sa青山</title>
    <link rel="stylesheet" type="text/css" href="../../statics/css/waifu1.css"/>
    <link rel="stylesheet" type="text/css" href="../../statics/css/flat-ui.min1.css"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="waifu" id="waifu">
    <div class="waifu-tips" style="opacity: 1; font-size: 14px"></div>
    <canvas id="live2d" width="280" height="430" class="live2d"></canvas>
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
<script src="../../statics/js/live2d1.js"></script>
<script src="../../statics/js/waifu-tips1.js"></script>
<%--<script src="../statics/js/live2d.js"></script>
<script src="../statics/js/waifu-tips.js"></script>--%>
<script type="text/javascript">initModel()</script>
</body>
</html>
<!--live2dend-->
