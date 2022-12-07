package com.example.springboot.controller.sys;

import com.example.springboot.model.enums.ResultCodeEnum;
import com.example.springboot.model.form.Result;
import com.example.springboot.model.req.sys.CmsUserReq;
import com.example.springboot.service.sys.CmsUserService;
import com.example.springboot.utils.*;
import com.example.springboot.utils.regex.CheckUtils;
import com.example.springboot.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jzl
 * @create 2019-06-29
 **/
@RestController
@RequestMapping("/cmsUser")
public class CmsUserController {

    @Autowired
    private CmsUserService cmsUserService;

    /**
     * 员工列表(搜索)
     */
    @PostMapping("/list")
    public Result getCmsUserList(CmsUserReq cmsUserReq) {
        return Result.buildSuccess(cmsUserService.getPage(cmsUserReq));
    }

    /**
     * 员工添加
     */
    @PostMapping("/save")
    public Result saveCmsUser(CmsUserReq cmsUserReq) {

        // 参数校验
        Assert.isTrue(StringUtil.hasBlank(
                cmsUserReq.getUsername(),
                cmsUserReq.getPassword(),
                cmsUserReq.getMobile(),
                cmsUserReq.getRoleIds()),
                ResultCodeEnum.CLIENT_PARAM_ERROR);

        Assert.notTrue(CheckUtils.checkMobile(cmsUserReq.getMobile()), ResultCodeEnum.MOBILE_FORMAT_ERROR);
        Assert.notTrue(CheckUtils.checkPassWord(cmsUserReq.getPassword()), ResultCodeEnum.PASSWORD_FORMAT_ERROR);

        return cmsUserService.saveCmsUser(cmsUserReq.getDto());
    }

    /**
     * 查询员工信息
     */
    @GetMapping("/info")
    public Result getCmsUserInfoById(Long userId) {
        return cmsUserService.getCmsUserInfo(userId);
    }

    /**
     * 员工禁用
     */
    @PostMapping("/disable")
    public Result cmsUserDisable(Long userId) {
        return cmsUserService.disableByUserId(userId);
    }

    /**
     * 员工解禁
     */
    @PostMapping("/enable")
    public Result cmsUserEnable(Long userId) {
        return cmsUserService.enableByUserId(userId);
    }

    /**
     * 员工修改
     */
    @PostMapping("/edit")
    public Result cmsUserEdit(CmsUserReq cmsUserReq) {
        // 参数校验
        Assert.isTrue(StringUtil.hasBlank(
                cmsUserReq.getId(),
                cmsUserReq.getUsername(),
                cmsUserReq.getMobile(),
                cmsUserReq.getRoleIds()),
                ResultCodeEnum.CLIENT_PARAM_ERROR);

        Assert.notTrue(CheckUtils.checkMobile(cmsUserReq.getMobile()), ResultCodeEnum.MOBILE_FORMAT_ERROR);

        if (StringUtil.isNotEmpty(cmsUserReq.getPassword())) {
            if (!CheckUtils.checkPassWord(cmsUserReq.getPassword())){
                return Result.buildFail(ResultCodeEnum.PASSWORD_FORMAT_ERROR.getMsg());
            }
        }

        return cmsUserService.updateCmsUser(cmsUserReq.getDto());
    }

    /**
     * 根据用户名称获取用户信息
     */
    @RequestMapping(value = "/getCmsUserListByName", method = RequestMethod.POST)
    public Result getCmsUserListByName(@RequestParam("name") String name) {
        return Result.buildSuccess(cmsUserService.getUserListByName(name));
    }

    /**
     * 根据用户名称或手机号获取用户信息
     */
    @RequestMapping(value = "/getByNameOrMobile", method = RequestMethod.POST)
    public Result getCmsUserListByNameOrMobile(String name, String mobile) {
        return Result.buildSuccess(cmsUserService.getCmsUserListByNameOrMobile(name, mobile));
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/delete")
    public Result deleteCmsUser(Long userId) {
        return cmsUserService.deleteCmsUser(userId);
    }
}
