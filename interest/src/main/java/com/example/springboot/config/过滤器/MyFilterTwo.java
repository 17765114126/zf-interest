package com.example.springboot.config.过滤器;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName MyFilterTwo
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 *
 * 至此，一个过滤器就可以使用了，但是在我们平常的工作中一般有多个过滤器，并且要指定每个过滤器的顺序等等，那么用这种方式是没有办法 实现的，
 * 可以使用FilterRegistrationBean 来实现 
 *
 * 1.将上面项目的 过滤器上的@WebFilter(filterName = "myFilter",urlPatterns = {"/*"}) 去掉，将入口类的@ServletComponentScan去掉。
 *
 * 2.根据之前 的做饭做法再编写一个过滤器： MyFilterTwo
 *
 * 3. 编写java配置文件FilterConfig
 **/
public class MyFilterTwo implements Filter {

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("执行过滤器二 MyFilterTwo init ");
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("执行过滤器二 MyFilterTwo doFilter"+req.getParameter("name"));
        chain.doFilter(request, response);
        return ;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("执行过滤器二 MyFilterTwo destroy");
    }

}
