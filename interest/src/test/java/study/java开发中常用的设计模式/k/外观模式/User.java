package study.java开发中常用的设计模式.k.外观模式;

/**
 *
 * 外观模式（Facade）
 *
 * 外观模式是为了解决类与类之间的依赖关系的，像spring一样，可以将类和类之间的关系配置到配置文件中，
 * 而外观模式就是将他们的关系放在一个Facade类中，降低了类类之间的耦合度，该模式中没有涉及到接口，
 * （我们以一个计算机的启动过程为例）
 *
 * */
public class User {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}