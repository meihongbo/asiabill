package com.lj.base.mvc.bean;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Date;

import org.apache.commons.beanutils.Converter;

import com.lj.base.core.util.DateUtils;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：日期转换函数，BeanUtils原来的处理方式有问题，必须替换
 *   
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class DateConverter implements Converter { 
    
    /* (non-Javadoc)
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
	public Object convert(Class type, Object value) {
	    if(value == null) {
	        return null;
	    } 
	    else if(value instanceof Date) {
	        return value;
	    } 
	    else if(value instanceof Long) {
	        Long longValue = (Long) value;
	        return new Date(longValue.longValue());
	    }
	    //sqlDate转换
	    else if(value instanceof java.sql.Date){
	    	java.sql.Date sqlDate =( java.sql.Date)value;
	    	return new Date(sqlDate.getTime());
	    }
	    else{ 
	    	try {
	    		String dateValueStr = value.toString();
	    		//按日期长度判断
	    		if(dateValueStr.length()==8)
	    			return DateUtils.parseDate(dateValueStr,DateUtils.PATTERN_yyyyMMdd);
	    		else if(dateValueStr.length()==10){
	    			return DateUtils.parseDate(dateValueStr,DateUtils.PATTERN_yyyy_MM_dd);
	    		}else if(dateValueStr.length()==14){
	    			return DateUtils.parseDate(dateValueStr,DateUtils.PATTERN_yyyyMMddHHmmss);
	    		}else if(dateValueStr.length()==19){
	    			return DateUtils.parseDate(dateValueStr,DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss);
	    		}else{//当作long型解析
	    			return new Date(Long.valueOf(dateValueStr));
	    		}
			} catch (Throwable e) {
				return null;
			}
	    } 
	     
    }
}