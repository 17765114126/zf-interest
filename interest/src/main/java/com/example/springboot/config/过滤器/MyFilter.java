package com.example.springboot.config.过滤器;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 需要在启动类加上@SpringBootApplication注解
 *
 * @ClassName: FilterDemo01
 * @Description:filter的三种典型应用： <br/>
 * 1、可以在filter中根据条件决定是否调用chain.doFilter(request, response)方法， 即是否让目标资源执行<br/>
 * 2、在让目标资源执行之前，可以对request\response作预处理，再让目标资源执行 <br/>
 * 3、在目标资源执行之后，可以捕获目标资源的执行结果，从而实现一些特殊的功能 <br/>
 *
 * @ClassName MyFilter
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 */
//@WebFilter(filterName = "myFilter",urlPatterns = {"/hello"})
public class MyFilter implements Filter {

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化
        System.out.println("执行过滤器的MyFilter init ");
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("执行过滤器的MyFilter doFilter" + req.getParameter("name"));
        chain.doFilter(request, response);

        // 对request和response进行一些预处理
/*        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response); // 让目标资源执行，放行*/
        return;
    }

    @Override
    public void destroy() {
        // 过滤器销毁
        System.out.println("执行过滤器的MyFilter destroy");
    }

}
/**
 * Filter的生命周期
 * <p>
 * Filter的创建
 * Filter的创建和销毁由WEB服务器负责。 web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，完成对象的初始化功能，
 * 从而为后续的用户请求作好拦截的准备工作，filter对象只会创建一次，init方法也只会执行一次。通过init方法的参数，
 * 可获得代表当前filter配置信息的FilterConfig对象。
 * Filter的销毁
 * 　　 Web容器调用destroy方法销毁Filter。destroy方法在Filter的生命周期中仅执行一次。在destroy方法中，可以释放过滤器使用的资源。
 */