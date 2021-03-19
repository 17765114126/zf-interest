package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ListenerConterller
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 **/

@RestController
public class ListenerConterller {

    @RequestMapping("/testListenerLogin")
    public String testListenerLogin(HttpServletRequest req) {
        System.out.println("当前在线人数" + req.getSession().getId() + "："
                + req.getSession().getServletContext().getAttribute("count"));
        return "Hello testListenerLogin";
    }

}
