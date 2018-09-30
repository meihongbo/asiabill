package com.lj.base.core.encryption;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.lj.base.core.util.ValidateUtils;


/**
 * 
 * 
 * 类说明：AES工具类
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
public class AES {
	
	/**
	 * 方法说明：AES加密.
	 *
	 * @param data 	待加密数据
	 * @param key 	加密密钥
	 * @return the byte[]
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		ValidateUtils.notEmpty(data, "data");
		ValidateUtils.notEmpty(key, "key");
		if(key.length != 16) {
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, EncrptyConstants.AES); 
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, EncrptyConstants.AES);
			Cipher cipher = Cipher.getInstance(EncrptyConstants.AES_ALGORITHM);	// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, seckey);	// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e){
			throw new RuntimeException("encrypt fail!", e);
		}
	}
	
	/**
	 * 方法说明：AES加密.
	 *
	 * @param data 	待加密数据
	 * @param key 	加密密钥
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String encrypt(String data, String key) {
		try {
			byte[] valueByte = encrypt(data.getBytes(EncrptyConstants.ENCODE_UTF8), key.getBytes(EncrptyConstants.ENCODE_UTF8));
			return new String(Base64.encodeBase64(valueByte));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("encrypt fail!", e);
		}
	}
	
	/**
	 * 方法说明：AES解密.
	 *
	 * @param data 	待解密数据
	 * @param key 	密钥
	 * @return the byte[]
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		ValidateUtils.notEmpty(data, "data");
		ValidateUtils.notEmpty(key, "key");
		if(key.length != 16){
			throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, EncrptyConstants.AES); 
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, EncrptyConstants.AES);
			Cipher cipher = Cipher.getInstance(EncrptyConstants.AES_ALGORITHM);	// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, seckey);	// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e){
			throw new RuntimeException("decrypt fail!", e);
		}
	}

	/**
	 * 方法说明：AES解密.
	 *
	 * @param data the data
	 * @param key the key
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String decrypt(String data, String key){
		try {
			byte[] originalData = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(originalData, key.getBytes(EncrptyConstants.ENCODE_UTF8));
			return new String(valueByte, EncrptyConstants.ENCODE_UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("decrypt fail!", e);
		}
	}
}
