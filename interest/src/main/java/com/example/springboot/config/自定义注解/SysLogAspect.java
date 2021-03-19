package com.example.springboot.config.自定义注解;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.MyLogClass;
import com.example.springboot.model.SysCmsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 操作日志
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    /**
     * 操作日志
     */
    //@Resource
    //MallCmsModelButtonService mallCmsModelButtonService;
    @Resource
    MyLogService myLogService;
    //@Resource
    //SysCmsLogMapper sysCmsLogMapper;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.example.springboot.config.自定义注解.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysCmsLog(JoinPoint joinPoint) {
        MyLogClass myLogClass = null;
        Integer modelButtonCatrgoryId = null;
        String value = null;
        Class LogMethod= null;
        //保存日志
        SysCmsLog sysLog = new SysCmsLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        //获取用户名
/*        SysUser user = SecurityUtil.sysUser();
        if(user != null){
            sysLog.setLoginName(user.getUsername());
            sysLog.setLoginMobile(user.getMobile());
        }*/
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            LogMethod = myLog.method();
            value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作按钮
            //插入菜单按钮记录
            //modelButtonCatrgoryId = mallCmsModelButtonService.modelButtonCatrgory(value);
        }
        sysLog.setModelButtonCatrgoryId(modelButtonCatrgoryId);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);//[{"iconName":"测试修改5","id":1}]
        sysLog.setParams(params);
        //获取操作数据的id
        params = StringUtils.strip(params,"[]");

        try {
             myLogClass = myLogService.getTblId(value, params, LogMethod);
        } catch (Exception e) {
            log.error("切面获取操作表数据id失败"+value+"."+params);
        }
        if (myLogClass != null){
            if (myLogClass.getTblId() != null){
                sysLog.setTblId(myLogClass.getTblId());
            }
            if (myLogClass.getProductId() != null){
                sysLog.setProductId(Integer.parseInt(myLogClass.getProductId()));
            }
            if (myLogClass.getQdNumber() != null){
                sysLog.setQdNumber(myLogClass.getQdNumber());
            }
        }

        //sysCmsLogMapper.insert(sysLog);
    }

}
