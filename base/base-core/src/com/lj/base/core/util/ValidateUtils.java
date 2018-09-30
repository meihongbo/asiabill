package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * 类说明：正则表达式工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class ValidateUtils {
	
	/** The Constant szVerCode. */
	private static final char[] szVerCode = new char[] { '1', '0', 'X', '9',
			'8', '7', '6', '5', '4', '3', '2' };
	
	/** The Constant iW. */
	private static final int[] iW = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2, 1 };

	/**
	 * 方法说明：匹配身份证号码.
	 *
	 * @param ID18 the I d18
	 * @return true, if checks if is id card no
	 * @deprecated Use IdcardValidator.isValidate18Idcard instead.
	 */
	@Deprecated
	public static boolean isIDCardNo(String ID18) {
		boolean flag = false;
		if (ID18 == null || ID18.length() != 18)
			return false;
		int i = 0;
		for (int k = 0; k < 18; k++) {
			char c = ID18.charAt(k);
			int j;
			if (c == 'X')
				j = 10;
			else if (c <= '9' || c >= '0')
				j = c - 48;
			else
				return flag;
			i += j * iW[k];
		}
		if (i % 11 == 1)
			flag = true;
		return flag;
	}

	/**
	 * 方法说明：15位身份证转18位.
	 *
	 * @param ID15 the I d15
	 * @return the string
	 * @deprecated Use IdcardValidator instead.
	 */
	@Deprecated
	public static String toIDCardNO18(String ID15) {
		if (ID15 == null || ID15.length() != 15) {
			return null;
		}
		String ID17 = new StringBuilder(ID15.substring(0, 6)).append("19")
				.append(ID15.substring(6, 15)).toString();
		int[] ID17Array = new int[17];
		for (int i = 0; i < 17; i++) {
			ID17Array[i] = Integer.parseInt(ID17.substring(i, i + 1));
		}
		int s = 0;
		for (int i = 0; i < 17; i++) {
			s = s + ID17Array[i] * iW[i];
		}
		s = s % 11;
		return ID17 + Character.toString(szVerCode[s]);
	}

	/**
	 * 方法说明：指定长度的字母加数字.
	 *
	 * @param value the value
	 * @param maxLength the max length
	 * @param minLength the min length
	 * @return true, if checks if is letter number
	 */
	public static boolean isLetterNumber(String value, int maxLength,
			int minLength) {
		return check("[a-z0-9A-Z]{" + minLength + "," + maxLength + "}", value,
				true);
	}

	/**
	 * 方法说明：匹配带域名的URL地址.
	 *
	 * @param value the value
	 * @return true, if checks if is domain url
	 */
	public static boolean isDomainURL(String value) {
		return check("^(http:\\/\\/)[a-z0-9A-Z]+([\\.][a-z0-9A-Z]+)+", value,
				false);
	}

	/**
	 * 方法说明：匹配纯数字.
	 *
	 * @param value the value
	 * @param length the length
	 * @return true, if checks if is number
	 */
	public static boolean isNumber(String value, int length) {
		return check("[0-9]{" + length + "}", value, true);
	}

	/**
	 * 方法说明：匹配EMAIL格式.
	 *
	 * @param value the value
	 * @return true, if checks if is email
	 */
	public static boolean isEmail(String value) {
		return check(
				"^([a-z0-9A-Z])+([_|\\-|\\.]?[a-z0-9A-Z])*@[a-z0-9A-Z]+(\\-[a-z0-9A-Z]+)*(\\.[a-zA-Z]+){1,2}$",
				value, true);
	}

	/**
	 * 方法说明：匹配包含中文.
	 *
	 * @param value the value
	 * @return true, if checks for chinese
	 */
	public static boolean hasChinese(String value) {
		return check("[\u4e00-\u9fa5]", value, true);
	}

	/**
	 * 方法说明：匹配全部中文.
	 *
	 * @param value the value
	 * @return true, if checks if is chinese
	 */
	public static boolean isChinese(String value) {
		for (char ch : value.toCharArray()) {
			if (!check("[\u4e00-\u9fa5]", String.valueOf(ch), true))
				return false;
		}
		return true;
	}

	/**
	 * Check.
	 *
	 * @param regex the regex
	 * @param value the value
	 * @param matchAll the match all
	 * @return true, if check
	 */
	public static boolean check(String regex, String value, boolean matchAll) {
		Pattern p = Pattern.compile(regex);
		Matcher result = p.matcher(value);
		return matchAll ? result.matches() : result.find();
	}
	
	/**
	 * 方法说明：验证对象是否为NULL，空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串).
	 *
	 * @param obj 		被验证的对象
	 * @param message 	异常信息
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message + " must be required.");
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message + " must be required.");
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message + " must be required.");
		}
		if (obj instanceof Collection && ((Collection)obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be required.");
		}
		if (obj instanceof Map && ((Map)obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be required.");
		}
	}
}
