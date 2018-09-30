package com.lj.base.core.validation;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * 
 * 
 * 类说明：将对象里注解{@link TsfaField}的参数解析成指定对象
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
public final class FieldParser {
	
	/**
	 * 方法说明：通过{@link TsfaField}标识将请求参数装载到Map中.
	 *
	 * @param param the param
	 * @return the tree map< string, object>
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static TreeMap<String, Object> buildTreeMap(Object param) {
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		Field[] fields = null;
		TsfaField tsfaField = null;
		boolean access = false;
		Class<?> clazz = param.getClass();
		fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			tsfaField = field.getAnnotation(TsfaField.class);
			if (null == tsfaField) {
				continue;
			}
			access = field.isAccessible();// field的原始修饰类型
			try {
				field.setAccessible(true);// 设置 field 可访问
				map.put(tsfaField.name(), field.get(param));
				field.setAccessible(access);// 还原当前 field 的修饰类型，避免被非法使用
			} catch (Exception e) {
			}
		}
		return map;
	}
	
}
