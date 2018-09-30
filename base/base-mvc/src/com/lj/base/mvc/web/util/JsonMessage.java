package com.lj.base.mvc.web.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：常用Json返回包装类
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
public class JsonMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 267873936941716320L;
	
	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";
	
	/** The Constant FAILURE. */
	public static final String FAILURE = "error";
	
	/** The Constant SUCCESS_CODE. */
	public static final String SUCCESS_CODE = "00";
	
	/** The Constant FAILURE_CODE. */
	public static final String FAILURE_CODE = "01";
	
	/** The type. */
	private String type = SUCCESS;
	
	/** The code. */
	private String code = SUCCESS_CODE;
	
	/** The message. */
	private String message;
	
	/** The data. */
	private Object data;
	
	/**
	 * The Constructor.
	 */
	public JsonMessage() {
		super();
		this.type = SUCCESS;
		this.code = SUCCESS_CODE;
		this.message = "处理成功";
	}
	
	
	/**
	 * The Constructor.
	 *
	 * @param type the type
	 * @param message the message
	 */
	public JsonMessage(String type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	/**
	 * The Constructor.
	 *
	 * @param type the type
	 * @param code the code
	 * @param message the message
	 */
	public JsonMessage(String type, String code, String message) {
		super();
		this.type = type;
		this.code = code;
		this.message = message;
	}

	/**
	 * The Constructor.
	 *
	 * @param type the type
	 * @param code the code
	 * @param message the message
	 * @param data the data
	 */
	public JsonMessage(String type, String code, String message, Object data) {
		super();
		this.type = type;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JsonMessage [type=");
		builder.append(type);
		builder.append(", code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
