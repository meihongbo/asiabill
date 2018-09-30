package com.lj.base.mvc.web.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * 
 * 
 * 类说明：SPRING WEB入参转化工具类
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
public class LongEditor extends PropertyEditorSupport { 
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override  
	public void setAsText(String text) throws IllegalArgumentException {  
		if(text == null ||text.equals(""))  
			text = "0";  
		if ( !StringUtils.hasText(text)) {  
			setValue(null);  
		}  
		else {  
			setValue(Long.parseLong(text));//这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind  
		}  
	}  
	
	
	/**
	 * Format the Date as String, using the specified DateFormat.
	 *
	 * @return the as text
	 */  
	@Override  
	public String getAsText() {  
		return getValue().toString();  
	} 
}  
