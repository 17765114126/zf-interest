package study.java开发中常用的设计模式.i.装饰模式.装饰;

public class ManDecoratorB extends Decorator {

    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }
}
