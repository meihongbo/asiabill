package com.lj.base.core.encryption;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * 
 * 
 * 类说明：RSA工具类
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
public class RSA {

	/** The keysize. */
	private static int KEYSIZE = 1024;

	/**
	 * 方法说明：生成密钥对.
	 *
	 * @return the map< string, string>
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static Map<String, String> generateKeyPair() throws Exception {
		// RSA算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 为RSA算法创建一个KeyPairGenerator对象
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(EncrptyConstants.RSA);
		// 利用上面的随机数据源初始化这个KeyPairGenerator对象
		kpg.initialize(KEYSIZE, sr);
		// 生成密匙对
		KeyPair kp = kpg.generateKeyPair();
		// 得到公钥
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = Base64.encodeBase64String(publicKeyBytes);
		// 得到私钥
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = Base64.encodeBase64String(privateKeyBytes);
		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	/**
	 * 方法说明：RSA加密.
	 *
	 * @param data 		待加密数据
	 * @param publicKey 	 对方RSA公钥
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String encrypt(String data, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(EncrptyConstants.RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = data.getBytes();
		byte[] b1 = cipher.doFinal(b);
		return Base64.encodeBase64String(b1);
	}

	/**
	 * 方法说明：RSA解密.
	 *
	 * @param data 		待解密数据
	 * @param privateKey RSA私钥
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String decrypt(String data, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance(EncrptyConstants.RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decodeBase64(data.getBytes()); 
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	/**
	 * 方法说明：签名.
	 *
	 * @param data 		签名数据
	 * @param privateKey 己方私钥
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String sign(String data, String privateKey) throws Exception {
			return Base64.encodeBase64String(signByte(data, privateKey));
	}
	
	/**
	 * 方法说明：签名.
	 *
	 * @param data 		签名数据
	 * @param privateKey 己方私钥
	 * @return the byte[]
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static byte[] signByte(String data, String privateKey) throws Exception {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance(EncrptyConstants.RSA);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(priKey);
			signature.update(data.getBytes(EncrptyConstants.ENCODE_UTF8));
			byte[] signed = signature.sign();
			return signed;
	}

	/**
	 * 方法说明：验签.
	 *
	 * @param data 		待验签数据明文
	 * @param sign 		数据签名
	 * @param publicKey 	对方公钥
	 * @return true, if validate sign
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static boolean validateSign(String data, String sign, String publicKey) throws Exception {
			KeyFactory keyFactory = KeyFactory.getInstance(EncrptyConstants.RSA);
			byte[] encodedKey = Base64.decode2(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");
			signature.initVerify(pubKey);
			signature.update(data.getBytes(EncrptyConstants.ENCODE_UTF8));

			return signature.verify(Base64.decode2(sign));
	}

	/**
	 * 方法说明：得到RSA公钥.
	 *
	 * @param key 	密钥字符串（经过base64编码）
	 * @return the public key
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance(EncrptyConstants.RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 方法说明：得到RSA私钥.
	 *
	 * @param key 	密钥字符串（经过base64编码）
	 * @return the private key
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance(EncrptyConstants.RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 方法说明：取得私钥.
	 *
	 * @param privkeyAddress 私钥文件地址
	 * @return the private key string
	 * @throws IOException the IO exception
	 * @author 彭阳
	 *  
	 * CreateDate: 2017-7-1
	 */
	public static String getPrivateKeyString(String privkeyAddress) throws IOException {  
		File privKeyFile = new File(privkeyAddress);  
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(privKeyFile);
			dis = new DataInputStream(fis);  
			byte[] privKeyBytes = new byte[(int) privKeyFile.length()];  
			dis.read(privKeyBytes);  
			return Base64.encodeBase64String(privKeyBytes);
		}catch(IOException e) {
			throw e;
		}finally {
			try {
				dis.close();
			} catch (IOException e) {
				throw e;
			}  
		}
	} 

	/**
	 * 方法说明：取得公钥.
	 *
	 * @param pubkeyAddress 公钥文件地址
	 * @return the public key string
	 * @throws IOException the IO exception
	 * @author 彭阳 
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String getPublicKeyString(String pubkeyAddress) throws IOException{
		File pubKeyFile = new File(pubkeyAddress);  
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(pubKeyFile);
			dis = new DataInputStream(fis);  
			byte[] pubKeyBytes = new byte[(int) pubKeyFile.length()];  
			dis.read(pubKeyBytes);  
			return Base64.encodeBase64String(pubKeyBytes);
		}catch(IOException ex) {
			throw ex;
		}finally {
			try {
				dis.close();
			} catch (IOException e) {
				throw e;
			}  
		}
	}  

	
	/**
	 * 方法说明：.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 * @author 彭阳 
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static void main(String[] args) throws Exception {	
		 Map<String, String> map = RSA.generateKeyPair();
		 System.out.println(map.get("publicKey"));
		 System.out.println(map.get("privateKey"));
		 System.out.println(map.get("modulus"));
		
		/*final String PRIVKEY = "D:/workspace/openssl/private_sdjl.der";
		final String PUBKEY = "D:/workspace/openssl/public_sdjl.der";
		String str = "macidadminorderid2012081313511811302423time1384769433";  
		System.out.println("原文:"+str);  
		String privKeyString = getPrivateKeyString(PRIVKEY);
		String pubKeyString = getPublicKeyString(PUBKEY);
		
		System.out.println("私钥："+ privKeyString); 
		System.out.println("公钥："+ pubKeyString); 

		//byte[] signBytes = sign(str.getBytes(), privKeyBytes);
		String signStr = sign(str, privKeyString);

		System.out.println("产生签名："+java.net.URLEncoder.encode(signStr,   "utf-8"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse("1970-01-01 00:00:01");
		long t = (System.currentTimeMillis() - d.getTime()) / 1000; 

		System.out.println("时间差值差值:\t" + t);
		validateSign(str, signStr, pubKeyString);
		boolean status = validateSign(str, signStr, pubKeyString);
		System.out.println("状态："+status);  
		
		System.out.println(System.getProperty("file.encoding"));*/

	}

}
