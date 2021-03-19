package study.springStudy.Ioc2;

import org.junit.Test;
import study.springStudy.Ioc.DI;
import study.springStudy.Ioc.IOCContainer;

/**
 * @ClassName IOCContainer
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 * 链接：https://juejin.im/post/5e561077518825492c0504fd
 **/
public class IOCContainerTest {

    @Test
    public void test(){
        IOCContainer container = new IOCContainer();
        DI di = new DI(container);
        di.inject();
        System.out.println(container.getBeanByType(A.class).get(0).getB().getUuid());
        System.out.println(container.getBeanByType(B.class).get(0).getUuid());
    }

}
