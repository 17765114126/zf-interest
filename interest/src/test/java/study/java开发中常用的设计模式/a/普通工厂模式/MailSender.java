package study.java开发中常用的设计模式.a.普通工厂模式;

/**
 * 其次，创建实现类：
 * */
public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mail sender!");
    }
}