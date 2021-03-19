package com.example.springboot.model.sys;

import com.example.springboot.config.IocOrDI.Animal;
import com.example.springboot.config.IocOrDI.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName BussinessPerson
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 **/
@Component
public class BussinessPerson implements Person {
    @Autowired
    private Animal animal = null;

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
