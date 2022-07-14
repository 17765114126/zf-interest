package study.java开发中常用的设计模式.i.装饰模式.装饰;

public class Test {

    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md1.eat();

        md2.setPerson(man);
        md2.eat();
    }
}
