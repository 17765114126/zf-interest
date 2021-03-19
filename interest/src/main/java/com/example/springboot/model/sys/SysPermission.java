package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = -1L;

    Long id;

    String comment;

    String name;

    Integer type;

    String url;

}
