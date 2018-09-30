package com.lj.base.exception;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.exception.TsfaException;

/**
 * 
 * 
 * 类说明：业务逻辑异常处理类
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
public class TsfaRuntimeException extends TsfaException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -365014972012367025L;
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 */
	public TsfaRuntimeException(String exceptionCode,String exceptionInfo ){
		super(exceptionCode,exceptionInfo);
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param throwable the throwable
	 */
	public TsfaRuntimeException(String exceptionCode,String exceptionInfo,Throwable throwable ){
		super(exceptionCode,exceptionInfo,throwable);
	}

}
