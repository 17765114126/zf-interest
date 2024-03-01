package com.example.application.config.IocOrDI;

import com.example.application.model.sys.BussinessPerson;
import com.example.application.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName IocTest
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 * @Description: 测试扫描
 **/

public class IocTest {

    public static void main(String[] args) {
        /*Ioc测试*/
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        System.out.println(user.getId());
        System.out.println(user.getNote());
        System.out.println(user.getUserName());
        System.out.println("---------------");
        /*DI测试--未完成*/
        Person person = ctx.getBean(BussinessPerson.class);
        person.service();

    }
}
