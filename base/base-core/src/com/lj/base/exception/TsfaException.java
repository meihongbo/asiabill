package com.lj.base.exception;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：业务系统异常处理类（不能直接使用）
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
public abstract class TsfaException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3664225596755566456L;
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 */
	public TsfaException(String exceptionCode) {
		super(exceptionCode);
		this.setExceptionCode(exceptionCode);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 */
	public TsfaException(String exceptionCode, String exceptionInfo) {
		super(exceptionCode + "-->" + exceptionInfo);
		this.setExceptionCode(exceptionCode);
		this.setExceptionInfo(exceptionInfo);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param throwable the throwable
	 */
	public TsfaException(String exceptionCode, Throwable throwable) {
		super(exceptionCode, throwable);
		this.setExceptionCode(exceptionCode);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param throwable the throwable
	 */
	public TsfaException(String exceptionCode, String exceptionInfo, Throwable throwable) {
		super(exceptionCode + "-->" + exceptionInfo, throwable);
		this.setExceptionCode(exceptionCode);
		this.setExceptionInfo(exceptionInfo); 
	}
	
	/** * 错误代码. */
	private String exceptionCode;
	
	/** * 错误描述. */
	private String exceptionInfo;
	
	/**
	 * Gets the * 错误代码.
	 *
	 * @return the * 错误代码
	 */
	public String getExceptionCode() {
		return exceptionCode;
	}
	
	/**
	 * Sets the * 错误代码.
	 *
	 * @param exceptionCode the new * 错误代码
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	/**
	 * Gets the * 错误描述.
	 *
	 * @return the * 错误描述
	 */
	public String getExceptionInfo() {
		return exceptionInfo;
	}
	
	/**
	 * Sets the * 错误描述.
	 *
	 * @param exceptionInfo the new * 错误描述
	 */
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

}
