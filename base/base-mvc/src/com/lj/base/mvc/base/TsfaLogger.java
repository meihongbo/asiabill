package com.lj.base.mvc.base;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * 类说明：日志处理 （appender用TsfaLogger）
 * 
 * <p>
 * 详细描述：
 * logback:配置文件先读取logback-test.xml，没有读取logback.xml
 * 需要第三方jar包：slf4j-api.jar、logback-core.jar、logback-classic.jar
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class TsfaLogger {
	
	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(TsfaLogger.class);
	
	/**
	 * Debug.
	 *
	 * @param text the text
	 */
	public static void debug(String text){
		logger.debug(text);
	}
	
	/**
	 * Info.
	 *
	 * @param text the text
	 */
	public static void info(String text){
		logger.info(text);
	}
	
	/**
	 * Warn.
	 *
	 * @param text the text
	 */
	public static void warn(String text){
		logger.warn(text);
	}
	
	/**
	 * Error.
	 *
	 * @param text the text
	 */
	public static void error(String text){
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
