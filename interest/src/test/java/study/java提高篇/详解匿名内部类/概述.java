package study.java提高篇.详解匿名内部类;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class 概述 {
}
//在 Java 提高篇—–详解内部类中对匿名内部类做了一个简单的介绍，但是内部类还存在很多其他细节问题，所以就衍生出这篇博客。
// 在这篇博客中你可以了解到匿名内部类的使用、匿名内部类要注意的事项、如何初始化匿名内部类、匿名内部类使用的形参为何要为 final。

//一、使用匿名内部类内部类
//匿名内部类由于没有名字，所以它的创建方式有点儿奇怪。创建格式如下：


//    new 父类构造器（参数列表）|实现接口（）
//        {
//         //匿名内部类的类体部分
//        }
//在这里我们看到使用匿名内部类我们必须要继承一个父类或者实现一个接口，当然也仅能只继承一个父类或者实现一个接口。同时它也是没有 class 关键字，这是因为匿名内部类是直接使用 new 来生成一个对象的引用。当然这个引用是隐式的。


//    public abstract class Bird {
//        private String name;

//        public String getName() {
//            return name;
//        }

//        public void setName(String name) {
//            this.name = name;
//        }

//        public abstract int fly();
//    }

//    public class Test {

//        public void test(Bird bird){
//            System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
//        }

//        public static void main(String[] args) {
//            Test test = new Test();
//            test.test(new Bird() {
//
//                public int fly() {
//                    return 10000;
//                }

//                public String getName() {
//                    return "大雁";
//                }
//            });
//        }
//    }
//    ------------------
//    Output：
//    大雁能够飞 10000米
//在 Test 类中，test() 方法接受一个 Bird 类型的参数，同时我们知道一个抽象类是没有办法直接 new 的，我们必须要先有实现类才能 new 出来它的实现类实例。所以在 main 方法中直接使用匿名内部类来创建一个 Bird 实例。

//由于匿名内部类不能是抽象类，所以它必须要实现它的抽象父类或者接口里面所有的抽象方法。

//对于这段匿名内部类代码其实是可以拆分为如下形式：


//    public class WildGoose extends Bird{
//        public int fly() {
//            return 10000;
//        }
//
//        public String getName() {
//            return "大雁";
//        }
//    }

//    WildGoose wildGoose = new WildGoose();
//    test.test(wildGoose);
//在这里系统会创建一个继承自 Bird 类的匿名类的对象，该对象转型为对 Bird 类型的引用。

//对于匿名内部类的使用它是存在一个缺陷的，就是它仅能被使用一次，创建匿名内部类时它会立即创建一个该类的实例，该类的定义会立即消失，所以匿名内部类是不能够被重复使用。对于上面的实例，如果我们需要对 test() 方法里面内部类进行多次使用，建议重新定义类，而不是使用匿名内部类。

//二、注意事项
//在使用匿名内部类的过程中，我们需要注意如下几点：

//1、使用匿名内部类时，我们必须是继承一个类或者实现一个接口，但是两者不可兼得，同时也只能继承一个类或者实现一个接口。

//2、匿名内部类中是不能定义构造函数的。

//3、匿名内部类中不能存在任何的静态成员变量和静态方法。

//4、匿名内部类为局部内部类，所以局部内部类的所有限制同样对匿名内部类生效。
//
//5、匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。

//三、使用的形参为何要为 final
//参考文件：http://android.blog.51cto.com/268543/384844

//我们给匿名内部类传递参数的时候，若该形参在内部类中需要被使用，那么该形参必须要为 final。也就是说：当所在的方法的形参需要被内部类里面使用时，该形参必须为 final。

//为什么必须要为 final 呢？

//首先我们知道在内部类编译成功后，它会产生一个 class 文件，该 class 文件与外部类并不是同一 class 文件，仅仅只保留对外部类的引用。当外部类传入的参数需要被内部类调用时，从 java 程序的角度来看是直接被调用：


//    public class OuterClass {
//        public void display(final String name,String age){
//            class InnerClass{
//                void display(){
//                    System.out.println(name);
//                }
//            }
//        }
//    }
//从上面代码中看好像 name 参数应该是被内部类直接调用？其实不然，在 java 编译之后实际的操作如下：


//    public class OuterClass$InnerClass {
//        public InnerClass(String name,String age){
//            this.InnerClass$name = name;
//            this.InnerClass$age = age;
//        }

//        public void display(){
//            System.out.println(this.InnerClass$name + "----" + this.InnerClass$age );
//        }
//    }
//所以从上面代码来看，内部类并不是直接调用方法传递的参数，而是利用自身的构造器对传入的参数进行备份，自己内部方法调用的实际上时自己的属性而不是外部方法传递进来的参数。

//直到这里还没有解释为什么是 final？
// 在内部类中的属性和外部方法的参数两者从外表上看是同一个东西，但实际上却不是，
// 所以他们两者是可以任意变化的，也就是说在内部类中我对属性的改变并不会影响到外部的形参，
// 而然这从程序员的角度来看这是不可行的，毕竟站在程序的角度来看这两个根本就是同一个，
// 如果内部类该变了，而外部方法的形参却没有改变这是难以理解和不可接受的，所以为了保持参数的一致性，就规定使用 final 来避免形参的不改变。

//简单理解就是，拷贝引用，为了避免引用值发生改变，例如被外部类的方法修改等，而导致内部类得到的值不一致，于是用 final 来让该引用不可改变。

//故如果定义了一个匿名内部类，并且希望它使用一个其外部定义的参数，那么编译器会要求该参数引用是 final 的。

//四、匿名内部类初始化
//我们一般都是利用构造器来完成某个实例的初始化工作的，但是匿名内部类是没有构造器的！
// 那怎么来初始化匿名内部类呢？使用构造代码块！利用构造代码块能够达到为匿名内部类创建一个构造器的效果。


//    public class OutClass {
//        public InnerClass getInnerClass(final int     age,final String name){
//            return new InnerClass() {
//                int age_ ;
//                String name_;
//                //构造代码块完成初始化工作
//                {
//                    if(0 < age && age < 200){
//                        age_ = age;
//                        name_ = name;
//                    }
//                }
//                public String getName() {
//                    return name_;
//                }

//                public int getAge() {
//                    return age_;
//                }
//            };
//        }

//        public static void main(String[] args) {
//            OutClass out = new OutClass();

//            InnerClass inner_1 = out.getInnerClass(201, "chenssy");
//            System.out.println(inner_1.getName());

//            InnerClass inner_2 = out.getInnerClass(23, "chenssy");
//            System.out.println(inner_2.getName());
//        }
//    }