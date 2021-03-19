package study.java开发中常用的设计模式.d.抽象工厂模式;


/**
 * 其次，创建实现类：
 * */
public class SmsSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
