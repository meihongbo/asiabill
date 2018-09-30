package com.lj.base.mvc.base;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.core.BaseEntity;

/**
 * 
 * 
 * 类说明：domain实体，在BaseEntity基础上增加打印日志,日志appender为类名
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
public abstract class BaseDomainEntity extends BaseEntity {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private final   Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Debug.
	 *
	 * @param text the text
	 */
	public   void debug(String text){
		logger.debug(text); 
	}
	
	/**
	 * Info.
	 *
	 * @param text the text
	 */
	public   void info(String text){
		logger.info(text);
	}
	
	/**
	 * Warn.
	 *
	 * @param text the text
	 */
	public   void warn(String text){
		logger.warn(text);
	}
	
	/**
	 * Error.
	 *
	 * @param text the text
	 */
	public   void error(String text){
		logger.error(text);
	}
	
	/**
	 * Debug.
	 *
	 * @param text the text
	 * @param e the e
	 */
	public   void debug(String text,Throwable e){
		logger.debug(text,e); 
	}
	
	/**
	 * Info.
	 *
	 * @param text the text
	 * @param e the e
	 */
	public   void info(String text,Throwable e){
		logger.info(text,e);
	}
	
	/**
	 * Warn.
	 *
	 * @param text the text
	 * @param e the e
	 */
	public   void warn(String text,Throwable e){
		logger.warn(text,e);
	}
	
	/**
	 * Error.
	 *
	 * @param text the text
	 * @param e the e
	 */
	public   void error(String text,Throwable e){
		logger.error(text,e);
	}
	
}
