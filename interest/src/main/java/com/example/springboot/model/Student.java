package com.example.springboot.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Date: 2019/9/26
 * @Author: zhaofu
 * @Description: 学生类
 **/

public class Student implements Serializable {
    private static final long serialVersionUID = 3577522325645247161L;
    //    @Value("昴")
//    private String name = "昴";
//    @Value("23")
//    private Integer age = 23;
    public Student() {
    }
    public Student(String name,Integer age) {
        this.setName(name);
        this.setAge(age);
    }
    private Integer age;
    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        System.out.println("Age : " + age);
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name : " + name);
        return name;
    }

    public void printThrowException() {
        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }

}
