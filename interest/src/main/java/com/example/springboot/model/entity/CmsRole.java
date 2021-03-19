package com.example.springboot.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author system
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CmsRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    private String comment;

    /**
     * 逻辑删除字段(0:未删除 1:已删除)
     */
    private Integer invalid;

    private java.util.Date createTime;


}
