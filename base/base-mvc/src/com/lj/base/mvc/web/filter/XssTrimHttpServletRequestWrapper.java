package com.lj.base.mvc.web.filter;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

 
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：防止XSS注入，并且将字符串Trim掉
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月14日
 */
public class XssTrimHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	/** The org request. */
	HttpServletRequest orgRequest = null;

	/**
	 * The Constructor.
	 *
	 * @param request the request
	 */
	public XssTrimHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 *
	 * @param name the name
	 * @return the parameter
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
//	@Override
//checkbox无需
//	public String []getParameterValues(String name){
//		String []oriValues = super.getParameterValues(name);
//		//----逐个转换------ 
//		return oriValues;
//	}

	/**
 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
 * getHeaderNames 也可能需要覆盖
 *
 * @param name the name
 * @return the header
 */
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getParameterMap(){ 
		Map oriMap = super.getParameterMap();  
		for(Object  key : oriMap.keySet()){
			String str = (String)oriMap.get(key);
			oriMap.put(key, xssEncode(str));
		}
		return oriMap;
	}
	
	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符.
	 *
	 * @param s the s
	 * @return the string
	 */
	private static String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');//全角大于号
				break;
			case '<':
				sb.append('＜');//全角小于号
				break;
			case '\'':
				sb.append('‘');//全角单引号
				break;
			case '\"':
				sb.append('“');//全角双引号
				break;
			case '&':
				sb.append('＆');//全角
				break;
			case '\\':
				sb.append('＼');//全角斜线
				break;
			case '#':
				sb.append('＃');//全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString().trim();
	}

	/**
	 * 获取最原始的request.
	 *
	 * @return the org request
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法.
	 *
	 * @param req the req
	 * @return the org request
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XssTrimHttpServletRequestWrapper) {
			return ((XssTrimHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}

}