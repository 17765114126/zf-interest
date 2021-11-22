package com.example.springboot.controller.sys;


import com.example.springboot.model.entity.CmsRole;
import com.example.springboot.model.entity.CmsRolePermission;
import com.example.springboot.model.sys.CmsRolePermissionVo;
import com.example.springboot.model.sys.ModelAndButtonVo;
import com.example.springboot.model.sys.PermissionListVo;
import com.example.springboot.service.sys.PermissionListService;
import com.example.springboot.model.form.Result;
import com.example.springboot.utils.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author <a href="mailto:cjw_0709@163.com">cjw</a>
 * @create 2019/6/29 10:45
 */
@RestController
@Slf4j
@RequestMapping(value = "/permissionList", method = RequestMethod.POST)
public class PermissionListController {


    @Autowired
    private PermissionListService permissionListService;

    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    public Object permissionList() {
        try {
            List<CmsRole> cmsRoleList = permissionListService.selectAllCmsRole();
            if (cmsRoleList == null || cmsRoleList.size() < 1) {
                Result.buildFail("没有任何角色!");
            }
            ModelAndButtonVo modelAndButtonVos = permissionListService.selectModelAndButton();
            if(modelAndButtonVos==null){
                return Result.buildFail("权限列表查询为空!");
            }
            PermissionListVo permissionListVo = new  PermissionListVo();
            permissionListVo.setCmsRoles(cmsRoleList);
            permissionListVo.setModelAndButtonVos(modelAndButtonVos);
            return Result.buildSuccess(permissionListVo);

        }catch (Exception e){
            log.error("接口异常!msg[{}]",e);
            return Result.buildFail("权限列表展示接口异常!");
        }
    }

    @RequestMapping(value = "/listByRole", method = RequestMethod.POST)
    public Object permissionListByRole(Long roleId) {
        if (StringUtil.hasBlank(roleId)) {
            return Result.buildFail("参数缺失!");
        }
        try {
            List<CmsRolePermission> list = permissionListService.selectByRoleId(roleId);
            if(list==null||list.size()<1){
                CmsRolePermissionVo cmsRolePermissionVo = new CmsRolePermissionVo();
                cmsRolePermissionVo.setRoleId(roleId);
                cmsRolePermissionVo.setButtonPermissions("");
                cmsRolePermissionVo.setModelPermissions("");
                log.info("查询结果不存在,msg[{}]",roleId);
                return Result.buildSuccess(cmsRolePermissionVo);
            }
            String modelPermission ="";
            String buttonPermission ="";
            for (CmsRolePermission cmsRolePermission:list) {
                if(cmsRolePermission.getType().equals(1)){
                    modelPermission+=cmsRolePermission.getPermissionId()+",";
                }else{
                    buttonPermission+=cmsRolePermission.getPermissionId()+",";
                }
            }
            CmsRolePermissionVo cmsRolePermissionVo = new CmsRolePermissionVo();
            cmsRolePermissionVo.setRoleId(roleId);
            if(StringUtils.isNotBlank(modelPermission)){
                modelPermission = modelPermission.substring(0,modelPermission.length()-1);
                cmsRolePermissionVo.setModelPermissions(modelPermission);
            }
            if(StringUtils.isNotBlank(buttonPermission)){
                buttonPermission = buttonPermission.substring(0,buttonPermission.length()-1);
                cmsRolePermissionVo.setButtonPermissions(buttonPermission);
            }
            return Result.buildSuccess(cmsRolePermissionVo);
        } catch (Exception e) {
            log.error("角色查询权限结果异常,msg[{}]", e);
            return Result.buildFail("角色查询权限结果异常:" + roleId);
        }
    }

    @RequestMapping(value = "/bindPermission", method = RequestMethod.POST)
    public Object bindPermission(Long roleId, String modelPermission,String buttonPermission) {
        if (StringUtil.hasBlank(roleId,modelPermission)) {
            return Result.buildFail("参数缺失!");
        }
        try {
            permissionListService.bindPermission(roleId,modelPermission,buttonPermission);
            return Result.buildSuccess();
        } catch (Exception e) {
            log.error("绑定角色权限接口异常,msg[{}]", e);
            return Result.buildFail("绑定角色权限接口异常:" + roleId);
        }
    }


}
