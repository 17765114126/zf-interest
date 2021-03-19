package study.java提高篇.抽象类与接口;

/**
 * @ClassName Cat
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Cat extends Animal{

    @Override
    public void cry() {
        System.out.println("猫叫：喵喵...");
    }
}
