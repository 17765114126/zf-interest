package com.example.springboot.config.Shiro.security;

import com.example.springboot.model.sys.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
public class SecurityUtil {

    public static String encodePassword(String password, String salt) {
        return new SimpleHash(SecurityConstants.HASH_ALGORITHMNAME, password, salt, SecurityConstants.HASH_ITERATIONS).toString();
    }

    public static SysUser sysUser() {
        return (SysUser) org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
    }

}
