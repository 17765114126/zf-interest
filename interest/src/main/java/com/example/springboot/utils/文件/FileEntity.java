package com.example.springboot.utils.文件;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName FileEntity
 * @Description: TODO
 * @Author admin
 * @Date 2019/7/23
 * @Version V1.0
 **/
@Data
public class FileEntity {
    private long fileId;

    /*****
     * 原文件名
     ***/

    private String titleOrig;

    /*****
     * 修改后文件名
     ***/

    private String titleAlter;

    /*****
     * 文件大小
     ***/

    private String size;

    /*****
     * 文件类型
     ***/

    private String type;

    /*****
     * 文件保存路径
     ***/

    private String path;

    /*****
     * 文件上传时间
     ***/

    private Timestamp uploadTime;

}
