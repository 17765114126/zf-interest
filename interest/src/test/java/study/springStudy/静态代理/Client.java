package study.springStudy.静态代理;

/**
 * @ClassName Client
 * @Author zhaofu
 * @Date 2020/12/31
 * @Version V1.0
 **/
//客户类，一般客户都会去找代理！
public class Client {
    public static void main(String[] args) {
        //房东要租房
        Host host = new Host();
        //中介帮助房东
        Proxy proxy = new Proxy(host);

        //你去找中介！
        proxy.rent();
    }
}
/**
 *
 * url:https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247484130&idx=1&sn=73741a404f7736c02bcdf69f565fe094&scene=19#wechat_redirect
 *
 * 静态代理角色分析
 *
 * 抽象角色 : 一般使用接口或者抽象类来实现
 *
 * 真实角色 : 被代理的角色
 *
 * 代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 .
 *
 * 客户  :  使用代理角色来进行一些操作 .
 * */

/**
 * 分析：在这个过程中，你直接接触的就是中介，就如同现实生活中的样子，你看不到房东，但是你依旧租到了房东的房子通过代理，这就是所谓的代理模式，
 * 程序源自于生活，所以学编程的人，一般能够更加抽象的看待生活中发生的事情。
 *
 * 静态代理的好处:
 *
 * 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
 *
 * 公共的业务由代理来完成 . 实现了业务的分工 ,
 *
 * 公共业务发生扩展时变得更加集中和方便 .
 *
 * 缺点 :
 *
 * 类多了 , 多了代理类 , 工作量变大了 . 开发效率降低 .
 *
 * 我们想要静态代理的好处，又不想要静态代理的缺点，所以 , 就有了动态代理 !
 * */