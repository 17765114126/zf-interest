package study.java开发中常用的设计模式.d.抽象工厂模式;


/**
 * 最后，建工厂类：
 * 两个工厂类
 *
 * */
public class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
