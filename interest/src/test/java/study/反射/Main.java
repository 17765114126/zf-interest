package study.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    /**
     * 为了看清楚Java反射部分代码，所有异常我都最后抛出来给虚拟机处理！
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Demo1===============================================");
        //Demo1.  通过Java反射机制得到类的包名和类名
        Demo1();

        System.out.println("Demo2===============================================");
        //Demo2.  验证所有的类都是Class类的实例对象
        Demo2();

        System.out.println("Demo3===============================================");
        //Demo3.  通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
        Demo3();

        System.out.println("Demo4===============================================");
        //Demo4:  通过Java反射机制得到一个类的构造函数，并实现构造带参实例对象
        Demo4();

        System.out.println("Demo5===============================================");
        //Demo5:  通过Java反射机制操作成员变量, set 和 get
        Demo5();

        System.out.println("Demo6===============================================");
        //Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
        Demo6();

        System.out.println("Demo7===============================================");
        //Demo7: 通过Java反射机制调用类中方法
        Demo7();

        System.out.println("Demo8===============================================");
        //Demo8: 通过Java反射机制获得类加载器
        Demo8();

    }

    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    public static void Demo1() {
        Person person = new Person();
        System.out.println("包名:" + person.getClass().getPackage().getName());
        System.out.println("完整类名:" + person.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     */
    public static void Demo2() throws ClassNotFoundException {
        //定义两个类型都未知的Class , 设置初值为null, 看看如何给它们赋值成Person类
        Class<?> class1 = null;
        Class<?> class2 = null;

        //写法1, 可能抛出 ClassNotFoundException [多用这个写法]
        class1 = Class.forName("study.反射.Person");
        System.out.println("(写法1) 包名: " + class1.getPackage().getName() + "，"
                + "完整类名: " + class1.getName());

        //写法2
        class2 = Person.class;
        System.out.println("(写法2) 包名: " + class2.getPackage().getName() + "，"
                + "完整类名: " + class2.getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     */
    public static void Demo3() throws Exception {
        Class<?> class1 = null;
        class1 = Class.forName("study.反射.Person");
        //由于这里不能带参数，所以你要实例化的这个类Person，一定要有无参构造函数哈～
        Person person = (Person) class1.newInstance();
        person.setAge(20);
        person.setName("张三");
        System.out.println("name: " + person.getName() + " age: " + person.getAge());
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     */
    public static void Demo4() throws Exception {
        Class<?> class1 = null;
        Person person1 = null;
        Person person2 = null;

        class1 = Class.forName("study.反射.Person");
        //得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();
        //无参构造函数
        person1 = (Person) constructors[0].newInstance();
        person1.setAge(30);
        person1.setName("李四");
        //有参构造函数
        person2 = (Person) constructors[1].newInstance(20, "王五");

        System.out.println("name: " + person1.getName() + " age: " + person1.getAge());
        System.out.println("name: " + person2.getName() + " age: " + person2.getAge());
    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     */
    public static void Demo5() throws Exception {
        Class<?> class1 = null;
        class1 = Class.forName("study.反射.Person");
        Object obj = class1.newInstance();

        //获取com.jincou.study.Person.name的属性名
        Field personNameField = class1.getDeclaredField("name");
        personNameField.setAccessible(true);
        personNameField.set(obj, "张三");

        System.out.println("修改属性之后得到属性变量的值：" + personNameField.get(obj));
    }

    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     */
    public static void Demo6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("study.反射.SuperMan");

        //取得父类名称
        Class<?> superClass = class1.getSuperclass();
        System.out.println("SuperMan类的父类名: " + superClass.getName());

        //取得属性信息
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("类中的成员: " + fields[i]);
        }

        //取得类方法
        Method[] methods = class1.getDeclaredMethods();

        // 取得SuperMan类的方法
        System.out.println("函数名：" + methods[0].getName());
        System.out.println("函数代码写法： " + methods[0]);

        //取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到哈
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName());
        }

    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     */
    public static void Demo7() throws Exception {
        Class<?> class1 = null;
        class1 = Class.forName("study.反射.SuperMan");

        //调用fly()方法
        Method method = class1.getMethod("fly");
        method.invoke(class1.newInstance());

        //调用walk(int m)方法
        method = class1.getMethod("walk", int.class);
        method.invoke(class1.newInstance(), 100);
    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     */
    public static void Demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("study.反射.SuperMan");
        String nameString = class1.getClassLoader().getClass().getName();

        System.out.println("类加载器类名: " + nameString);
    }

}


/**
 * Person类
 */
class Person {
    private int age;
    private String name;

    public Person() {

    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//SuperMan类
class SuperMan extends Person implements ActionInterface {
    private boolean BlueBriefs;

    public void fly() {
        System.out.println("我是fly方法......");
    }

    public boolean isBlueBriefs() {
        return BlueBriefs;
    }

    public void setBlueBriefs(boolean blueBriefs) {
        BlueBriefs = blueBriefs;
    }

    @Override
    public void walk(int m) {
        System.out.println("我是walk方法，我的int值为：" + m);
    }
}

//ActionInterface接口
interface ActionInterface {
    public void walk(int m);
}


