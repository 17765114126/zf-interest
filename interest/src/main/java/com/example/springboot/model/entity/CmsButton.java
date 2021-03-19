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
public class CmsButton implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private java.util.Date createTime;

    /**
     * 权限ID
     */
    private Long permissionId;

    private Byte invalid;
}
