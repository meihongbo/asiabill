
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
 * 类说明：RSA签名结果
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月14日
 */
public class Sign {

	/** AES密钥明文. */
	private String aesKey;
	
	/** 被对方RSA公钥加密的AES密钥密文. */
	private String encryptAesKey;

	/** json格式的业务数据明文. */
	private String data;

	/** 被AES密钥加密的业务数据密文. */
	private String encryptData;

	/**
	 * The Constructor.
	 */
	public Sign() {
		super();
	}
	
	/**
	 * The Constructor.
	 *
	 * @param aesKey the aes key
	 * @param encryptAesKey the encrypt aes key
	 * @param data the data
	 * @param encryptData the encrypt data
	 */
	public Sign(String aesKey, String encryptAesKey, String data, String encryptData) {
		super();
		this.aesKey = aesKey;
		this.encryptAesKey = encryptAesKey;
		this.data = data;
		this.encryptData = encryptData;
	}
	
	/**
	 * Gets the aES密钥明文.
	 *
	 * @return the aesKey
	 */
	public String getAesKey() {
		return aesKey;
	}

	/**
	 * Sets the aES密钥明文.
	 *
	 * @param aesKey the aesKey to set
	 */
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	/**
	 * Gets the 被对方RSA公钥加密的AES密钥密文.
	 *
	 * @return the encryptAesKey
	 */
	public String getEncryptAesKey() {
		return encryptAesKey;
	}

	/**
	 * Sets the 被对方RSA公钥加密的AES密钥密文.
	 *
	 * @param encryptAesKey the encryptAesKey to set
	 */
	public void setEncryptAesKey(String encryptAesKey) {
		this.encryptAesKey = encryptAesKey;
	}

	/**
	 * Gets the json格式的业务数据明文.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the json格式的业务数据明文.
	 *
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the 被AES密钥加密的业务数据密文.
	 *
	 * @return the encryptData
	 */
	public String getEncryptData() {
		return encryptData;
	}

	/**
	 * Sets the 被AES密钥加密的业务数据密文.
	 *
	 * @param encryptData the encryptData to set
	 */
	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sign [aesKey=");
		builder.append(aesKey);
		builder.append(", encryptAesKey=");
		builder.append(encryptAesKey);
		builder.append(", data=");
		builder.append(data);
		builder.append(", encryptData=");
		builder.append(encryptData);
		builder.append("]");
		return builder.toString();
	}
	
}
