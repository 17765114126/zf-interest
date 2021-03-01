package study.jvm;

/**
 * @ClassName 类的加载顺序
 * @Author zhaofu
 * @Date 2020/10/22
 * @Version V1.0
 * <p>
 * 类的实例化顺序
 * <p>
 * 1． 父类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
 * <p>
 * 2． 子类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
 * <p>
 * 3． 父类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
 * <p>
 * 4． 父类构造方法
 * <p>
 * 5． 子类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
 * <p>
 * 6． 子类构造方法
 * <p>
 * <p>
 * 3--Dervied tell name: null
 * 4--Dervied print name: null
 * 3--Dervied tell name: Java3y
 * 4--Dervied print name: Java3y
 **/
public class 类的加载顺序 extends Base {
    private String name = "Java3y";

    public 类的加载顺序() {
        tellName();

        printName();
    }

    public void tellName() {
        System.out.println("3--Dervied tell name: " + name);
    }

    public void printName() {
        System.out.println("4--Dervied print name: " + name);
    }


    public static void main(String[] args) {
        new 类的加载顺序();
    }

}

class Base {

    private String name = "公众号";

    public Base() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("1--Base tell name: " + name);
    }

    public void printName() {
        System.out.println("2--Base print name: " + name);
    }

}