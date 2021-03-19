package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = -1L;

    Long id;

    String name;

    String comment;


    List<SysPermission> permissions;

}
