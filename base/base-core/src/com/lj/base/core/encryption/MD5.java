package com.lj.base.core.encryption;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.CharEncoding;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 * 
 * MD5的算法在RFC1321 中定义
 * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：
 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0
 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b.
 * 
 * 传入参数：一个字节数组
 * 传出参数：字节数组的 MD5 结果字符串
 * 
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class MD5 {   
	
	/** The Constant MD5_STR. */
	private static final String MD5_STR = "MD5";

	/** The Constant TIMESSHARING. */
	public static final String TIMESSHARING = "timessharing";
	
	/** The Constant hexDigits. */
	private static final char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符   
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};    



	/**
	 * *
	 * 对输入字符串进行MD5加密.
	 *
	 * @param pwd the pwd
	 * @return the string
	 */
	public static String encryptByMD5(String pwd){

		String s = null;   
		byte[] source = null;
		try {
			source = pwd.getBytes(CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException unsptE) { 
			return s;
		}
			try  
		{   
			MessageDigest md = MessageDigest.getInstance( MD5_STR );   
			md.update( source );   
			byte tmp[] = md.digest();          // MD5 的计算结果是一个 128 位的长整数，   
			// 用字节表示就是 16 个字节   
			char str[] = new char[16 * 2];   // 每个字节用 16 进制表示的话，使用两个字符，   
			// 所以表示成 16 进制需要 32 个字符   
			int k = 0;                                // 表示转换结果中对应的字符位置   
			for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节   
				// 转换成 16 进制字符的转换   
				byte byte0 = tmp[i];                 // 取第 i 个字节   
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换,    
				// >>> 为逻辑右移，将符号位一起右移   
				str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换   
			}    
			s = new String(str);                                 // 换后的结果转换为字符串   

		}catch( Exception e ) {   
			e.printStackTrace();   
		}   
		return s;  
	}  

	/**
	 * 方法说明：MD5+盐.
	 *
	 * @param pwd the pwd
	 * @param salt the salt
	 * @return the string
	 * @author 彭阳 CreateDate: 2017-7-1
	 */
	public static String encryptByMD5(String pwd,String salt){
		if(salt == null)
			salt = "";
		return MD5.encryptByMD5(pwd + salt);
	}
	
	/**
	 * 方法说明：MD5加密（附带加默认盐）.
	 *
	 * @param pwd the pwd
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-06-29
	 */
	public static String encryptByMD5BySalt(String pwd){
		return MD5.encryptByMD5(pwd + MD5.TIMESSHARING);
	}   

	/**
	 * **
	 * 防止md5暴力破解，两次md5.
	 *
	 * @param inputStr the input str
	 * @return the string
	 */
	public static String encryptByMD5Twice(String inputStr){
		return MD5.encryptByMD5(MD5.encryptByMD5(inputStr) + TIMESSHARING);
	}
	
	/**
	 * **
	 * 对inputStr进行MD5处理后的字符串再加上afterMD5Str
	 * 一起二次MD5加密.
	 *
	 * @param inputStr the input str
	 * @param afterMD5Str the after m d5 str
	 * @return the string
	 */
	public static String encryptByMD5Twice(String inputStr,String afterMD5Str){
		return MD5.encryptByMD5(
				MD5.encryptByMD5(inputStr) + afterMD5Str
				);
	}


	/**
	 * 方法说明：.
	 *
	 * @param object the object
	 * @return the string
	 * @author 彭阳
	 */
	public static String encryptByMD5Twice(Object object){
		AssertUtils.notNull(object);
		return encryptByMD5Twice(String.valueOf(object));
	}


	/**
	 * *
	 * 根据时间生成二次MD5动态加密串
	 * 订单、账户系统防篡改等场景使用.
	 *
	 * @param inputStr the input str
	 * @param encryptDate the encrypt date
	 * @return the string
	 */
	public String encryptByMD5Dynamic(String inputStr,Date encryptDate){
		return encryptByMD5Twice( inputStr + encryptDate.getTime() );
	}

	/**
	 * 方法说明：MD5加密.
	 *
	 * @param data the data
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String encryptToBase64(String data) {
		String s = null;   
		byte[] source = null;
		try {
			source = data.getBytes(EncrptyConstants.ENCODE_UTF8);
		} catch (UnsupportedEncodingException unsptE) { 
			return s;
		}
		try  
		{   
			MessageDigest md = MessageDigest.getInstance(EncrptyConstants.MD5);   
			md.update( source );   
			byte tmp[] = md.digest();          // MD5 的计算结果是一个 128 位的长整数
			s = new String(Base64.encodeBase64(tmp));
		}catch( Exception e ) {   
			e.printStackTrace();   
		}   
		return s;  
	}   

	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String args []){
		for (int i = 0; i < 1; i++) {
		//	System.out.println(encryptByMD5Twice("1647381382729274042","6473813827"));
			System.out.println(encryptByMD5("898746"));
			System.out.println(encryptByMD5("a123456"));
			System.out.println(encryptByMD5("a898746"));
			//System.out.println(encryptByMD5("111111"));
			//dc483e80a7a0bd9ef71d8cf973673924
		}
		

	}

}  

