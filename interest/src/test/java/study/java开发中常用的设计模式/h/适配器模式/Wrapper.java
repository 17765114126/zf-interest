package study.java开发中常用的设计模式.h.适配器模式;


/**
 *
 *对象的适配器模式
 *
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
 *
 * 输出与第一种一样，只是适配的方法不同而已。
 */
public class Wrapper implements Sourceable {

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    @Override
    public void method1() {
        source.method1();
    }
}
