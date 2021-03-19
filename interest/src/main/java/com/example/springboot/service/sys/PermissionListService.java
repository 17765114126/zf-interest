package com.example.springboot.service.sys;

import com.example.springboot.model.entity.CmsModel;
import com.example.springboot.model.entity.CmsRole;
import com.example.springboot.model.entity.CmsRolePermission;
import com.example.springboot.model.sys.ModelAndButtonVo;

import java.util.List;

/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/6/29 10:52
 */
public interface PermissionListService {

    List<CmsRolePermission> selectByRoleId(Long roleId);

    void bindPermission(Long roleId, String permissions, String buttonPermission);

    List<CmsRole> selectAllCmsRole();

    ModelAndButtonVo selectModelAndButton();

    ModelAndButtonVo selectModelAndButtonByParam(List<CmsModel> list);

}
