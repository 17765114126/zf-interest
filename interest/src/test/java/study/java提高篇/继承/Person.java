package study.java提高篇.继承;

/**
 * @ClassName Person
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Person {
    protected String name;
    protected int age;
    protected String sex;

    Person(String name){
        System.out.println("Person Constrctor-----" + name);
    }
}


