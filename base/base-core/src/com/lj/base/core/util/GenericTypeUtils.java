package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.ParameterizedType;

/**
 * 
 * 
 * 类说明：泛型工具包
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
@SuppressWarnings("rawtypes")
public class GenericTypeUtils {
	
	/**
	 * *
	 * 获取实例对象的泛型参数具体类型
	 * 类定义泛型有几个，则为数组.
	 *
	 * @param obj the obj
	 * @param idx the idx
	 * @return the request generic type
	 */
	public static Class getRequestGenericType(Object obj,int idx){ 
		return(Class) ((ParameterizedType)obj.getClass().getGenericSuperclass()).getActualTypeArguments()[idx];
	}
	
	/**
	 * *
	 * 获取实例对象的泛型参数具体类型
	 * 类定义泛型有几个，则为数组.
	 *
	 * @param obj the obj
	 * @return the request generic type
	 */
	public static Class getRequestGenericType(Object obj){ 
		return(Class) ((ParameterizedType)obj.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
