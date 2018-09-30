package com.lj.base.mvc.web.httpclient;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * 类说明：Http请求内容
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
public class HttpParameter {

	/** The parameters. */
	private final List<Parameter> parameters = new ArrayList<Parameter>();

	/** The header map. */
	private final Map<String, String> headerMap = new HashMap<String, String>();

	/**
	 * Gets the header map.
	 *
	 * @return the header map
	 */
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	/**
	 * Checks if is empty header.
	 *
	 * @return true, if checks if is empty header
	 */
	public boolean isEmptyHeader() {
		return this.headerMap.isEmpty();
	}

	/**
	 * Adds the header.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void addHeader(String name, String value) {
		this.headerMap.put(name, value);
	}

	/**
	 * Adds the.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void add(String name, String value) {
		this.parameters.add(new Parameter(name, value));
	}

	/**
	 * Adds the.
	 *
	 * @param name the name
	 * @param values the values
	 */
	public void add(String name, List<String> values) {
		for (String s : values) {
			this.parameters.add(new Parameter(name, s));
		}
	}
	
	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Checks if is parameter empty.
	 *
	 * @return true, if checks if is parameter empty
	 */
	public boolean isParameterEmpty() {
		return this.parameters.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HttpParameter [parameters=");
		builder.append(parameters);
		builder.append(", headerMap=");
		builder.append(headerMap);
		builder.append("]");
		return builder.toString();
	}
}