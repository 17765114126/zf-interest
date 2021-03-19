package com.example.springboot.config.拦截器;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 拦截器和过滤器的功能类似，但是是不同的东西
 * 过滤器只能在web项目中使用， 拦截器可以在非web项目中使用。
 * 过滤器是servlet容器支持的，拦截器是spring支持的，所以拦截器可以使用spring的资源，例如数据源，可以注入的对象等等，而过滤器是不行的。
 * 过滤器只在servlet前后起作用，但是拦截器可以深入 到方法前后等等，功能更强大，所以再spring中要优先使用拦截器
 *
 * @ClassName LoginInterceptor
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        System.out.println("打印拦截器的preHandle");
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute("loginName");
        //这里的loginName是登陆时放入session的
        if (null == loginName || !(loginName instanceof String)) {
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
//            request.setAttribute("msg","请先登录！");
//            request.getRequestDispatcher("/login").forward(request,response);
            response.sendRedirect("/login");
            return false;
        }
        String userName = (String) loginName;
        System.out.println("当前用户已登录，登录的用户名为： " + userName);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("打印拦截器的postHandle");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("打印拦截器的afterCompletion");
    }
}
/**
 * 至此，过滤器、拦截器、监听器的基本使用完成，在实际项目中主要是考虑编写拦截业务等。
 * 例如在拦截器上判断session中是否存在某个值，或者判断redis中token是否过期来判断是否可以 访问请求
 */