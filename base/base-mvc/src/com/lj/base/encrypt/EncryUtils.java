package com.lj.base.encrypt;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lj.base.core.encryption.AES;
import com.lj.base.core.encryption.RSA;
import com.lj.base.core.util.RandomUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;

/**
 * 
 * 
 * 类说明：RSA签名、验签
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
public class EncryUtils {

	/**
	 * 方法说明：生成RSA签名.
	 *
	 * @param map 		签名数据
	 * @param privateKey 己方私钥
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String signRSA(TreeMap<String, Object> map, String privateKey) throws Exception {
		String sign = "";
		if (StringUtils.isNotEmpty(privateKey)) {
			sign = RSA.sign(mapToString(map), privateKey);
		}
		return sign;
	}
	
	/**
	 * 方法说明：RSA签名及AES加密.
	 *
	 * @param treeMap 		业务数据集合
	 * @param privateKey 	己方私钥
	 * @param otherPublicKey 对方公钥
	 * @return the sign
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static Sign signAndEncrypt(TreeMap<String, Object> treeMap, String privateKey, String otherPublicKey) throws Exception {
		// 使用己方RSA私钥进行签名
		String sign = signRSA(treeMap, privateKey);
		// 随机生成AES密钥
		String aesKey = RandomUtils.getRandom(16);
		// 使用对方的RSA公钥加密AES密钥
		String encryptAesKey = RSA.encrypt(aesKey, otherPublicKey);
		// 将签名连同业务数据一起进行AES加密
		treeMap.put("sign", sign);
		// 将业务数据转化为json格式的字符串
		String data = JsonUtils.jsonFromObject_AllToString(treeMap);
		// 使用AES密钥加密业务数据
		String encryptData = AES.encrypt(data, aesKey);	
		return new Sign(aesKey, encryptAesKey, data, encryptData);
	}
	
	/**
	 * 方法说明：验签.
	 *
	 * @param data 				待验签数据
	 * @param encryptAesKey 		使用对方RSA公钥加密自己产生的AES密钥的密文
	 * @param myselfPrivateKey 	自己的RSA私钥，用来解密encryptAesKey
	 * @param otherPublickKey 	对方的RSA公钥，对data进行验签
	 * @return boolean				验签是否通过
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static boolean validateSign(String data, String encryptAesKey, String myselfPrivateKey, String otherPublickKey) throws Exception {
		return validateSignAndDecrypt(data, encryptAesKey, myselfPrivateKey, otherPublickKey).isSign();
	}
	
	/**
	 * 方法说明：验签并解密业务数据.
	 *
	 * @param data 				待验签数据
	 * @param encryptAesKey 		使用对方RSA公钥加密自己产生的AES密钥的密文
	 * @param myselfPrivateKey 	自己的RSA私钥，用来解密encryptAesKey
	 * @param otherPublickKey 	对方的RSA公钥，对data进行验签
	 * @param clazz 				将解密数据转为数据对象
	 * @return SignResult			验签结果
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	@SuppressWarnings("rawtypes")
	public static SignResult validateSignAndDecrypt(String data, String encryptAesKey, String myselfPrivateKey, String otherPublickKey, Class clazz) throws Exception {
		SignResult result = validateSignAndDecrypt(data, encryptAesKey, myselfPrivateKey, otherPublickKey);
		if(clazz != null && result.isSign()) {
			result.setDataObject(JsonUtils.objectFromJson(result.getData(), clazz));
		}
		return result;
	}
	
	/**
	 * 方法说明：验签并解密业务数据.
	 *
	 * @param data 				待验签数据
	 * @param encryptAesKey 		使用对方RSA公钥加密自己产生的AES密钥的密文
	 * @param myselfPrivateKey 	自己的RSA私钥，用来解密encryptAesKey
	 * @param otherPublickKey 	对方的RSA公钥，对data进行验签
	 * @return SignResult			验签结果
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static SignResult validateSignAndDecrypt(String data, String encryptAesKey, String myselfPrivateKey, String otherPublickKey) throws Exception {
		// 1、使用自己的RSA私钥merchantPrivateKey解密encryptAesKey
		String AESKey = null;
		try {
			AESKey = RSA.decrypt(encryptAesKey, myselfPrivateKey);
		} catch (Exception e) {		// AES密钥解密失败
			e.printStackTrace();
			return new SignResult(Boolean.FALSE);
		}
		
		// 2、用解密的AES密钥对支付结果数据进行解密
		String resultData = AES.decrypt(data, AESKey);
		@SuppressWarnings("unchecked")
		TreeMap<String, Object> map = (TreeMap<String, Object>) JsonUtils.objectFromJson(resultData, TreeMap.class);	
		
		// 3、取得sign明文
		String sign = StringUtils.trim((String) map.get("sign"));
		
		// 4、将map中的值编译为字符串
		StringBuffer signData = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			// 过滤sign参数
			if (StringUtils.equals(entry.getKey(), "sign")) {
				continue;
			}
			signData.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}
		
		// 5、RSA验签
		boolean result = RSA.validateSign(signData.toString().substring(1), sign, otherPublickKey);
		
		// 6、返回验签结果
		return new SignResult(result, result ? resultData : null);
	}
	
	/**
	 * 方法说明：将map转换为key1=value1&key2=value2格式的字符串.
	 *
	 * @param map the map
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToString(Map<String, Object> map) {
		if(map == null || map.isEmpty()) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			// 如果对象类型为List，则转换为json字符串
			if(entry.getValue() instanceof List || entry.getValue() instanceof ArrayList) {
				value = JsonUtils.jsonFromList((List) value);
			}
			buffer.append("&").append(entry.getKey()).append("=").append(value);
		}
		return buffer.toString().substring(1);
	}
	
}
