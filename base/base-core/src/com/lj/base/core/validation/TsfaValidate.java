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
 * 类说明：参数校验注解
 * 
 * 
 * <p>
 * 详细描述：被注解方法将校验参数有效性
 * 
 * @Company: 领居科技有限公司
 * @author 彭阳
 * 
 *         CreateDate: 2017年7月1日
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TsfaValidate {

}