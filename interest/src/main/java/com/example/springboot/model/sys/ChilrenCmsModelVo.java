package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @Description:
 * @create 2019/7/2 19:30
 */
@Data
public class ChilrenCmsModelVo implements Serializable {

    private static final long serialVersionUID = -4369480856767866370L;
    private Integer id;

    /**
     * 父节点的id
     */
    private Integer pid;

    private Integer sort;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块url
     */
    private String url;

    private String imgUrl;

    private String sysTag;

    /**
     * 注明该操作是否在前台展示 0：不展示；1：展示；-1：删除
     */
    private Integer status;

    private Date createDateTime;

    /**
     * 权限ID
     */
    private Long permissionId;


    /**
     * 父权限ID
     */
    private Long parentPermissionId;

    private List<CmsButtonVo> cmsButtons;

    private List<ChilrenCmsModelVo> chilrenCmsModels;
}
