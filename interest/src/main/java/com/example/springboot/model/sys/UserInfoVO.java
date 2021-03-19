package com.example.springboot.model.sys;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserInfoVO implements Serializable {

    private String id;
    private String token;
    private String username;
    private String mobile;
    private String account;
    private String roleName;
}
