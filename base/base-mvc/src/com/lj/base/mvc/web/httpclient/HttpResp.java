package com.lj.base.mvc.web.httpclient;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 
 * 
 * 类说明：Http请求响应参数
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
public class HttpResp {

	/** http响应码，200为正常返回. */
    private int statusCode;

    /** The text. */
    private String text;

    /** The bytes. */
    private byte[] bytes;
    
    /**
     * 方法说明：是否响应成功.
     *
     * @return true, if checks if is success
     * @author 彭阳
     * 
     * CreateDate: 2017-7-1
     */
    public boolean isSuccess() {
    	return statusCode == 200;
    }

    /**
     * Gets the http响应码，200为正常返回.
     *
     * @return the http响应码，200为正常返回
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the http响应码，200为正常返回.
     *
     * @param statusCode the new http响应码，200为正常返回
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the text.
     *
     * @param charset the charset
     * @return the text
     */
    public String getText(String charset) {
        if (text == null) {
            try {
                text = new String(bytes, charset);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return text;
    }
    
    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
    	return this.getText("UTF-8");
    }

    /**
     * Gets the bytes.
     *
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Sets the bytes.
     *
     * @param bytes the bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HttpResp [statusCode=");
		builder.append(statusCode);
		builder.append(", text=");
		builder.append(this.getText());
		builder.append(", bytes=");
		builder.append(Arrays.toString(bytes));
		builder.append("]");
		return builder.toString();
	}
    
}
