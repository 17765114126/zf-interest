package study.java提高篇.使用序列化实现对象的拷贝;

import javax.validation.constraints.Email;

/**
 * @ClassName Person
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Person implements Cloneable{
    /** 姓名 **/
    private String name;

    /** 电子邮件 **/
    private Email email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Person(String name, Email email){
        this.name  = name;
        this.email = email;
    }

    public Person(String name){
        this.name = name;
    }

    protected Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }
}


