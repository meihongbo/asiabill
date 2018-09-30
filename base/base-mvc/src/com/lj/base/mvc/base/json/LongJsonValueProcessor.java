package com.lj.base.mvc.base.json;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


/**
 * 
 * 
 * 类说明：Long 处理时 转化为String处理，防止页面传输超大LONG值时出错
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
public class LongJsonValueProcessor implements JsonValueProcessor{

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
	         return String.valueOf(value);
	        }  
	    }  

}
