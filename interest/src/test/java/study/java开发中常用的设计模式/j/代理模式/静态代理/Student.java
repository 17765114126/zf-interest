package study.java开发中常用的设计模式.j.代理模式.静态代理;

public class Student implements Teacher {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name + "上交班费100元");
    }
}
