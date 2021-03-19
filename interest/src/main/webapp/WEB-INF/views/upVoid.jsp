<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="../../plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script type="text/javascript">
        function setVideo(obj){//用于视频上传，返回地址
            var f=$(obj).val();
            if(f == null || f ==undefined || f == ''){
                return false;
            }
            if(!/\.(?:mp4|flv|ppt|avi|mpg|wmv|3gp|mov|asf|asx|vob|wmv9|rm|rmvb)$/.test(f)){
                alert("文件类型必须是视频");
                $(obj).val('');
                return false;
            }
            var data = new FormData();
            $.each($(obj)[0].files,function(i,file){
                data.append('file', file);
            });
            $.ajax({
                type: "POST",
                url: "/fileUpload/upVideo.html",
                data: data,
                cache: false,
                contentType: false,
                processData: false,
                dataType:"json",
                success: function(suc) {
                    if(suc.code==0){
                        alert("操作成功")
                        $("#videoUrl").val(suc.message);//将地址存储好
                        $("#videoShow").attr("src",suc.message);//显示视频
                        $("#thumburlShow").attr("original",suc.message);
                    }else{
                        alert("操作失败"+suc.message)
                        $(obj).val('');
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("上传失败，请检查网络后重试");
                    $(obj).val('');
                }
            });
        }
    </script>
</head>
<body style="padding: 20px;background-color: #f2f2f2;">

<div class="row" id="video" style="display: block">
    <label class="personattr" id="productVideo">上传视频:</label>
    <input type="hidden" name="shareVideo" id="videoUrl"/>
    <a href="javascript:void(0);" class="btn_addPic" style="margin-left: 40px;">
        <span id="videoTitle">上传视频</span>
        <input id="selectedLogoVideoId" class="selectedLogoImgId" type="file" name="videoFile" onchange="setVideo(this)"
               style="margin-left:1px;"/>
    </a>
    <div id="logoVideoDiv" style="margin-left:140px; height: 140px;">
        <video width="200px" height="120px" style="margin-top:10px;text-align:center;margin-left:15px;display: none"
               id="videoShow" controls src='' name="logoVideoId"/>
    </div>
</div>

</body>
</html>

<!--live2dend-->
