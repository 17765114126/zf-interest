package study.springStudy.动态代理;

import study.springStudy.静态代理.Host;
import study.springStudy.静态代理.Rent;

/**
 * @ClassName Client
 * @Author zhaofu
 * @Date 2020/12/31
 * @Version V1.0
 **/
public class Client {

    //租客
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理实例的调用处理程序
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //将真实角色放置进去！
        pih.setRent(host);
        //动态生成对应的代理类！
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }

    /**
     * 动态代理的角色和静态代理的一样 .
     *
     * 动态代理的代理类是动态生成的 . 静态代理的代理类是我们提前写好的
     *
     * 动态代理分为两类 : 一类是基于接口动态代理 , 一类是基于类的动态代理
     *
     * 基于接口的动态代理----JDK动态代理
     *
     * 基于类的动态代理--cglib
     *
     * 现在用的比较多的是 javasist 来生成动态代理 . 百度一下javasist
     *
     * 我们这里使用JDK的原生代码来实现，其余的道理都是一样的！、
     *
     * JDK的动态代理需要了解两个类
     *
     * 核心 : InvocationHandler     和     Proxy   ， 打开JDK帮助文档看看
     *
     * 【InvocationHandler：调用处理程序】
     *
     * Object invoke(Object proxy, 方法 method, Object[] args)；
     * //参数
     * //proxy - 调用该方法的代理实例
     * //method -所述方法对应于调用代理实例上的接口方法的实例。方法对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。
     * //args -包含的方法调用传递代理实例的参数值的对象的阵列，或null如果接口方法没有参数。原始类型的参数包含在适当的原始包装器类的实例中，例如java.lang.Integer或java.lang.Boolean 。
     *
     * 【Proxy  : 代理】
     * */

    /**
     * 核心：一个动态代理 , 一般代理某一类业务 , 一个动态代理可以代理多个类，代理的是接口！
     *
     *
     * 动态代理的好处
     * 静态代理有的它都有，静态代理没有的，它也有！
     *
     * 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
     *
     * 公共的业务由代理来完成 . 实现了业务的分工 ,
     *
     * 公共业务发生扩展时变得更加集中和方便 .
     *
     * 一个动态代理 , 一般代理某一类业务
     *
     * 一个动态代理可以代理多个类，代理的是接口！
     * */
}