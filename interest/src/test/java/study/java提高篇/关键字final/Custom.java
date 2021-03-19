package study.java提高篇.关键字final;

/**
 * @ClassName Custom
 * @Author zhaofu
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class Custom extends Person {
    Custom(String name) {
        super(name);
    }

    public void method1() {
        System.out.println("Person's  method1....");
    }

    //        Cannot override the final method from person：子类不能覆盖父类的final方法
    public void method2() {
        System.out.println("Person's method2...");
    }

    public void test(final int i) {
//        i++;    // ---final参数不可改变
        System.out.println(i);
    }

    public void test(final Person p) {
//        p = new Person();    //--final参数不可变
        p.setName("chenssy");
    }
}
