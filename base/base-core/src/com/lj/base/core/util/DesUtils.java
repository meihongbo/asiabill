package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * 
 * 
 * 类说明：DES安全编码组件 
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
public abstract class DesUtils{  

	/**
	 * DES加密
	 */
	public static final String ALGORITHM_DES = "DES";  
	
	/**
	 * DESede加密
	 */
	public static final String ALGORITHM_DESEDE = "DESede";  

	/**
	 * Blowfish加密
	 */
	public static final String ALGORITHM_BLOWFISH = "Blowfish";  



	/**
	 * 方法说明：转换密钥.
	 *
	 * @param key the key
	 * @param ALGORITHM the algorithm
	 * @return the key
	 * @throws Exception the exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	private static Key toKey(byte[] key , String ALGORITHM) throws Exception {  
		AssertUtils.notNullAndEmpty(key);
		DESKeySpec dks = new DESKeySpec(key);  
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
		SecretKey secretKey = keyFactory.generateSecret(dks);  

		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码  
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);  

		return secretKey;  
	}  

	/**
	 * 方法说明：解密.
	 *
	 * @param data the data
	 * @param key the key
	 * @param ALGORITHM the algorithm
	 * @return the byte[]
	 * @throws Exception the exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static byte[] decrypt(byte[] data, String key, String ALGORITHM) throws Exception {  
		AssertUtils.notNullAndEmpty(data);
		AssertUtils.notNullAndEmpty(key);
		Key k = toKey(key.getBytes(),ALGORITHM);  

		Cipher cipher = Cipher.getInstance(ALGORITHM);  
		cipher.init(Cipher.DECRYPT_MODE, k);  

		return cipher.doFinal(data);  
	}  

	/**
	 * 方法说明：加密.
	 *
	 * @param data the data
	 * @param key the key
	 * @param ALGORITHM the algorithm
	 * @return the byte[]
	 * @throws Exception the exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static byte[] encrypt(byte[] data, String key, String ALGORITHM) throws Exception {  
		AssertUtils.notNullAndEmpty(data);
		AssertUtils.notNullAndEmpty(key);
		Key k = toKey(key.getBytes(),ALGORITHM);  
		Cipher cipher = Cipher.getInstance(ALGORITHM);  
		cipher.init(Cipher.ENCRYPT_MODE, k);  

		return cipher.doFinal(data);  
	}  

	/**
	 * 方法说明：生成密钥.
	 *
	 * @param ALGORITHM the algorithm
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static String initKey( String ALGORITHM) throws Exception {  
		return initKey(null,ALGORITHM);  
	}  

	/**
	 * 方法说明：生成密钥.
	 *
	 * @param seed the seed
	 * @param ALGORITHM the algorithm
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * CreateDate: 2017年7月1日
	 */
	public static String initKey(String seed, String ALGORITHM) throws Exception {  
		SecureRandom secureRandom = null;  

		if (seed != null) {  
			secureRandom = new SecureRandom(Base64Utils.decode(seed));  
		} else {  
			secureRandom = new SecureRandom();  
		}  

		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);  
		kg.init(secureRandom);  

		SecretKey secretKey = kg.generateKey();  

		return Base64Utils.encode(secretKey.getEncoded());  
	}  

	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String args []){
		System.out.println("start");
		try {
			System.out.println(encrypt("1".getBytes(), "123456781",DesUtils.ALGORITHM_DES));
			System.out.println(new String(decrypt(encrypt("1".getBytes(), "123456781",DesUtils.ALGORITHM_DES),"12345678",DesUtils.ALGORITHM_DES)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	} 


}  