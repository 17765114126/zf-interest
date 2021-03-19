package com.example.springboot.model.sys;

import lombok.Data;

import java.util.List;

/**
 * @program: syjcms
 * @description: 下拉框
 * @author: xk. Hu
 * @create: 2019-11-19 14:29
 */
@Data
public class BoxVO {

    private Integer id;

    private String name;

    private List<BoxVO> children;
}
