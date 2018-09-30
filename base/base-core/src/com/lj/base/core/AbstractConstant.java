package com.lj.base.core;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 
 * 类说明：读取配置文件的封装类（最好不用，用@Value注解）
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
public abstract class AbstractConstant {
	
	/** The Constant PROPERTIES_FILE_CONSTANT. */
	private final static String PROPERTIES_FILE_CONSTANT="properties/constant.properties";

	/** The Constant props. */
	protected final static Properties props = new Properties();

	static {
		
		InputStream inStream = AbstractConstant.class.getClassLoader()
				.getResourceAsStream(PROPERTIES_FILE_CONSTANT);
		
		if(inStream==null){
			throw new Error("properties file "+PROPERTIES_FILE_CONSTANT+" not exist,please check!");
		}		

		try {
			props.load(inStream);
		} catch (Exception e) {
			throw new Error("init constant error, exception as ", e);
		}
	}
	
	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public static String getProperty(String key){
		return props.getProperty(key);
	}
	
	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the property
	 */
	public static String getProperty(String key,String defaultValue){
		return props.getProperty(key, defaultValue);
	}


}
