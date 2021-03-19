/*
 * baixiong.com Inc. Copyright (c) 1999-2001 All Rights Reserved.
 */
package com.example.springboot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

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

}
