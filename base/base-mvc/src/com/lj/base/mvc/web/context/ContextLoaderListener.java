package com.lj.base.mvc.web.context;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import javax.servlet.ServletContextEvent;


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
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {		
		super.contextInitialized(event);		
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#createContextLoader()
	 */
	public ContextLoader createContextLoader() {	
		return new ContextLoader();
	}

}
