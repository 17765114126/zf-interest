package com.example.springboot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MyLogClass
 * @Author zhaofu
 * @Date 2019/10/18
 * @Version V1.0
 **/
@Data
public class MyLogClass implements Serializable {

    private String tblId;

    private String productId;

    private String qdNumber;

}
