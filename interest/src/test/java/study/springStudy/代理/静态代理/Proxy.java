package study.springStudy.代理.静态代理;

/**
 * @ClassName Proxy
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
public class Proxy extends UserImpl {
    @Override
    public void system() {
        super.system();
        System.out.println("增强了，哈哈");
    }
}
