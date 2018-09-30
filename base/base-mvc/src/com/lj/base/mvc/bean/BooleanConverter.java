package com.lj.base.mvc.bean;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import org.apache.commons.beanutils.Converter;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：Boolean转换函数，BeanUtils原来的处理方式有问题，必须替换
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class BooleanConverter implements Converter { 
    
    /* (non-Javadoc)
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean convert(Class type, Object value) {
	    if(value == null) {
	        return null;
	    } 
	    else{ 
	    	try {
	    		String booleanValueStr = value.toString();
	    		return Boolean.valueOf(booleanValueStr);
			} catch (Throwable e) {
				return null;
			}
	    } 
	     
    }
}