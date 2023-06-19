/*
 * baixiong.com Inc. Copyright (c) 1999-2001 All Rights Reserved.
 */
package com.example.springboot.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年7月19日 账号类型判断
 */
public class AccountUtil {

	/**
	 * 
	 * @param userName
	 * @return 1:邮箱  2：手机   3：账号为空  0：不符合
	 */
	public static int checkAccount(String userName) {
		if(StringUtils.isBlank(userName))
			return 0;
		if (isEmail(userName)) {
			return 1;
		} else if (isMobile(userName)) {
			return 2;
		}
		return 0;
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param userName
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String userName) {
		if (null == userName || "".equals(userName))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(userName);
		return m.matches();
	}

	/**
	 * 检查手机号码是否合法
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles) {
		if (StringUtils.isBlank(mobiles))
			return false;
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();

	}


	/**
	 * 手机号正则表达式
	 */
	@Test
	public void Test10() {
		boolean matches = "17765114126".matches("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
		System.out.println(matches);
	}

	//    同时支持手机号、用户名、邮箱登录
	public static void main(String[] args) {
		String e = "15315315326@163.com";
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String ph = "^[1][3578]\\d{9}$";

		if (e.matches(em)) {
			//邮箱登录
			System.out.println("邮箱");
		} else if (e.matches(ph)) {
			//手机号登录
			System.out.println("手机号");
		} else {
			//就是用户名登录
			System.out.println("用户名");
		}
	}

}
