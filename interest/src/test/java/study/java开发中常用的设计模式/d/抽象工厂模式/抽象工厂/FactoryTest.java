package study.java开发中常用的设计模式.d.抽象工厂模式.抽象工厂;

public class FactoryTest {
    public static void main(String[] args) {

        //工厂1生产男人和宝马
        AbstractFactory factory1 = new Factory1();
        Man man = (Man) factory1.createNvWa();
        man.say();
        Bmw bmw = (Bmw) factory1.createCar();
        bmw.say();
        //工厂2生产女人和奔驰
        AbstractFactory factory2 = new Factory2();
        Women women = (Women) factory2.createNvWa();
        women.say();
        Bc bc = (Bc) factory2.createCar();
        bc.say();
    }
}