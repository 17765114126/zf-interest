package com.example.springboot.service.sys.Impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.config.Shiro.security.SecurityUtil;
import com.example.springboot.mapper.CmsRoleMapper;
import com.example.springboot.mapper.CmsUserMapper;
import com.example.springboot.mapper.CmsUserRoleMapper;
import com.example.springboot.model.dto.CmsUserDto;
import com.example.springboot.model.dto.CmsUserRoleDto;
import com.example.springboot.model.entity.CmsRole;
import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.model.entity.CmsUserRole;
import com.example.springboot.model.enums.CmsUserStatusEnum;
import com.example.springboot.model.enums.ResultCodeEnum;
import com.example.springboot.model.enums.StringPool;
import com.example.springboot.model.form.PageUtil;
import com.example.springboot.model.form.Result;
import com.example.springboot.model.req.sys.CmsUserReq;
import com.example.springboot.model.sys.CmsUserVo;
import com.example.springboot.service.sys.CmsUserService;
import com.example.springboot.utils.*;
import com.example.springboot.utils.string.RandomUtil;
import com.example.springboot.utils.string.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jzl
 * @create 2019-07-02
 **/
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements CmsUserService {

    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;
    @Autowired
    private CmsRoleMapper cmsRoleMapper;

    @Override
    public Page<CmsUserVo> getPage(CmsUserReq cmsUserReq) {

        Page<CmsUser> page = cmsUserReq.toPage();
        List<Long> cmsUserIds = null;

        if (StringUtil.isNotEmpty(cmsUserReq.getRoleParam())) {
            List<CmsRole> cmsRoles = cmsRoleMapper.selectList(PageUtil.getQueryWrapper(new CmsRole())
                    .select(CmsRole::getId)
                    .eq(CmsRole::getId, cmsUserReq.getRoleParam()));

            if (CollectionUtils.isNotEmpty(cmsRoles)) {

                List<CmsUserRole> cmsUserRoles = cmsUserRoleMapper.selectList(PageUtil.getQueryWrapper(new CmsUserRole())
                        .select(CmsUserRole::getUserId)
                        .in(CmsUserRole::getRoleId, cmsRoles.stream().map(CmsRole::getId).collect(Collectors.toList())));

                if (CollectionUtils.isNotEmpty(cmsUserRoles)) {
                    cmsUserIds = cmsUserRoles.stream().map(CmsUserRole::getUserId).collect(Collectors.toList());
                }else {
                    ArrayList<Long> longs = new ArrayList<>();
                    longs.add(-1L);
                    cmsUserIds = longs;
                }
            }
        }

        LambdaQueryWrapper<CmsUser> queryWrapper = PageUtil.getQueryWrapper(new CmsUser())
                // 查询全部
                .eq(CmsUserStatusEnum.ALL.getCode().equals(cmsUserReq.getStatus()) && StringUtil.isEmpty(cmsUserReq.getQueryParam()), CmsUser::getInvalid, 0)
                .in(CmsUserStatusEnum.ALL.getCode().equals(cmsUserReq.getStatus())
                        && StringUtil.isEmpty(cmsUserReq.getQueryParam())
                        && CollectionUtils.isNotEmpty(cmsUserIds), CmsUser::getId, cmsUserIds)

                // 按用户名查
                .or(StringUtil.isNotEmpty(cmsUserReq.getQueryParam()))
                .in(CollectionUtils.isNotEmpty(cmsUserIds), CmsUser::getId, cmsUserIds)
                .eq(!CmsUserStatusEnum.ALL.getCode().equals(cmsUserReq.getStatus()), CmsUser::getStatus, cmsUserReq.getStatus())
                .like(StringUtil.isNotEmpty(cmsUserReq.getQueryParam()), CmsUser::getUsername, cmsUserReq.getQueryParam())
                .eq(CmsUser::getInvalid, 0)

                // 按手机号查
                .or(StringUtil.isNotEmpty(cmsUserReq.getQueryParam()))
                .in(CollectionUtils.isNotEmpty(cmsUserIds), CmsUser::getId, cmsUserIds)
                .eq(!CmsUserStatusEnum.ALL.getCode().equals(cmsUserReq.getStatus()), CmsUser::getStatus, cmsUserReq.getStatus())
                .like(StringUtil.isNotEmpty(cmsUserReq.getQueryParam()), CmsUser::getMobile, cmsUserReq.getQueryParam())
                .eq(CmsUser::getInvalid, 0);

        if (CollectionUtils.isNotEmpty(cmsUserReq.getAsc())) {
            if (cmsUserReq.getAsc().contains("createTime")) {
                queryWrapper.orderByAsc(CmsUser::getCreateTime);
            }
        }

        if (CollectionUtils.isNotEmpty(cmsUserReq.getDesc())) {
            if (cmsUserReq.getDesc().contains("createTime")) {
                queryWrapper.orderByDesc(CmsUser::getCreateTime);
            }
        }

        IPage<CmsUser> iPage = page(page, queryWrapper);
        page = PageUtil.toPage(iPage);

        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new Page<>();
        }
        // page转换 VO返回
        return PageUtil.convert(page, cmsUser -> {

            CmsUserVo cmsUserVo = new CmsUserVo(cmsUser);

            // 查询该用户所有的关联角色
            List<CmsUserRole> cmsUserRoles = getCmsUserRoles(cmsUserVo.getId());

            // 设置角色名
            cmsUserVo.setRoleNames(getRoleNames(cmsUserRoles));

            return cmsUserVo;
        });
    }

    @Override
    public Result getCmsUserInfo(Long cmsUserId) {

        CmsUser cmsUser = getById(cmsUserId);
        Assert.isNull(cmsUser, ResultCodeEnum.CMS_USER_INFO_ERROR);

        String roleIds = getCmsUserRoles(cmsUserId).stream().map(e -> String.valueOf(e.getRoleId())).reduce((x, y) -> x + StringPool.COMMA + y).orElse(StringPool.EMPTY);
        String roleNames = getRoleNames(getCmsUserRoles(cmsUserId));

        CmsUserVo cmsUserVo = new CmsUserVo(cmsUser);
        cmsUserVo.setRoleIds(roleIds);
        cmsUserVo.setRoleNames(roleNames);

        return Result.buildSuccess(cmsUserVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveCmsUser(CmsUserDto cmsUserDto) {

        // 查询员工是否存在
        CmsUser cmsUser = getCmsUserByMobile(cmsUserDto);
        Assert.notNull(cmsUser, ResultCodeEnum.MOBILE_REPETITION_ERROR);

        // 密码加密
        cmsUserDto.setSalt(RandomUtil.userSalt());
        cmsUserDto.setPassword(SecurityUtil.encodePassword(cmsUserDto.getPassword(), cmsUserDto.getSalt()));

        // 添加用户
        boolean save = save(cmsUserDto);
        if (!save) {
            return Result.buildFail();
        }

        // 添加用户关联角色
        saveCmsUserRole(cmsUserDto);

        return Result.buildSuccess();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateCmsUser(CmsUserDto cmsUserDto) {

        // 查询员工是否存在
        CmsUser cmsUser = getById(cmsUserDto.getId());

        Assert.isNull(cmsUser, ResultCodeEnum.USER_NOT_EXIST);
        // 排除自己
        Assert.notEquals(cmsUser.getId(), cmsUserDto.getId(), ResultCodeEnum.MOBILE_REPETITION_ERROR);

        // 密码加密 (前端密码为空实为空字符串, 需重新赋值为null才能略过)
        if (StringUtil.isEmpty(cmsUserDto.getPassword())) {
            cmsUserDto.setPassword(null);
        } else {
            if (StringUtil.isEmpty(cmsUser.getSalt())) {
                cmsUserDto.setSalt(RandomUtil.userSalt());
                cmsUserDto.setPassword(SecurityUtil.encodePassword(cmsUserDto.getPassword(), cmsUserDto.getSalt()));
            } else {
                cmsUserDto.setPassword(SecurityUtil.encodePassword(cmsUserDto.getPassword(), cmsUser.getSalt()));
            }
        }

        // 更新用户信息
        boolean update = updateById(cmsUserDto);
        if (!update) {
            return Result.buildFail();
        }

        // 删除用户关联角色
        List<CmsUserRole> cmsUserRoles = getCmsUserRoles(cmsUserDto.getId());

        if (CollectionUtils.isNotEmpty(cmsUserRoles)) {
            // 获取旧的角色ids
            String oldRoleIds = cmsUserRoles.stream().map(e -> String.valueOf(e.getRoleId())).reduce((x, y) -> x + StringPool.COMMA + y).orElse(StringPool.EMPTY);

            // 角色ids不变不必操作, 否则 删除原数据
            if (cmsUserDto.getRoleIds().equals(oldRoleIds)) {
                return Result.buildSuccess();
            } else {
                cmsUserRoleMapper.deleteBatchIds(cmsUserRoles.stream().map(e -> e.getId()).collect(Collectors.toList()));
            }
        }

        // 添加用户关联角色
        saveCmsUserRole(cmsUserDto);
        return Result.buildSuccess();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result disableByUserId(Long userId) {
        // 查询员工信息(是否禁用或删除)
        CmsUser user = getCmsUser(userId, CmsUserStatusEnum.ENABLE.getCode());
        Assert.isNull(user, ResultCodeEnum.CMS_USER_DISABLE_ERROR);
        Assert.isTrue(0 != user.getInvalid(), ResultCodeEnum.CMS_USER_DELETE_ERROR);

        // 删除用户关联角色
//        List<CmsUserRole> cmsUserRoles = getCmsUserRoles(userId);
//        Assert.isTrue(CollectionUtils.isEmpty(cmsUserRoles), ResultCodeEnum.ROLES_IS_EMPTY_ERROR);
//
//        cmsUserRoleMapper.deleteBatchIds(cmsUserRoles.stream().map(CmsUserRole::getId).collect(Collectors.toList()));

        // 更新员工信息为禁用
        boolean update = updateCmsUser(userId, CmsUserStatusEnum.DISABLE.getCode());

        if (!update) {
            return Result.buildFail();
        }
        return Result.buildSuccess();
    }

    @Override
    public Result enableByUserId(Long userId) {
        // 查询员工信息(是否激活或删除)
        CmsUser user = getCmsUser(userId, CmsUserStatusEnum.DISABLE.getCode());

        Assert.isNull(user, ResultCodeEnum.CMS_USER_ENABLE_ERROR);
        Assert.isTrue(0 != user.getInvalid(), ResultCodeEnum.CMS_USER_DELETE_ERROR);

        // 判断用户是否绑定角色
        List<CmsUserRole> cmsUserRoles = getCmsUserRoles(userId);
        Assert.isTrue(CollectionUtils.isEmpty(cmsUserRoles), ResultCodeEnum.ROLES_IS_EMPTY_ERROR);

        // 更新员工信息为激活
        boolean update = updateCmsUser(userId, CmsUserStatusEnum.ENABLE.getCode());

        if (!update) {
            return Result.buildFail();
        }
        return Result.buildSuccess();
    }

    /**
     * 获取用户所拥有的的角色列表
     *
     * @param id
     * @return
     */
    private List<CmsUserRole> getCmsUserRoles(Long id) {
        return cmsUserRoleMapper.selectList(PageUtil.getQueryWrapper(new CmsUserRole()).eq(CmsUserRole::getUserId, id));
    }

    /**
     * 批量保存用户角色关联表
     *
     * @param cmsUserDto
     */
    private void saveCmsUserRole(CmsUserDto cmsUserDto) {
        Arrays.stream(cmsUserDto.getRoleIds().split(StringPool.COMMA)).forEach(e -> {
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setRoleId(Long.parseLong(e));
            cmsUserRoleDto.setUserId(cmsUserDto.getId());

            cmsUserRoleMapper.insert(cmsUserRoleDto);
        });
    }

    /**
     * 根据手机号查询用户
     *
     * @param cmsUserDto
     * @return
     */
    private CmsUser getCmsUserByMobile(CmsUserDto cmsUserDto) {
        return getOne(PageUtil.getQueryWrapper(new CmsUser()).eq(CmsUser::getMobile, cmsUserDto.getMobile()));
    }

    /**
     * 获取角色名称
     *
     * @param cmsUserRoles
     * @return
     */
    private String getRoleNames(List<CmsUserRole> cmsUserRoles) {
        if (CollectionUtils.isEmpty(cmsUserRoles)) {
            return StringPool.EMPTY;
        }
        return cmsUserRoles.stream()
                .map(cmsUserRole -> {
                    CmsRole cmsRole = cmsRoleMapper.selectById(cmsUserRole.getRoleId());
                    return cmsRole == null ? StringPool.EMPTY : cmsRole.getName();
                })
                .reduce((x, y) -> x + StringPool.COMMA + y)
                .orElse(StringPool.EMPTY);
    }

    /**
     * 根据id, 状态 获取用户信息
     *
     * @param userId
     * @param status
     * @return
     */
    private CmsUser getCmsUser(Long userId, Integer status) {
        return getOne(PageUtil.getQueryWrapper(new CmsUser())
                .eq(CmsUser::getId, userId)
                .eq(CmsUser::getStatus, status));
    }

    /**
     * 更新用户状态
     *
     * @param userId
     * @param status
     * @return
     */
    private boolean updateCmsUser(Long userId, Integer status) {
        CmsUser cmsUser = new CmsUser();
        cmsUser.setStatus(status);
        cmsUser.setId(userId);
        return updateById(cmsUser);
    }

    @Override
    public List<CmsUserVo> getUserListByName(String name) {
        Assert.isEmpty(name, ResultCodeEnum.CLIENT_PARAM_ERROR);
        LambdaQueryWrapper<CmsUser> cmsUserLambdaQueryWrapper = new QueryWrapper<CmsUser>().lambda().like(CmsUser::getUsername, name).eq(CmsUser::getStatus, CmsUserStatusEnum.ENABLE.getCode());
        List<CmsUser> cmsUsers = this.list(cmsUserLambdaQueryWrapper);
        List<CmsUserVo> userInfoList = Convert.toList(CmsUserVo.class, cmsUsers);
        return userInfoList;
    }

    /**
     * @param name   用户姓名
     * @param mobile 手机号
     * @Description:通过名称和手机号查询用户信息
     * @Author: xk. Hu
     */
    @Override
    public List<CmsUserVo> getCmsUserListByNameOrMobile(String name, String mobile) {
        LambdaQueryWrapper<CmsUser> cmsUserLambdaQueryWrapper = new QueryWrapper<CmsUser>().lambda()
                .like(StringUtil.isNotEmpty(name), CmsUser::getUsername, name)
                .eq(CmsUser::getStatus, CmsUserStatusEnum.ENABLE.getCode())
                .like(StringUtil.isNotEmpty(mobile), CmsUser::getMobile, mobile);
        List<CmsUser> cmsUsers = this.list(cmsUserLambdaQueryWrapper);
        List<CmsUserVo> userInfoList = Convert.toList(CmsUserVo.class, cmsUsers);
        return userInfoList;
    }

    @Override
    public Result deleteCmsUser(Long userId) {

        CmsUser cmsUser = getById(userId);
        Integer invalid = cmsUser.getInvalid();
        Assert.isEquals(invalid, 1, ResultCodeEnum.CMS_USER_DELETE_ERROR);

        boolean update = update(PageUtil.getUpdateWrapper(new CmsUser())
                .set(CmsUser::getStatus, CmsUserStatusEnum.DISABLE.getCode())
                .set(CmsUser::getInvalid, 1)
                .eq(CmsUser::getId, userId));
        return update ? Result.buildSuccess() : Result.buildFail();
    }
}
