package com.example.springboot.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 菜单权限按钮表
 * </p>
 *
 * @author system
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CmsRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限按钮ID
     */
    private Long permissionId;

    private java.util.Date createTime;

    private Integer type;

}
