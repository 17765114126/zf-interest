package com.example.application.service.sys.Impl;

import com.example.application.config.Shiro.security.SecurityUtil;
import com.example.application.mapper.CmsUserMapper;
import com.example.application.model.entity.CmsUser;
import com.example.application.model.sys.SysUser;
import com.example.application.service.sys.CmsUserService;
import com.example.application.service.sys.UserPwdService;
import com.example.application.utils.Assert;
import com.example.application.model.form.PageUtil;
import com.example.application.model.form.Result;
import com.example.application.model.enums.ResultCodeEnum;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserPwdServiceImpl implements UserPwdService {
    @Resource
    private CmsUserMapper cmsUserMapper;
    @Resource
    CmsUserService cmsUserService;
    public Result updatePwd(String oldPwd, String newPwd) {
        //当前登录用户
        /*String user = request.getRemoteUser();
        String[] userArray = user.split(",",1);
        String userStr = userArray[0];
        String[] idStr = userStr.split("=");
        String id = idStr[1];*/
        Subject sub = org.apache.shiro.SecurityUtils.getSubject();
        SysUser sysUser= (SysUser)sub.getPrincipal();
        String msg="";
        int code=0;
        if(sysUser!=null&&sysUser.getId()!=null){
            CmsUser cmsUser= cmsUserMapper.selectById(sysUser.getId());
            if(cmsUser!=null){
               /* String s = SecurityUtil.encodePassword(oldPwd, cmsUser.getId().toString());*/
                //校验原密码
                if(cmsUser.getPassword().equals(SecurityUtil.encodePassword(oldPwd,cmsUser.getSalt()))){
                    //修改密码
                    cmsUser.setPassword(SecurityUtil.encodePassword(newPwd,cmsUser.getSalt()));
                    int i = cmsUserMapper.updateById(cmsUser);
                    if(i>0){
                        msg="操作成功!";
                    }else{
                        msg="操作失败";
                        code=1;
                    }
                }else{
                    msg="原密码不正确!";
                    code=-1;
                }
            }else{
                msg="找不到此用户!";
                code=3;
            }
        }else{
            msg="数据错误!";
            code=2;
        }
        return Result.with(code,msg);
    }
    public Result updateDefaultPwd(String mobile) {
        String password = "$@$tz1@@7ndq8jypnifo";
        Assert.isEmpty(mobile, ResultCodeEnum.CLIENT_PARAM_ERROR);
        CmsUser cmsUser = cmsUserService.getOne(PageUtil.getQueryWrapper(new CmsUser())
                .eq(CmsUser::getMobile, mobile)
                .eq(CmsUser::getInvalid, 0), false);
        if (cmsUser == null || cmsUser.getId() == null) {
            return Result.buildFail("请输入有效手机号");
        }
        cmsUser.setPassword(SecurityUtil.encodePassword(password,cmsUser.getSalt()));
        if (cmsUserService.updateById(cmsUser)) {
            return Result.with(password);
        }
        return Result.buildFail();
    }
}
