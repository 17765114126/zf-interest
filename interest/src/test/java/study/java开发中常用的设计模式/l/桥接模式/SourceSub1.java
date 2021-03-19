package study.java开发中常用的设计模式.l.桥接模式;


public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}