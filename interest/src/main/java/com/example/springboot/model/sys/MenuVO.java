package com.example.springboot.model.sys;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    Integer id;

    String name;

    //boolean IsHide = false;

    String path;

    String iconCls;

    MetaVO meta;

    List<MenuVO> children;

}
