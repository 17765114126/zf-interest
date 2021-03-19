package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1L;

    Long id;

    String username;

    String password;

    String mobile;

    Integer invalid;

    Integer status;

    String salt;

    List<SysRole> roles;

}
