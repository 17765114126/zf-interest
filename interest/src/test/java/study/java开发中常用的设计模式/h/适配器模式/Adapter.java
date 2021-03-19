package study.java开发中常用的设计模式.h.适配器模式;


/**
 *
 * Adapter类继承Source类，实现Targetable接口，
 *这样Targetable接口的实现类就具有了Source类的功能。
 *
 */
public class Adapter extends Source implements Sourceable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
