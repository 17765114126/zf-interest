package study.java开发中常用的设计模式.d.抽象工厂模式.抽象工厂;

public abstract class AbstractFactory {
    /**
     * 生产人类
     */
    public abstract NvWa createNvWa();

    /**
     * 生产汽车（这个类就不具体些了，理解就好）
     */
    public abstract Car createCar();
}