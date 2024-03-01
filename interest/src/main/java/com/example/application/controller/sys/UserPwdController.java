package com.example.application.controller.sys;


import com.example.application.model.req.sys.LoginReq;
import com.example.application.service.sys.SuSmsLogService;
import com.example.application.service.sys.UserPwdService;
import com.example.application.model.form.Result;
import com.example.application.model.enums.ResultCodeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/password")
public class UserPwdController {

    @Resource
    private UserPwdService userPwdService;
    @Resource
    SuSmsLogService suSmsLogService;

    /**
     * @Description: 修改密码
     * @Param:  oldPwd,newPwd,request
     * @return: Baserult
     * @Author: weiquan
     * @Date: 2019/7/1
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updatePwd(@RequestParam(value = "oldPwd")String oldPwd,
                            @RequestParam(value = "newPwd")String newPwd){

        return userPwdService.updatePwd(oldPwd,newPwd);
    }
    @RequestMapping(value = "/updateDefaultPwd",method = RequestMethod.POST)
    public Result updateDefaultPwd(LoginReq req){
        //if(!SpringUtils.testEnv()) {
        Result checkResult = suSmsLogService.checkMobileMessage(req.getMobile(), req.getCode());
        if (!checkResult.success()) {
            return Result.with(ResultCodeEnum.CHECK_CODE_ERROR);
        }
        //}
        return userPwdService.updateDefaultPwd(req.getMobile());
    }
}
