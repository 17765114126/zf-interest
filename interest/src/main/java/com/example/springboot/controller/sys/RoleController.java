package com.example.springboot.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.mapper.*;
import com.example.springboot.model.entity.*;
import com.example.springboot.model.sys.ModelAndButtonVo;
import com.example.springboot.service.sys.PermissionListService;
import com.example.springboot.service.sys.RoleService;
import com.example.springboot.model.form.Result;
import com.example.springboot.utils.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private CmsUserRoleMapper cmsUserRoleMapper;

    @Resource
    private PermissionListService permissionListService;

    @Resource
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Resource
    private CmsPermissionMapper cmsPermissionMapper;

    @Resource
    private CmsModelMapper cmsModelMapper;

    @Resource
    private CmsButtonMapper cmsButtonMapper;

    @Resource
    private CmsRoleMapper roleMapper;


    /**
     * @Description: 角色列表
     * @Param: [name]
     * @return: BaseResult
     * @Author: weiquan
     * @Date: 2019/7/1
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getList(@RequestParam(value = "name", required = false) String name) {
        if (StringUtil.isBlank(name)) {
            name = "";
        }
        List<CmsRole> cmsRoleList = roleService.listByName(name);
        return Result.buildSuccess(cmsRoleList);
    }


    /**
     * @Description: 新增角色
     * @Param: cmsRole
     * @return: BaseResult
     * @Author: weiquan
     * @Date: 2019/7/1
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object saveRole(CmsRole cmsRole) {
        Integer integer = roleMapper.selectCount(new QueryWrapper<CmsRole>().lambda().eq(StringUtils.isNotBlank(cmsRole.getName()), CmsRole::getName, cmsRole.getName()));
        if(integer>0){
            return Result.buildFail("角色名已存在!");
        }
        boolean flag = roleService.save(cmsRole);
        if (flag) {
            return Result.buildSuccess();
        } else {
            return Result.buildFail();
        }

    }


    /**
     * @Description:修改角色
     * @Param: cmsRole
     * @return: BaseResult
     * @Author: weiquan
     * @Date: 2019/7/1
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateRole(CmsRole cmsRole) {
        boolean flag = roleService.updateById(cmsRole);
        if (flag) {
            return Result.buildSuccess();
        } else {
            return Result.buildFail();
        }
    }


    /**
     * @Description:删除角色
     * @Param: id
     * @return: BaseResult
     * @Author: weiquan
     * @Date: 2019/7/1
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteRole(@RequestParam(value = "id", required = true) Long id) {
        log.info("删除的角色Id,{}",id);
        return roleService.deleteById(id);
    }


    /**
     * @Description: 拉取登录用户权限(自己玩的 ， 已被废弃)
     * @Param: [userId]
     * @return: java.lang.Object
     * @Author: weiquan
     * @Date: 2019/7/5
     */

    @RequestMapping(value = "/permissionList", method = RequestMethod.POST)
    public Object queryPermission(Long userId) {


        //根据用户id去查询绑定的角色
        QueryWrapper<CmsUserRole> qur = new QueryWrapper<>();
        qur.lambda().eq(CmsUserRole::getUserId, userId);
        List<CmsUserRole> cmsUserRoles = cmsUserRoleMapper.selectList(qur);
        if (cmsUserRoles == null && cmsUserRoles.size() < 0) {
            return Result.buildFail("此用户没有绑定任何角色!");
        }


        //根据角色列表查询对应的权限
        List<List<CmsRolePermission>> cmsRolePermissionList = new ArrayList<>();
        QueryWrapper<CmsRolePermission> qrp = new QueryWrapper<>();
        for (CmsUserRole cmsUserRole : cmsUserRoles) {
            qrp.lambda().eq(CmsRolePermission::getRoleId, cmsUserRole.getRoleId());
            List<CmsRolePermission> cmsRolePermissions = cmsRolePermissionMapper.selectList(qrp);
            cmsRolePermissionList.add(cmsRolePermissions);
        }


        //根据角色查询对应的权限
        List<CmsPermission> cmsPermissionList = new ArrayList<>();
        for (List<CmsRolePermission> cmsRolePermissions : cmsRolePermissionList) {
            for (CmsRolePermission cmsRolePermission : cmsRolePermissions) {
                CmsPermission cmsPermission = cmsPermissionMapper.selectById(cmsRolePermission.getPermissionId());
                cmsPermissionList.add(cmsPermission);
            }

        }


        //去除重复权限
        Set<CmsPermission> cmsPermissionSet = new HashSet<>();
        cmsPermissionSet.addAll(cmsPermissionList);


        //获取菜单列表
        List<CmsModel> cmsModels = new ArrayList<>();
        for (CmsPermission cmsPermission : cmsPermissionSet) {
            if (cmsPermission.getType() == 1) {  //菜单
                QueryWrapper<CmsModel> qm = new QueryWrapper<>();
                qm.lambda().eq(CmsModel::getPermissionId, cmsPermission.getId());
                CmsModel cmsModel = cmsModelMapper.selectOne(qm);
                cmsModels.add(cmsModel);
            }
        }

        //调用曹嘉玮接口，返回树形菜单格式的数据
        ModelAndButtonVo modelAndButtonVo = permissionListService.selectModelAndButtonByParam(cmsModels);


        return Result.buildSuccess(modelAndButtonVo);
    }

    /**
     *@Description:获取角色下拉框

     */
    @PostMapping("/getRoleBox")
    public Result getRoleBox() {
        return roleService.getRoleBox();
    }
    /**
     * 复制角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/copyRole", method = RequestMethod.POST)
    public Result copyRole(Long id){
        return roleService.copyRole(id);
    }

}
