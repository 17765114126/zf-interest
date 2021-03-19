package study.java开发中常用的设计模式.c.静态工厂模式;


/**
 * 最后，建工厂类：
 * 将多个工厂模式的代码做下修改，改动下SendFactory类就行
 *
 * */
public class SendFactory {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }
}
