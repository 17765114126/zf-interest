package study.springStudy.代理.动态代理;

/**
 * @ClassName MyProxyTest
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
public class MyProxyTest {
    //代理模式
    public static void main(String[] args) {

        //不需要执行额外方法的
        Italk people1 = new People("22和33 ", "1800");
        people1.talk("No ProXY Test测试");
        System.out.println("-----------------------------");

        //需要执行额外方法的
        TalkProxy talker = new TalkProxy(people1);
        talker.talk("ProXY Test", "以父之名");
    }
}
/**
 * 一个 Italk 接口，有空的方法 talk（）（说话），所有的 people 对象都实现（implements）这个接口，实现 talk() 方法，
 * 前端有很多地方都将 people 实例化，执行 talk 方法，后来发现这些前端里有一些除了要说话以外还要唱歌（sing），
 * 那么我们既不能在 Italk 接口里增加 sing() 方法，又不能在每个前端都增加 sing 方法，我们只有增加一个代理类 talkProxy ，
 * 这个代理类里实现 talk 和 sing 方法，然后在需要 sing 方法的客户端调用代理类即可，
 * <p>
 * 这也是实现动态代理的方式，是通过实现（implements）的方式来实现的，
 * 这种方法的优点，在编码时，代理逻辑与业务逻辑互相独立，各不影响，没有侵入，没有耦合
 */
