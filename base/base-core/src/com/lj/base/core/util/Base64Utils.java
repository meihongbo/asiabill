package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.io.InputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 
 * 类说明：Base64编码的编码和解码类
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
@SuppressWarnings("restriction")
public class Base64Utils {


	/**
	 * 方法说明：编码方法, 把一个字节数组编码为BASE64编码的字符串.
	 *
	 * @param arrB the arr b
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static String encode(byte[] arrB) {
		AssertUtils.notNullAndEmpty(arrB);
		return new BASE64Encoder().encode(arrB);
	}

	/**
	 * 方法说明：解码方法, 把一个BASE64编码的字符串解码为编码前的字节数组.
	 *
	 * @param in the in
	 * @return the byte[]
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static byte[] decode(String in) throws IOException {
		AssertUtils.notNullAndEmpty(in);
		byte[] arrB = null;
		arrB = new BASE64Decoder().decodeBuffer(in);
		return arrB;
	}

	/**
	 * 方法说明：编码方法, 把InputStream流中的数据内容编码为BASE64编码的字符串.
	 *
	 * @param in the in
	 * @return the string
	 * @throws IOException the IO exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static String encode(InputStream in) throws IOException {
		AssertUtils.notNullAndEmpty(in);
		byte[] read = new byte[64];
		StringBuffer result = new StringBuffer("");
		int bytesRead = -1;
		while ((bytesRead = in.read(read)) != -1) {
			byte[] src = read;
			if (bytesRead != 64) {
				src = new byte[bytesRead];
				for (int j = 0; j < bytesRead; j++) {
					src[j] = read[j];
				}
			}
			result.append(Base64Utils.encode(src));
		}
		return result.toString();
	}

	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String args []){
		System.out.println("start");
		try {
			System.out.println(encode("12345678".getBytes()));
			System.out.println(encode("1234567811".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	} 
}
