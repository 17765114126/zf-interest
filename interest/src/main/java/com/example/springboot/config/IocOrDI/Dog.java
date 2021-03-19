package com.example.springboot.config.IocOrDI;

import org.springframework.stereotype.Component;

/**
 * @ClassName Dog
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 **/
@Component
public class Dog implements Animal{
    @Override
    public void use() {
        System.out.println("狗{"+Dog.class.getSimpleName()+"}是看门用的。");
    }
}
