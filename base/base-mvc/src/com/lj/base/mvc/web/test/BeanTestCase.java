package com.lj.base.mvc.web.test;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lj.base.mvc.web.context.InitResouece;

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
public class BeanTestCase<V> {

	/** The bean factory. */
	private static ClassPathXmlApplicationContext beanFactory;

	/**
	 * Inits the bean.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public  static void initBean() throws Exception {
		if(beanFactory==null){
		String[] configLocations = InitResouece.getConfigLocations(null);	
		beanFactory = new ClassPathXmlApplicationContext(configLocations);
		}
	}

	/**
	 * Gets the bean.
	 *
	 * @param beanName the bean name
	 * @return the bean
	 */
	protected V getBean(String beanName) {
		return (V) beanFactory.getBean(beanName);
	}	
}

