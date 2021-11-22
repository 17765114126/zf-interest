package com.example.springboot.utils.file.文件;

import com.example.springboot.model.form.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName FileUploadController
 * @Author zhaofu
 * @Date 2019/8/26
 * @Version V1.0
 * @Description: 文件上传
 **/
@RequestMapping("/fileUpload")
@Controller
@Slf4j
public class FileUploadController {

    /**
     * @Date: 2019/7/23
     * @Author: zhaofu
     * @Description: 上传视频文件
     **/

    @ResponseBody
    @RequestMapping(value = "/upVideo.html")
    public Result videosUp(@RequestParam("file") MultipartFile files, HttpServletRequest request,
                           HttpServletResponse response, ModelMap map) throws ServletException, IOException {
        log.info("视频文件" + files.getName());
        FileEntity entity;
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(files, request);
            if (entity != null) {
                return Result.buildSuccess(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.buildFail();
    }

}
