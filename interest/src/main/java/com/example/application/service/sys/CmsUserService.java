package com.example.application.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.application.model.dto.CmsUserDto;
import com.example.application.model.entity.CmsUser;
import com.example.application.model.req.sys.CmsUserReq;
import com.example.application.model.sys.CmsUserVo;
import com.example.application.model.form.Result;

import java.util.List;

/**
 * @author chen
 * @create 2020-12-01
 **/
public interface CmsUserService extends IService<CmsUser> {

    /**
     * 分页查询员工列表
     *
     * @param cmsUserReq
     * @return
     */
    Page<CmsUserVo> getPage(CmsUserReq cmsUserReq);

    /**
     * 查询员工信息
     *
     * @param cmsUserId
     * @return
     */
    Result getCmsUserInfo(Long cmsUserId);

    /**
     * 添加员工
     *
     * @param cmsUserDto
     * @return
     */
    Result saveCmsUser(CmsUserDto cmsUserDto);

    /**
     * 修改员工信息
     *
     * @param cmsUserDto
     * @return
     */
    Result updateCmsUser(CmsUserDto cmsUserDto);

    /**
     * 禁用员工
     *
     * @param userId
     * @return
     */
    Result disableByUserId(Long userId);

    /**
     * 激活员工
     *
     * @param userId
     * @return
     */
    Result enableByUserId(Long userId);

    /**
     * 根据名称匹配用户
     *
     * @param name
     * @return
     */
    List<CmsUserVo> getUserListByName(String name);

    /**
     * 根据用户名称或手机号获取用户信息
     */
    List<CmsUserVo> getCmsUserListByNameOrMobile(String name, String mobile);

    /**
     * 删除员工
     *
     * @param userId
     * @return
     */
    Result deleteCmsUser(Long userId);
}
