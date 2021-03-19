package study.springStudy.代理.cgLib代理;

/**
 * @ClassName TestService
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
//我们实现一个业务类，不实现任何的接口

/**
 * 业务类，
 */
public class TestService {
    public TestService() {
        System.out.println("TestService的构造");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println("TestService:sayOthers>>" + name);
        return null;
    }

    public void sayHello() {
        System.out.println("TestService:sayHello");
    }

}
