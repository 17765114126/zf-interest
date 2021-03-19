package study.springStudy.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AOP（Aspect Oriented Programming）意为：
 * 面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
 * AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。
 * 利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
 *
 * 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....
 *
 * 切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。
 *
 * 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
 *
 * 目标（Target）：被通知对象。
 *
 * 代理（Proxy）：向目标对象应用通知之后创建的对象。
 *
 * 切入点（PointCut）：切面通知 执行的 “地点”的定义。
 *
 * 连接点（JointPoint）：与切入点匹配的执行点。
 *
 * 三种方式，这里用注解方式
 **/
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }

/**
 * 第二步：在Spring配置文件中，注册bean，并增加支持注解的配置
 *    <aop:aspectj-autoproxy/>
 *    <bean id="annotationPointcut" class="study.springStudy.AOP.AnnotationPointcut"/>
 *    <bean id="userService" class="study.springStudy.AOP.UserServiceImpl"/>
 *
 * 通过aop命名空间的<aop:aspectj-autoproxy />声明自动为spring容器中那些配置@aspectJ切面的bean创建代理，织入切面。
 * 当然，spring 在内部依旧采用AnnotationAwareAspectJAutoProxyCreator进行自动代理的创建工作，
 * 但具体实现的细节已经被<aop:aspectj-autoproxy />隐藏起来了
 *
 * <aop:aspectj-autoproxy />有一个proxy-target-class属性，默认为false，表示使用jdk动态代理织入增强，
 * 当配为<aop:aspectj-autoproxy  poxy-target-class="true"/>时，表示使用CGLib动态代理技术织入增强。
 * 不过即使proxy-target-class设置为false，如果目标类没有声明接口，则spring将自动使用CGLib动态代理。
 * */

}
