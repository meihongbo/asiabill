package com.lj.base.exception;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Map;

/**
 * 
 * 
 * 类说明：带上下文的服务层异常
 *  
 * 
 * <p>
 * 详细描述：可传递异常的上下文信息（Map对象）
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class TsfaContextServiceException extends TsfaServiceException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5674060378599770571L;

	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 */
	public TsfaContextServiceException(String exceptionCode, String exceptionInfo){
		super(exceptionCode,exceptionInfo);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param exceptionMap the exception map
	 */
	public TsfaContextServiceException(String exceptionCode, String exceptionInfo, Map<String, String> exceptionMap) {
		super(exceptionCode, exceptionInfo);
		this.exceptionMap = exceptionMap;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param throwable the throwable
	 */
	public TsfaContextServiceException(String exceptionCode, String exceptionInfo, Throwable throwable){
		super(exceptionCode,exceptionInfo,throwable);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param exceptionMap the exception map
	 * @param throwable the throwable
	 */
	public TsfaContextServiceException(String exceptionCode, String exceptionInfo, Map<String, String> exceptionMap, Throwable throwable) {
		super(exceptionCode, exceptionInfo, throwable);
		this.exceptionMap = exceptionMap;
	}
	

	/** 错误参数. */
	private Map<String, String> exceptionMap;


	/**
	 * Gets the 错误参数.
	 *
	 * @return the exceptionMap
	 */
	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}

	/**
	 * Sets the 错误参数.
	 *
	 * @param exceptionMap the exceptionMap to set
	 */
	public void setExceptionMap(Map<String, String> exceptionMap) {
		this.exceptionMap = exceptionMap;
	}
	
}
