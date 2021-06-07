package com.example.springboot.service.sys;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.mapper.CmsUserMapper;
import com.example.springboot.mapper.SuSmsLogMapper;
import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.model.entity.SmsLog;
import com.example.springboot.model.enums.CmsUserStatusEnum;
import com.example.springboot.model.enums.ResultCodeEnum;
import com.example.springboot.model.form.Result;
import com.example.springboot.utils.aliyun.AliSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class SuSmsLogService {
    @Resource
    SuSmsLogMapper suSmsLogMapper;
    @Resource
    CmsUserMapper cmsUserMapper;

    public Result selectMobileMessage(String mobile) {
        CmsUser user = cmsUserMapper.selectOne(new QueryWrapper<CmsUser>().lambda().eq(CmsUser::getMobile, mobile));
        if (user == null) {
            return Result.with(ResultCodeEnum.NOT_ZC);
        }
        if (user.getStatus() == CmsUserStatusEnum.DISABLE.getCode()) {
            return Result.buildFail("此用户已禁用");
        }
        String code = "";
        try {
            SmsLog ssl = suSmsLogMapper.selectOne(new QueryWrapper<SmsLog>().lambda().eq(SmsLog::getMobile, mobile));
            Random rand = new Random();//生成随机数
            for (int a = 0; a < 6; a++) {
                code += rand.nextInt(10);//生成6位验证码
            }
            if (ssl != null) {
                ssl.setCode(code);
                ssl.setCreateDateTime(new Date());
                ssl.setUpdateTime(new Date());
                suSmsLogMapper.updateById(ssl);
            } else {
                SmsLog smsLog = new SmsLog();//短信验证对象
                smsLog.setCode(code);
                smsLog.setMobile(mobile);
                smsLog.setCreateDateTime(new Date());
                smsLog.setUpdateTime(new Date());
                smsLog.setStatus(1);
                smsLog.setInvalid(0);
                suSmsLogMapper.insert(smsLog);
            }
            String smscode = "{\"code\":\"" + code + "\"}";
            SendSmsResponse srm = AliSmsUtil.sendSms(mobile, smscode, "SMS_206605062");//登录确认验证码
            String aLiMessage = srm.getMessage();
            if (aLiMessage != null) {
                if (aLiMessage.contains("触发")) {
                    return Result.buildFail("你发送短信过于频繁,已经触发分级流控,请1分钟以后再次发送！！");
                }
            } else {
                return Result.buildFail("发送失败");
            }
        } catch (Exception e) {
            log.error("发送验证码异常,msg[{}]", e);
            return Result.buildFail(e.toString());
        }
        return Result.buildSuccess();
    }

    public Result checkMobileMessage(String mobile, String registCode) {
        String msg = null;
        //验证码是否正确
        SmsLog ssl = suSmsLogMapper.selectOne(new QueryWrapper<SmsLog>().lambda().eq(SmsLog::getMobile, mobile));
        if (ssl != null) {
            if (registCode != null && registCode.equals(ssl.getCode())) {
                if (new Date().getTime() - ssl.getCreateDateTime().getTime() > 600000) {
                    return Result.buildFail("验证码超时");
                }
            } else {
                return Result.buildFail("验证码错误");
            }
        } else {
            return Result.buildFail("没有此用户，或者没有获取验证码");
        }
        return Result.buildSuccess();
    }
}
