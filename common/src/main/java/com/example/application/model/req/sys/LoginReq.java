package com.example.application.model.req.sys;

import lombok.Data;

@Data
public class LoginReq {

    String username;

    String password;

    String code;

    String mobile;

}
