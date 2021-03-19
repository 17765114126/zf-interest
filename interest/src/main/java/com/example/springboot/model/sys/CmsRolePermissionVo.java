package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/7/1 9:56
 */
@Data
public class CmsRolePermissionVo implements Serializable {
    private static final long serialVersionUID = -1843301866996690492L;
    private Long roleId;
    private String modelPermissions;
    private String buttonPermissions;
}
