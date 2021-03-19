package com.example.springboot.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName Person
 * @Author zhaofu
 * @Date 2020/3/3
 * @Version V1.0
 **/
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {
    @Value("name")
    private String name;
    private Integer age;
    private String sex;
    public Person() {
    }

    public Person(String name, Integer age, String sex) {
        System.out.println(" 对象被创建了.............");
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}

