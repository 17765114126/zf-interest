package com.example.application.config.IocOrDI.Ioc;

/**
 * @ClassName HelloWorld
 * @Author zhaofu
 * @Date 2020/7/13
 * @Version V1.0
 **/
public class HelloWorld {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your Message : " + message);
    }

    public void init() {
        System.out.println("Bean is going through init.");
    }

    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}