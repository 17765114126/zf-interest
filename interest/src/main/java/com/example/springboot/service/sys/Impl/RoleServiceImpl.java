package com.example.springboot.service.sys.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.mapper.CmsRoleMapper;
import com.example.springboot.mapper.CmsRolePermissionMapper;
import com.example.springboot.mapper.CmsUserRoleMapper;
import com.example.springboot.model.entity.CmsRole;
import com.example.springboot.model.entity.CmsRolePermission;
import com.example.springboot.model.entity.CmsUserRole;
import com.example.springboot.model.sys.BoxVO;
import com.example.springboot.service.sys.RoleService;
import com.example.springboot.model.form.PageUtil;
import com.example.springboot.model.form.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private CmsRoleMapper roleMapper;
    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    CmsRolePermissionMapper cmsRolePermissionMapper;
    @Override
    public boolean save(CmsRole cmsRole) {
        int i = roleMapper.insert(cmsRole);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateById(CmsRole cmsRole) {
        if (cmsRole.getId() != null) {
            int i = roleMapper.updateById(cmsRole);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Result deleteById(Long id) {

        // 判断是否有用户分配该角色
        List<CmsUserRole> cmsUserRoles = cmsUserRoleMapper.selectList(PageUtil.getQueryWrapper(new CmsUserRole()).eq(CmsUserRole::getRoleId, id));
        if (CollectionUtils.isNotEmpty(cmsUserRoles)) {
            return Result.buildFail("请先禁用分配该角色的人员");
        }

        log.info("改角色已完成删除验证");
        CmsRole cmsRole = new CmsRole();
        cmsRole.setId(id);
        cmsRole.setInvalid(1);

        int i = roleMapper.updateById(cmsRole);
        log.info("已执行角色更新sql");
        if (i > 0) {
            return Result.buildSuccess("操作成功!");
        } else {
            return Result.buildFail("操作失败!");
        }
    }

    @Override
    public List listByName(String name) {
        QueryWrapper<CmsRole> qr = new QueryWrapper<>();
        qr.lambda().like(CmsRole::getName, name).eq(CmsRole::getInvalid, 0);
        return roleMapper.selectList(qr);
    }

    @Override
    public List listByMap(Map columnMap) {
        return roleMapper.selectByMap(columnMap);
    }


    @Override
    public Result getRoleBox() {
        List<CmsRole> busRoles = roleMapper.selectList(PageUtil.getQueryWrapper(new CmsRole())
                .select(CmsRole::getName, CmsRole::getId)
                .eq(CmsRole::getInvalid, 0));
        List<BoxVO> boxVOList = busRoles.stream()
                .filter((e) -> e != null)
                .map(e -> {
                    BoxVO boxVO = new BoxVO();
                    boxVO.setId(e.getId().intValue());
                    boxVO.setName(e.getName());
                    return boxVO;
                })
                .collect(Collectors.toList());
        return Result.buildSuccess(boxVOList);
    }
    /**
     * 复制角色
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Result copyRole(Long id) {
        if (id == null){
            return Result.buildFail("角色id不能为空");
        }
        //查询角色
        CmsRole cmsRole = roleMapper.selectById(id);
        CmsRole newRole = new CmsRole();
        newRole.setInvalid(0);
        newRole.setName(cmsRole.getName()+"_副本");
        newRole.setComment("副本");
        roleMapper.insert(newRole);
        //权限相关
        LambdaQueryWrapper<CmsRolePermission> wrapper = PageUtil.getQueryWrapper(new CmsRolePermission())
                .eq(CmsRolePermission::getRoleId, id);
        List<CmsRolePermission> rolePermissions = cmsRolePermissionMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(rolePermissions)){
            rolePermissions.forEach(oldRolePre -> {
                //批量插入
                oldRolePre.setRoleId(newRole.getId());
                oldRolePre.setCreateTime(new Date());
                cmsRolePermissionMapper.insert(oldRolePre);
            });
        }
        return Result.buildSuccess();
    }
}
