package com.example.springboot.service.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.config.Shiro.security.SecurityUtil;
import com.example.springboot.mapper.*;
import com.example.springboot.model.sys.SysPermission;
import com.example.springboot.model.sys.SysRole;
import com.example.springboot.model.sys.SysUser;
import com.example.springboot.model.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserService {

    @Resource
    CmsUserMapper cmsUserMapper;

    @Resource
    CmsUserRoleMapper cmsUserRoleMapper;

    @Resource
    CmsRoleMapper cmsRoleMapper;

    @Resource
    CmsPermissionMapper cmsPermissionMapper;

    @Resource
    CmsRolePermissionMapper cmsRolePermissionMapper;

    public List<SysPermission> getPermissionList(){
        List<SysPermission> list = new ArrayList<>();
        SysUser u = SecurityUtil.sysUser();
        if(CollectionUtils.isEmpty(u.getRoles())){
            return list;
        }
        Set<Long> pids = new HashSet<>();
        for(SysRole r : u.getRoles()){
            if(CollectionUtils.isNotEmpty(r.getPermissions())) {
                for(SysPermission p : r.getPermissions()) {
                    if(pids.contains(p.getId())){
                        continue;
                    }
                    list.add(p);
                    pids.add(p.getId());
                }
            }
        }
        return list;
    }

    public boolean hasPermission(String permission){
        List<SysPermission> list = getPermissionList();
        if(CollectionUtils.isEmpty(list)){
            return false;
        }
        for(SysPermission p : list){
            if(permission.equals(p.getName())){
                return true;
            }
        }
        return false;
    }

    @Cacheable(value="sys_user", key="#mobile", unless="#result == null")
    public SysUser getUserByMobile(String mobile){
        CmsUser user = cmsUserMapper.selectOne(new QueryWrapper<CmsUser>().lambda().eq(CmsUser::getMobile,mobile));
        if(user == null){
            return null;
        }
        SysUser su = new SysUser();
        su.setId(user.getId());
        su.setInvalid(user.getInvalid());
        su.setUsername(user.getUsername());
        su.setMobile(user.getMobile());
        su.setPassword(user.getPassword());
        su.setStatus(user.getStatus());
        su.setSalt(user.getSalt());
        List<CmsUserRole> rl = cmsUserRoleMapper.selectList(new QueryWrapper<CmsUserRole>().lambda().eq(CmsUserRole::getUserId,user.getId()));
        if(rl == null || rl.isEmpty()){
            return su;
        }
        List<SysRole> roles = new ArrayList<>();
        for(CmsUserRole role : rl){
            SysRole sysRole = new SysRole();
            roles.add(sysRole);
            CmsRole r = cmsRoleMapper.selectById(role.getRoleId());
            sysRole.setId(r.getId());
            sysRole.setName(r.getName());
            sysRole.setComment(r.getComment());
            List<CmsRolePermission> ps = cmsRolePermissionMapper.selectList(new QueryWrapper<CmsRolePermission>().lambda().eq(CmsRolePermission::getRoleId,role.getRoleId()));
            if(ps == null || ps.isEmpty()){
                continue;
            }
            List<SysPermission> permissions = new ArrayList<>();
            for(CmsRolePermission rp : ps){
                SysPermission sp = new SysPermission();
                CmsPermission p = cmsPermissionMapper.selectById(rp.getPermissionId());
                if(p != null){
                    sp.setId(p.getId());
                    sp.setName(p.getName());
                    sp.setComment(p.getComment());
                    sp.setType(p.getType());
                    permissions.add(sp);
                }

            }
            sysRole.setPermissions(permissions);
        }
        su.setRoles(roles);
        return su;
    }

}
