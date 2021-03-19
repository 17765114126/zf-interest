package study.springStudy.代理.cgLib代理;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @ClassName Client
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
public class Client {
    /**
     * cgLib代理
     *
     * 这种代理则是适合那些没有接口抽象的类代理，而Java 动态代理适合于那些有接口抽象的类代理。
     */

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\x");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(TestService.class);
        // 设置enhancer的回调对象
        MethodInterceptorTest t = new MethodInterceptorTest();
        enhancer.setCallback(t);
        // 创建代理对象
        TestService proxy = (TestService) enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
    }
    /*上面代码想要运行，需要cglib的jar和asm的jar*/
/**
 *
 * 实现CGLIB动态代理必须实现MethodInterceptor(方法拦截器)接口，
 *
 * 这个接口只有一个intercept()方法，这个方法有4个参数：
 *
 * obj表示增强的对象，即实现这个接口类的一个对象；
 *
 * method表示要被拦截的方法；
 *
 * args表示要被拦截方法的参数；
 *
 * proxy表示要触发父类的方法对象
 */

/**
 *
 * 代理的使用
 * 那么什么时候使用静态态代理，什么时候使用动态代理和cgLib代理呢？
 *
 * 一般情况静态代理是很少是用的，因为他对代码的复用性或者说是耦合度都非常不友好，不推荐使用。
 *
 * 如果目标对象至少实现了一个接口，那么就用JDK动态代理，所有由目标对象实现的接口将全部都被代理。
 *
 * 如果目标对象没有实现任何接口，就是个类，那么就用CGLIB代理。
 *
 *
 * 参考文章：http://www.justdojava.com/2019/07/27/java-Proxy/
 * */

}
