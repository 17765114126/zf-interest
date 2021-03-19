package com.example.springboot.config.Shiro.security;

import com.example.springboot.model.sys.SysPermission;
import com.example.springboot.model.sys.SysRole;
import com.example.springboot.model.sys.SysUser;
import com.example.springboot.service.sys.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //权限配置
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser userInfo = (SysUser) principals.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoles()) {
            authorizationInfo.addRole(role.getName());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证：主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //https://www.geek-share.com/detail/2777305691.html
        //得到用户名
        String mobile = (String) token.getPrincipal();
        //可能是因为在UsernamePasswordToken内部将密码部分转为字符数组了，所以要这样取String password = new String((char[]) token.getCredentials()); ，
        //得到密码
        String password = new String((char[]) token.getCredentials());
        SysUser sysUser = sysUserService.getUserByMobile(mobile);
        if (sysUser == null) {
            //抛出UnknownAccountException
            return null;
        }
        //账户禁用
        if (sysUser.getStatus() == 2) {
            throw new LockedAccountException();
        }
        //此密码为明文，然后给密码加密String md5Pwd = new Md5Hash(password, username).toHex();，然后再将md5Pwd放到SimpleAuthenticationInfo中
        String md5Pwd = new Md5Hash(password, sysUser.getSalt()).toHex();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                sysUser,
                //密码
                md5Pwd,
                //salt=username+salt
                ByteSource.Util.bytes(sysUser.getSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

}
