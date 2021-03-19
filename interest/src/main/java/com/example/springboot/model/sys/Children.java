package com.example.springboot.model.sys;

import lombok.Data;

/**
 * Created by chen on 2019/7/2.
 */
@Data
public class Children {
    private int id;

    private Integer pid;

    private String name;

//    private boolean IsHide;

    private String path;

    private String iconCls;

    private Meta meta;

    private Integer sort;

    private String children;

}
