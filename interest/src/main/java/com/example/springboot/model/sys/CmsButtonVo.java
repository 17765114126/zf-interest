package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @Description:
 * @create 2019/7/5 10:39
 */
@Data
public class CmsButtonVo implements Serializable {


    private static final long serialVersionUID = -8081951235984011259L;
    private Long id;

    private Integer modelId;

    /**
     * 权限按钮名称
     */
    private String name;

    /**
     * 描述
     */
    private String comment;

    /**
     * 请求URL
     */
    private String url;

    private Date createTime;

    /**
     * 权限ID
     */
    private Long permissionId;

    private Byte invalid;

    private Long parentPermissionId;
}
