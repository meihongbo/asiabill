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
public class TsfaWebException extends TsfaException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4645737999016764363L;
	
	/** * 异常请求的url. */
    private String url;
	
	/**
	 * Gets the * 异常请求的url.
	 *
	 * @return the * 异常请求的url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Sets the * 异常请求的url.
	 *
	 * @param url the new * 异常请求的url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param url the url
	 */
	public TsfaWebException(String exceptionCode,String exceptionInfo ,String url){
		super(exceptionCode,exceptionInfo);
		this.url = url;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionInfo the exception info
	 * @param url the url
	 * @param throwable the throwable
	 */
	public TsfaWebException(String exceptionCode,String exceptionInfo ,String url,Throwable throwable ){
		super(exceptionCode,exceptionInfo,throwable);
		this.url = url;
	}

}
