package com.example.springboot.controller.sys;

import com.alibaba.fastjson.JSON;
import com.example.springboot.config.Shiro.security.MySessionManager;
import com.example.springboot.config.Shiro.security.SecurityConstants;
import com.example.springboot.config.Shiro.security.SecurityUtil;
import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.model.req.sys.LoginReq;
import com.example.springboot.model.sys.SysUser;
import com.example.springboot.model.sys.UserInfoVO;
import com.example.springboot.service.sys.SuSmsLogService;
import com.example.springboot.controller.Redis.RedisUtil;
import com.example.springboot.model.form.Result;
import com.example.springboot.model.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class LoginController {

    @Resource
    SuSmsLogService suSmsLogService;

    @Resource
    MySessionManager mySessionManager;

    @Resource
    RedisUtil redisUtil;

    private final static String LOGIN_SESSION_KEY_PREFIX = "zf_";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(LoginReq req) {
        Result result;
        try {
            if(!SpringUtils.testEnv()) {
                Result checkResult = suSmsLogService.checkMobileMessage(req.getMobile(), req.getCode());
                if (!checkResult.success()) {
                    return Result.with(ResultCodeEnum.CHECK_CODE_ERROR);
                }
            }
            //获取当前用户
            Subject subject = SecurityUtils.getSubject();
            //用户登陆数据
            UsernamePasswordToken token = new UsernamePasswordToken(req.getMobile(), req.getPassword());
            subject.login(token);

            String currentSessionId = subject.getSession().getId().toString();
            String cacheKey = LOGIN_SESSION_KEY_PREFIX+req.getMobile();
            redisUtil.lSet(cacheKey,currentSessionId, SecurityConstants.SESSION_TIMEOUT/1000);
            List<Object> sl = redisUtil.lGet(cacheKey,0,-1);
            for(Object obj : sl){
                String loginSessionId = (String)obj;
                if(!loginSessionId.equals(currentSessionId)){
                    redisUtil.lRemove(cacheKey,10,obj);
                    Session session = mySessionManager.getSessionDAO().readSession(loginSessionId);
                    if(session!=null){
                        mySessionManager.getSessionDAO().delete(session);
                    }
                }
            }

            SysUser user = SecurityUtil.sysUser();
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setId(user.getId().toString());
            userInfoVO.setUsername(user.getUsername());
            userInfoVO.setMobile(user.getMobile());
            userInfoVO.setToken(subject.getSession().getId().toString());
            userInfoVO.setRoleName(user.getRoles().get(0).getName());
            result = Result.with(userInfoVO);
           /*return Result.with(subject.getSession().getId());*/
        } catch (IncorrectCredentialsException e) {
            System.out.println(JSON.toJSONString(e));
            result = Result.with(ResultCodeEnum.PASSWORD_ERROR);
        } catch (LockedAccountException e) {
            System.out.println(JSON.toJSONString(e));
            result = Result.with(ResultCodeEnum.USER_INVALID);
        } catch (AuthenticationException e) {
            System.out.println(JSON.toJSONString(e));
            result = Result.with(ResultCodeEnum.USER_NOT_EXIST);
        } catch (Exception e) {
            System.out.println(JSON.toJSONString(e));
            result = Result.with(ResultCodeEnum.INNER_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/notlogin")
    public Object unauth() {
        return Result.with(ResultCodeEnum.NOT_LOGIN);
    }


    @RequestMapping(value = "/roleList",method = RequestMethod.POST)
    public Object roleList(@RequestParam(value = "name")String name){
        SysUser user = SecurityUtil.sysUser();
        return Result.with(user.getRoles());
    }

   @RequestMapping(value = "logout",method = RequestMethod.GET)
    public void logout(){
       Subject subject = SecurityUtils.getSubject();
       subject.logout();
   }



}
