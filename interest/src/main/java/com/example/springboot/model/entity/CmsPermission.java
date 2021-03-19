package com.example.springboot.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 权限按钮表
 * </p>
 *
 * @author system
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CmsPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String comment;

    private java.util.Date createTime;

    /**
     * 1. 菜单 2. 按钮
     */
    private Integer type;



}
