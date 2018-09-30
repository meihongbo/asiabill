package com.lj.base.mvc.web.filter;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.lj.base.core.encryption.MD5;
import com.lj.base.exception.TsfaWebException;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：防重复提交
 *   
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class DoubleSubmitPreventionFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 if(request instanceof HttpServletRequest){
			 HttpServletRequest hsr = (HttpServletRequest)request;
			 //post提交，做封装
			 if("POST".equals( hsr.getMethod())){
				 //---检验--------
				 if( !verifyToken(hsr) ){
					 //-----检验未通过，抛出异常----------
					 throw new TsfaWebException("doublesubmitprevention","token校验未通过",hsr.getRequestURI());
				 }
			 }
			 //----重新生成token-------
			 generateToken(hsr);
		 }
		 
		 
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
		
	}
 
	/**
	 * *
	 * 生成token.
	 *
	 * @param httpRequest the http request
	 */
	public static void generateToken(HttpServletRequest httpRequest){
		HttpSession session = httpRequest.getSession(); 
		String token = MD5.encryptByMD5Twice( httpRequest.getPathInfo(),
				String.valueOf(System.currentTimeMillis()));
        
		session.setAttribute(TOKEN_SESSION_NAME, token);
	}
	
	/**
	 * *
	 * 检验是否token合法.
	 *
	 * @param httpRequest the http request
	 * @return true, if verify token
	 */
	public static boolean verifyToken(HttpServletRequest httpRequest){
		HttpSession session = httpRequest.getSession(); 
		String token = (String)session.getAttribute(TOKEN_SESSION_NAME);
		if(StringUtils.isNotEmpty(token)){
			//----检验token-----------
			return token.equals(httpRequest.getParameter(TOKEN_SESSION_NAME));
		}
		return true;//无session token则为true
	}
	
	/** The Constant TOKEN_SESSION_NAME. */
	private static final String TOKEN_SESSION_NAME= "token";
}
