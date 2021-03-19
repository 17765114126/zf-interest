package study.springStudy.代理.静态代理;

/**
 * @ClassName TestMain
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
public class TestMain {
    /**
     * 静态代理
     * 我们先说静态代理的实现方式，为什么不推荐使用静态代理？
     *
     * 1.继承方式实现代理（静态代理中的继承代理）
     * */
    public static void main(String[] args) {
        UserImpl user = new Proxy();
        user.system();
    }
    /**
     * 静态代理可以看出来一点问题吧？
     *
     * 每次代理都要实现一个类，导致项目中代码很多；你每次想要代理，都要去实现一个类，
     * 代码就会成堆的增加，然后你就会发现项目的类就会越来越多，就会导致你们的项目显得很臃肿。
     * 而且代码的复用性太低了，并且耦合度非常高，这个我们所说的高内聚低耦合是相悖的。
     * */
}
