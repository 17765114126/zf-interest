package com.example.springboot.config.Shiro.security;

import java.util.Arrays;
import java.util.List;

public interface SecurityConstants {

    String HASH_ALGORITHMNAME = "md5";

    int HASH_ITERATIONS = 1;

    int SESSION_TIMEOUT = 7200*1000;

     /**
      * 绕过权限可直接访问的页面
      * */
    List<String> PATH_WHITE_LIST = Arrays.asList("/login","/swagger-ui.html", "/forgetPwdMobileMessage", "/password/updateDefaultPwd");

}
