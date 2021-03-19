package study.springStudy.Ioc2;

import study.springStudy.Ioc.Part;

/**
 * @ClassName A
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 **/
@Part("testA")
class A{

//    @InjectByType
    private B b;

    public A(){

    }

    public B getB() {
        return b;
    }
}
