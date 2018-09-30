package com.lj.base.core.encryption;

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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.lj.base.core.util.StringUtils;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 * 
 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储. 方法: void
 * getKey(String strKey)从strKey的字条生成一个Key String getEncString(String
 * strMing)对strMing进行加密,返回String密文 String getDesString(String
 * strMi)对strMin进行解密,返回String明文 byte[] getEncCode(byte[] byteS)byte[]型的加密 byte[]
 * getDesCode(byte[] byteD)byte[]型的解密
 * 
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class DES {

	/** The Constant DEFAULT_CHARSET. */
	private static final String DEFAULT_CHARSET = "utf-8";

	/** The key. */
	private Key key;

	/** The byte mi. */
	private byte[] byteMi = null;

	/** The byte ming. */
	private byte[] byteMing = null;

	/** The str mi. */
	private String strMi = "";

	/** The str m. */
	private String strM = "";

	/**
	 * 根据参数生成KEY .
	 *
	 * @param strKey
	 *            the key
	 */
	public void setKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");

			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes(DEFAULT_CHARSET));
			_generator.init(56, secureRandom); // DES 56 bit ,AES 128 bit

			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加密String明文输入,String密文输出
	 *
	 * @param strMing
	 *            the enc string
	 */
	public void setEncString(String strMing) {
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			this.byteMing = strMing.getBytes(DEFAULT_CHARSET);
			this.byteMi = this.getEncCode(this.byteMing);
			this.strMi = base64en.encode(this.byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			this.byteMing = null;
			this.byteMi = null;
		}
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 *
	 * @param byteS
	 *            the byte s
	 * @return the enc code
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}

		return byteFina;
	}

	/**
	 * 解密:以String密文输入,String明文输出 .
	 *
	 * @param strMi
	 *            the des string
	 */
	public void setDesString(String strMi) {
		BASE64Decoder base64De = new BASE64Decoder();
		try {
			this.byteMi = base64De.decodeBuffer(strMi);
			this.byteMing = this.getDesCode(byteMi);
			this.strM = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}

	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出.
	 *
	 * @param byteD
	 *            the byte d
	 * @return the des code
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 返回加密后的密文strMi .
	 *
	 * @return the str mi
	 */
	public String getStrMi() {
		return strMi;
	}

	/**
	 * 返回解密后的明文.
	 *
	 * @return the str m
	 */
	public String getStrM() {
		return strM;
	}

	/**
	 * * 根据密钥key对data进行加密.
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String encrpty(String data, String key) {
		DES des = new DES();
		des.setKey(key); // 调用set函数设置密钥。
		des.setEncString(data);// 将要加密的明文传送进行加密计算。
		return des.getStrMi(); // 调用get函数获取加密后密文。

	}

	/**
	 * 根据密钥key对data进行解密.
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String decrpty(String data, String key) {
		DES des = new DES();
		des.setKey(key);
		des.setDesString(data);
		return des.getStrM();
	}

	/**
	 * * 修正后的特殊二次加密 根据密钥key+key.hashCode对data反顺进行加密
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String encrptyRevised(String data, String key) {
		DES des = new DES();
		des.setKey(key + key.hashCode()); // 调用set函数设置密钥。
		String keyMd5 = MD5.encryptByMD5(key);
		String dataRevised = keyMd5.substring(0, 3) + StringUtils.reverse(data)
				+ keyMd5.substring(4, 7);
		des.setEncString(dataRevised);// 将要加密的明文传送进行加密计算。
		return des.getStrMi(); // 调用get函数获取加密后密文。

	}

	/**
	 * 根据密钥key+key.hashCode对data进行解密，然后data进行反顺
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String decrptyRevised(String data, String key) {
		DES des = new DES();
		des.setKey(key + key.hashCode());
		des.setDesString(data);
		String dataRevised = des.getStrM();
		// ----去掉前三，后三，做反顺
		return StringUtils.reverse(dataRevised.substring(3,
				dataRevised.length() - 3));
	}

}
