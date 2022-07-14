package study.java开发中常用的设计模式.j.代理模式.静态代理;

public class ProxyTest {
    public static void main(String[] args) {
        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Teacher zhangsan = new Student("张三一");

        //生成代理对象，并将张三传给代理对象
        Teacher monitor = new StudentsProxy(zhangsan);

        //班长代理上交班费
        monitor.giveMoney();
    }
}
