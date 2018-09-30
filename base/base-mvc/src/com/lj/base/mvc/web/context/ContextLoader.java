package com.lj.base.mvc.web.context;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * 
 * 
 * 类说明：
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
public class ContextLoader extends org.springframework.web.context.ContextLoader {	
	
	/** The logger. */
	private static  Logger logger = LoggerFactory.getLogger(ContextLoader.class);		
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoader#customizeContext(javax.servlet.ServletContext, org.springframework.web.context.ConfigurableWebApplicationContext)
	 */
	public void customizeContext(
			ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {		

		String contextConfigLocation =null;
		if(servletContext!=null){
			contextConfigLocation=servletContext
				.getInitParameter(CONFIG_LOCATION_PARAM);
		}
		
		InitResouece.getConfigLocations(contextConfigLocation);		
		String[] configLocations = InitResouece.getConfigLocations(contextConfigLocation);		
		applicationContext.setConfigLocations(configLocations);

		
		

	}

}
