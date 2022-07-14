package study.java开发中常用的设计模式.d.抽象工厂模式.抽象工厂;

public class Factory2 extends AbstractFactory {

    @Override
    public NvWa createNvWa() {
        return new Women();
    }

    @Override
    public Car createCar() {
        return new Bc();
    }
}