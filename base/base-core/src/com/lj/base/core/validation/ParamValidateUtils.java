package com.lj.base.core.validation;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.Field;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;

/**
 * 
 * 
 * 类说明：参数有效性校验
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
public class ParamValidateUtils {

	/**
	 * 方法说明：校验参数有效性.
	 *
	 * @param param 	参数对象
	 * @param clazz 	参数类型
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validate(Object param, Class<?> clazz) throws IllegalArgumentException, Exception {
		// 1、校验参数对象有效性
		AssertUtils.notNull(param, "参数为空");
		
		// 对象值，不再校验对象属性
		if(param == null) {
			return;
		}
		
		// 2、校验参数对象属性有效性
		Field[] fields = null;
		TsfaField tsfaField = null;
		boolean access = false;
		do {
			fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				tsfaField = field.getAnnotation(TsfaField.class);
				if (null == tsfaField) {
					continue;
				}
				access = field.isAccessible();		// field的原始修饰类型
				field.setAccessible(true);		// 设置 field 可访问
				Object fieldObject = field.get(param);
				validateNotNull(fieldObject, field, tsfaField);		// 校验参数是否为空	
				validateMinLength(fieldObject, field, tsfaField);	// 校验字符串类型参数最小长度
				validateMaxLength(fieldObject, field, tsfaField);	// 校验字符串类型参数最大长度
				validateMinValue(fieldObject, field, tsfaField);	// 校验长整型参数最小值
				validateMaxValue(fieldObject, field, tsfaField);	// 校验长整型参数最大值
				field.setAccessible(access);	// 还原当前 field 的修饰类型，避免被非法使用
			}
		} while ((clazz = clazz.getSuperclass()) != null);
	}
	
	/**
	 * 方法说明：校验参数是否为空.
	 *
	 * @param fieldObject the field object
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validateNotNull(Object fieldObject, Field field, TsfaField tsfaField) {
		if(tsfaField.notNull()) {
			AssertUtils.notNull(fieldObject, buildComment(field, tsfaField) + "为空");
			if(fieldObject instanceof String && ((String) fieldObject).length() == 0) {
				throw new IllegalArgumentException(buildComment(field, tsfaField) + "为空");
			}
		}
	}
	
	/**
	 * 方法说明：校验字符串类型参数最小长度.
	 *
	 * @param fieldObject the field object
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validateMinLength(Object fieldObject, Field field, TsfaField tsfaField) {
		if(fieldObject instanceof String && tsfaField.minLength() > 0) {
			AssertUtils.notNull(fieldObject, buildComment(field, tsfaField) + "长度必须大于或等于" + tsfaField.minLength() + "，但实际长度为0");
			String value = ((String) fieldObject);
			int length = value.length();
			if(length < tsfaField.minLength()) {
				throw new IllegalArgumentException(buildComment(field, tsfaField) + "长度必须大于或等于" + tsfaField.minLength() + "，但实际长度为" + length + " [" + value + "]");
			}
		}
	}
	
	/**
	 * 方法说明：校验字符串类型参数最大长度.
	 *
	 * @param fieldObject the field object
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validateMaxLength(Object fieldObject, Field field, TsfaField tsfaField) {
		if(fieldObject instanceof String && tsfaField.maxLength() > 0) {
			String value = fieldObject == null ? null : ((String) fieldObject);
			int length = value == null ? 0 : value.length();
			if(length > tsfaField.maxLength()) {
				throw new IllegalArgumentException(buildComment(field, tsfaField) + "长度必须小于或等于" + tsfaField.maxLength() + "，但实际长度为" + length + " [" + value + "]");
			}
		}
	}
	
	/**
	 * 方法说明：校验长整型参数最小值.
	 *
	 * @param fieldObject the field object
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validateMinValue(Object fieldObject, Field field, TsfaField tsfaField) {
		if(fieldObject instanceof Long && tsfaField.minValue() != Long.MIN_VALUE) {
			AssertUtils.notNull(fieldObject, buildComment(field, tsfaField) + "必须大于或等于" + tsfaField.minValue() + "，但实际值为" + fieldObject);
			Long value = (Long) fieldObject;
			if(value < tsfaField.minValue()) {
				throw new IllegalArgumentException(buildComment(field, tsfaField) + "必须大于或等于" + tsfaField.minValue() + "，但实际值为" + value);
			}
		}
	}
	
	/**
	 * 方法说明：校验长整型参数最大值.
	 *
	 * @param fieldObject the field object
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static final void validateMaxValue(Object fieldObject, Field field, TsfaField tsfaField) {
		if(fieldObject instanceof Long && tsfaField.maxValue() != Long.MAX_VALUE) {
			AssertUtils.notNull(fieldObject, buildComment(field, tsfaField) + "必须小于或等于" + tsfaField.maxValue() + "，但实际值为" + fieldObject);
			Long value = (Long) fieldObject;
			if(value > tsfaField.maxValue()) {
				throw new IllegalArgumentException(buildComment(field, tsfaField) + "必须小于或等于" + tsfaField.maxValue() + "，但实际值为" + value);
			}
		}
	}
	
	/**
	 * Builds the comment.
	 *
	 * @param field the field
	 * @param tsfaField the tsfa field
	 * @return the string
	 */
	private static final String buildComment(Field field, TsfaField tsfaField) {
		return StringUtils.isEmpty(tsfaField.comment()) ? field.getName() : tsfaField.comment();
	}
}
