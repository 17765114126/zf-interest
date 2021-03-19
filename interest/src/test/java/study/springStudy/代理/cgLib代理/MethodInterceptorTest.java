package study.springStudy.代理.cgLib代理;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName MethodInterceptorTest
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 * <p>
 * 自定义MethodInterceptor
 **/

public class MethodInterceptorTest implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("======插入后置通知======");
        return object;
    }

}
