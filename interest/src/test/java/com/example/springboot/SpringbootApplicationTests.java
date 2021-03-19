package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.config.LazyConfig;
import com.example.springboot.model.User;
import com.example.springboot.model.req.sys.CmsUserReq;
import com.example.springboot.model.sys.CmsUserVo;
import com.example.springboot.service.sys.CmsUserService;
import com.example.springboot.utils.EnvUtils;
import com.example.springboot.utils.SendEmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void test5() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(LazyConfig.class);
    }

    @Resource
    private SendEmailUtil sendEmailUtil;
    /**
     * 发送邮件
     * */
    @Test
    public void SendEmail() {
        String email = "17765114126@163.com";
        String html = "<p>亲爱的用户：</p>\n" +
                "<p>&#xa0;</p>\n" +
                "<p>您好！感谢您使用朵拉试衣间平台，您的账号（"+email+"）正在进行邮箱验证，本次请\n" +
                "<p>求的验证码为：</p>\n" +
                "<p>"+"9527"+"(为了保障您帐号的安全性，请在1小时内完成验证。)</p>\n" +
                "<p>&#xa0;</p>\n" +
                "<p>朵拉试衣间平台团队</p>\n" +
                "<p>"+new Date()+"</p>\n";
        String subject = "朵拉试衣间平台-更换邮箱账号操作";
        sendEmailUtil.sendHtmlMail(email, "", subject, html);
    }
    @Test
    public void Test(){
        User user = new User();
        user.setUserName("548");
        System.out.println(user.getUserName());

        if (!EnvUtils.devActive()) {
            System.out.println("生产环境不允许调用!!!");
        }
    }

    @Autowired
    private CmsUserService cmsUserService;

    @Test
    public void Test1() {
        CmsUserReq cmsUserReq = new CmsUserReq();
        cmsUserReq.setStatus(1);
        Page<CmsUserVo> page = cmsUserService.getPage(cmsUserReq);
        System.out.println(JSON.toJSONString(page));
    }
}
