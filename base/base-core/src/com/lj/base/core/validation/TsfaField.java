package com.lj.base.core.validation;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 类说明：字段定义，用于约束参数有效性
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
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TsfaField {

	/**
	 * 方法说明：字段别名.
	 *
	 * @return the string
	 */
	String name() default "";
	
	/**
	 * 方法说明：字段说明.
	 *
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	String comment() default "";
	
	/**
	 * 方法说明：是否允许字段为空，默认允许为空<br>
	 * 1、false 允许为空
	 * 2、true  不允许为空.
	 *
	 * @return true, if not null
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	boolean notNull() default false;
	
	/**
	 * 方法说明：字段串类型最小长度，默认值为-1，即无需判断字段符串最小长度.
	 *
	 * @return the int
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	int minLength() default -1;
	
	/**
	 * 方法说明：字段串类型最大长度，默认值为-1，即无需判断字段符串最大长度.
	 *
	 * @return the int
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	int maxLength() default -1;
	
	/**
	 * 方法说明：长整型最小值，默认为Long.MIN_VALUE，即无需判断长整型最小值
	 *
	 * @return the long
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	long minValue() default Long.MIN_VALUE;

	/**
	 * 方法说明：长整型最大值，默认为Long.MAX_VALUE，即无需判断长整型最大值
	 *
	 * @return the long
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	long maxValue() default Long.MAX_VALUE;
}