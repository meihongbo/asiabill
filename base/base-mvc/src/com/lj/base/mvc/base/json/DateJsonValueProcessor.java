package com.lj.base.mvc.base.json;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.lj.base.core.util.DateUtils;

/**
 * 
 * 
 * 类说明：格式化日期格式，按字符串yyyy-MM-dd HH:mm:ss格式输出
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
public class DateJsonValueProcessor implements JsonValueProcessor{

	//private static final String PATTREN_00_00_00 = "00:00:00";

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		
		return process(value);
	}
	
	 /**
 	 * 方法说明：process.
 	 *
 	 * @param value the value
 	 * @return the Object
 	 */
 	private Object process(Object value) {  
	        if (value == null) {  
	            return "";  
	        } else {
	         String formatDate = DateUtils.formatDate((Date)value, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss);
			//if(formatDate.indexOf(PATTREN_00_00_00) == -1)//时间为00:00:00则取日期
			//	return formatDate.subSequence(0, 10);
	         return formatDate;
	        }  
	    }  

}
