package com.lj.base.cache;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：根据类生成一个字符串,可用于Map对象的key
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class FullKeyGenerator implements KeyGenerator {

	/* (non-Javadoc)
	 * @see org.springframework.cache.interceptor.KeyGenerator#generate(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();

		String className = target.getClass().getName();
		sb.append(className);
		sb.append(".");
		String methodName = method.getName();
		sb.append(methodName);
		sb.append("(");
		for (Object param : params) {
			if (param != null) {
				sb.append(param.toString());
			} else {
				sb.append("null");
			}
			sb.append(",");
		}
		sb.append(")");
		//System.out.println("FullKeyGenerator:" + sb.toString());
		return sb.toString();
	}

}
