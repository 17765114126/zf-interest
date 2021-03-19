package com.example.springboot.config.监听器;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器主要是对对象自身的创建和销毁进行监听
 * 主要有ServletContextListener、HttpSessionListener、ServletRequestListener，用法基本一致
 * 下面以HttpSessionListener为例，用来监听 统计当前访问人数。
 *
 * @ClassName CountListener
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 **/

//@WebListener
public class CountListener implements HttpSessionListener {
    private int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        count++;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("新增在线人数，当前在线人数：" + count);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        count--;
        se.getSession().getServletContext().setAttribute("count", count);
        System.out.println("删减在线人数，当前在线人数：" + count);
    }

}
/**
 * 得到我们想要的效果，  当然HttpSessionListener除了创建和销毁方法还有一些其他的方法可以实现。
 * 上面使用java配置文件注册的方式注册监听器，当然还有更简单的，直接使用注解
 * 1.在CountListener类上添加@WebListener注解，标记为监听器
 * 2.在项目的启动类上添加注解@ServletComponentScan扫描
 * 一样启动项目，用两个浏览器访问 http://127.0.0.1:8080/testListenerLogin  可以看见和之前是一样的效果
 */