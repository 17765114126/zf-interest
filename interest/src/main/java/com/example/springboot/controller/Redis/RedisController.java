package com.example.springboot.controller.Redis;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.Student;
import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.service.other.RedisCacheUserService;
import com.example.springboot.model.form.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RedisController
 * @Author zhaofu
 * @Date 2019/8/27
 * @Version V1.0
 * <p>
 * 所依赖util
 * RedisConfig
 * RedisUtil
 * 启动不成功---->本地没有启动redis导致连接不到
 **/
@RestController
@Slf4j
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    RedisCacheUserService redisCacheUserService;

    @RequestMapping("/redisTest")
    public Result redisTest() {
        try {
            Student student = new Student();
            student.setName("专诸");
            student.setAge(18);
            log.info("Redis启动日志------------------" + student.toString());
            redisUtil.set("s",student.getName(),1000 * 50);
            String s1 = redisUtil.get("s").toString();
            System.out.println(s1);
            Thread.sleep(1000L);
            CmsUser cmsUser = redisCacheUserService.selectUserById("1");
            System.out.println(JSON.toJSONString(cmsUser));
            return Result.buildSuccess(cmsUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("错误==============" + e);
        }
        return Result.buildFail();
    }
}
