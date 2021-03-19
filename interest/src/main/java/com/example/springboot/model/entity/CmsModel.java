package com.example.springboot.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author system
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CmsModel implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private java.util.Date createDateTime;

    /**
     * 权限ID
     */
    private Long permissionId;


}
