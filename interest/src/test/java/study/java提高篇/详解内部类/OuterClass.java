package study.java提高篇.详解内部类;

import lombok.Data;

/**
 * @ClassName OuterClass
 * @Author zhaofu
 * @Date 2020/7/31
 * @Version V1.0
 **/
@Data
public class OuterClass {
    private String name;
    private int age;


    public class InnerClass {
        public InnerClass() {
            name = "chenssy";
            age = 23;
        }

        public void display() {
            System.out.println("name：" + getName() + "   ;age：" + getAge());
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
    }
}