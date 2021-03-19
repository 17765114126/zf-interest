package study.java提高篇.继承;

import study.java提高篇.封装.Husband;

/**
 * @ClassName Wife
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Wife {
    private String name;
    private int age;
    private String sex;
    private study.java提高篇.封装.Husband husband;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHusband(study.java提高篇.封装.Husband husband) {
        this.husband = husband;
    }

    public Husband getHusband() {
        return husband;
    }

}
