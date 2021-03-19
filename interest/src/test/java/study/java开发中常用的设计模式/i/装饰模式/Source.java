package study.java开发中常用的设计模式.i.装饰模式;


public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}