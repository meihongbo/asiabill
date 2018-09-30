package com.lj.base.mvc.web.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.lj.base.core.util.StringUtils;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class CookieUtils {
	
	/**
	 * *
	 * 获取Cookie的值.
	 *
	 * @param cookieName the cookie name
	 * @param hrs the hrs
	 * @return the cookie value
	 */
	public static String getCookieValue(String cookieName,HttpServletRequest hrs){
		Cookie [] cookies = hrs.getCookies();
		if(cookies!=null){
			//遍历Cookies获取对应名称的cookie
			for(Cookie cookie : cookies){
				if(cookieName.equals(cookie.getName()))
					return cookie.getValue();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
