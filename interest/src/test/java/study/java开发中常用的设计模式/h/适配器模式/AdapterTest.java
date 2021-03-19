package study.java开发中常用的设计模式.h.适配器模式;
/**
 *
 *我们接着讨论设计模式，上面讲完了5种创建型模式，从这里开始，讲下7种结构型模式：
 * 适配器模式、装饰模式、代理模式、外观模式、桥接模式、组合模式、享元模式。其中对象的适配器模式是各种模式的起源
 *
 */

/**
 *
 * 适配器模式(adapter)
 *
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 *
 * 核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable，通过Adapter类，将Source的功能扩展到Targetable里，
 * 看代码
 */
public class AdapterTest {

    public static void main(String[] args) {
        //类的适配器模式
        Sourceable target1 = new Adapter();
        target1.method1();
        target1.method2();

        //对象的适配器模式
        Source source = new Source();
        Sourceable target = new Wrapper(source);
        target.method1();
        target.method2();
        //接口的适配器模式。
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();
    }
/**
 *
 *  讲了这么多，总结一下三种适配器模式的应用场景：
 *
 * 类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
 *
 * 对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 *
 * 接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 */
}
