package study.java开发中常用的设计模式.a.普通工厂模式;


/**
 *举例如下：（我们举一个发送邮件和短信的例子）
 *
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 *
 */

public class FactoryTest {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.Send();
        Sender sender1 = factory.produce("mail");
        sender1.Send();
    }
}
