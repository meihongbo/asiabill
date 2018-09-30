

package com.lj.base.encrypt;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：验签结果
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
public class SignResult {

	/** 验签是否通过. */
	private boolean sign;
	
	/** 验签通过后解密数据（json格式）. */
	private String data;
	
	/** 验签通过后解密数据（数据对象）. */
	private Object dataObject;
	
	/**
	 * The Constructor.
	 */
	public SignResult() {
		super();
	}
	
	/**
	 * The Constructor.
	 *
	 * @param sign the sign
	 */
	public SignResult(boolean sign) {
		super();
		this.sign = sign;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param sign the sign
	 * @param data the data
	 */
	public SignResult(boolean sign, String data) {
		super();
		this.sign = sign;
		this.data = data;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param sign the sign
	 * @param dataObject the data object
	 */
	public SignResult(boolean sign, Object dataObject) {
		super();
		this.sign = sign;
		this.dataObject = dataObject;
	}

	/**
	 * Checks if is 验签是否通过.
	 *
	 * @return the sign
	 */
	public boolean isSign() {
		return sign;
	}

	/**
	 * Sets the 验签是否通过.
	 *
	 * @param sign the sign to set
	 */
	public void setSign(boolean sign) {
		this.sign = sign;
	}

	/**
	 * Gets the 验签通过后解密数据（json格式）.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the 验签通过后解密数据（json格式）.
	 *
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the 验签通过后解密数据（数据对象）.
	 *
	 * @return the dataObject
	 */
	public Object getDataObject() {
		return dataObject;
	}

	/**
	 * Sets the 验签通过后解密数据（数据对象）.
	 *
	 * @param dataObject the dataObject to set
	 */
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignResult [sign=");
		builder.append(sign);
		builder.append(", data=");
		builder.append(data);
		builder.append(", dataObject=");
		builder.append(dataObject);
		builder.append("]");
		return builder.toString();
	}

}
