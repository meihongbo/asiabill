package com.lj.base.mvc.web;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：获取web容器 Spring框架下的ApplicationContext
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class WebBeanLoader {
	
	/**
	 * Gets the bean.
	 *
	 * @param beanName the bean name
	 * @return the bean
	 */
	public static Object getBean(String beanName) {
		WebApplicationContext applicationContext=ContextLoader.getCurrentWebApplicationContext();
		Object obj = applicationContext.getBean(beanName);
		return obj;
	}
}
