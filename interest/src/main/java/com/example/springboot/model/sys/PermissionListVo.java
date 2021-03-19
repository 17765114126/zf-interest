package com.example.springboot.model.sys;

import com.example.springboot.model.entity.CmsRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/7/2 13:52
 */
@Data
public class PermissionListVo implements Serializable {

    private static final long serialVersionUID = -3058166051441760572L;

    private List<CmsRole> cmsRoles;

    private ModelAndButtonVo modelAndButtonVos;

}
