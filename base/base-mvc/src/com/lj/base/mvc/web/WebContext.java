package com.lj.base.mvc.web;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class WebContext {

	/** The Constant threadContext. */
	private static final ThreadLocal<WebContext> threadContext = new ThreadLocal<WebContext>();
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The response. */
	private HttpServletResponse response;
	
	/** The session. */
	private HttpSession session;

	/**
	 * Constructor.
	 *
	 * @param request the request
	 * @param response the response
	 */
	private WebContext(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		this.session=request.getSession();
	}
	
	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	public HttpServletResponse getResponse(){
		return this.response;
	}
	
	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public HttpSession getSession(){
		return this.session;
	}
	
	/**
	 * Gets the attribute from session.
	 *
	 * @param name the name
	 * @return the attribute from session
	 */
	public Object getAttributeFromSession(String name){			
		return session.getAttribute(name);		
	}
	
	/**
	 * Gets the attribute from request.
	 *
	 * @param name the name
	 * @return the attribute from request
	 */
	public Object getAttributeFromRequest(String name){
		return request.getAttribute(name);				
	}
	
	/**
	 * Gets the parameter map from request.
	 *
	 * @return the parameter map from request
	 */
	public Map getParameterMapFromRequest(){
		return request.getParameterMap();
	}
	
	
	/**
	 * Description:
	 * <BR>
	 * invoke by filter for involving request and response.
	 *
	 * @param request the request
	 * @param response the response
	 */
	public static void init(HttpServletRequest request, HttpServletResponse response){
		WebContext context=threadContext.get();
		if(context!=null){
			context=new WebContext(request,response);
		}		
		threadContext.set(context);
	}

	/**
	 * Description:
	 * <BR>
	 * invoke by WebContextUtils or other Class for get WebContext instance.
	 *
	 * @return the context
	 */
	public static WebContext getContext(){		
		WebContext context=threadContext.get();		
		return context;
	}
	
	
}
