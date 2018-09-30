package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Random;

/**
 * 
 * 
 * 类说明：随机生成指定位数的字符串
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
public class RandomUtils {
	
	/** The random. */
	public static Random random = new Random();
	
	/**
	 * *
	 * 随机生成length长度的字符串.
	 *
	 * @param length the length
	 * @return the random
	 */
	public static String getRandom(int length) {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < length; i++) {
			boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
			if (isChar) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				ret.append((char) (choice + random.nextInt(26)));
			} else { // 数字
				ret.append(Integer.toString(random.nextInt(10)));
			}
		}
		return ret.toString();
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成指定位数的随机码(十进制)
	 *
	 * @return
	 */
	public static String getDecRandom(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}
	
	/**
	 * 
	 *
	 * 方法说明：生成指定位数的随机码(十六进制)
	 *
	 * @param length
	 * @return
	 */
	public static String getHexRandom(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(Integer.toHexString((int) (Math.random() * 16)));
		}
		return sb.toString();
	}


}
