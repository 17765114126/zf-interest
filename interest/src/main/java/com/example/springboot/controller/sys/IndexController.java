package com.example.springboot.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * SpringBoot拦截器实现简单的登录认证模块
 *
 * @ClassName LoginController
 * @Author zhaofu
 * @Date 2019/8/27
 * @Version V1.0
 **/
@Controller
public class IndexController {

    // 预先设置好的正确的用户名和密码，用于登录验证
    private String rightUserName = "admin";
    private String rightPassword = "123";
    /**
     * 登录
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    /**
     * 登录校验
     * @param request
     * @return
     */
    @RequestMapping("/login1")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (null == username || null == password) {
            return "login";
        }
        if (password.equals(rightPassword)) {
            // 校验通过时，在session里放入一个标识
            // 后续通过session里是否存在该标识来判断用户是否登录
            request.getSession().setAttribute("loginName", "admin");
            return "redirect:/hello";
        }
        // 前端传回的密码实际为用户输入的：用户名（小写）+ 密码（小写）组合的字符串生成的md5值
        // 此处先通过后台保存的正确的用户名和密码计算出正确的md5值，然后和前端传回来的作比较
  /*      String md5info = rightUserName.toLowerCase() + rightPassword.toLowerCase( );
        String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
        if (!password.equals(realPassword)) {
            return "login";
        }*/

        //return "redirect:/hello";
        return "login";
    }


    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
}