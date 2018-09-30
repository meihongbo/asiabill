package com.lj.base.core.util.trans;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * 
 * 
 * 类说明：简繁互译
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
public class ZHConverter {


	/** The char map. */
	private Properties charMap = new Properties();
	
	/** The conflicting sets. */
	private Set<String> conflictingSets  = new HashSet<String>();

	/** The Constant TRADITIONAL. */
	public static final int TRADITIONAL = 0;
	
	/** The Constant SIMPLIFIED. */
	public static final int SIMPLIFIED = 1;
	
	/** The Constant NUM_OF_CONVERTERS. */
	private static final int NUM_OF_CONVERTERS = 2;
	
	/** The Constant converters. */
	private static final ZHConverter[] converters = new ZHConverter[NUM_OF_CONVERTERS];
	
	/** The Constant propertyFiles. */
	private static final String[]  propertyFiles = new String[2];

	static {
		propertyFiles[TRADITIONAL] = "zh2Hant.properties";
		propertyFiles[SIMPLIFIED] = "zh2Hans.properties";
	}

	/**
	 * 简体转繁体.
	 *
	 * @param simpStr 简体字符串
	 * @return 繁体字符串
	 */
	public static String SimToTra(String simpStr) {
		ZHConverter converter = ZHConverter
				.getInstance(ZHConverter.TRADITIONAL);
		String traditionalStr = converter.convert(simpStr);
		return traditionalStr;
	}

	/**
	 * 繁体转简体.
	 *
	 * @param tradStr 繁体字符串
	 * @return 简体字符串
	 */
	public static String TraToSim(String tradStr) {
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		String simplifiedStr = converter.convert(tradStr);
		return simplifiedStr;
	}


	/**
	 * Gets the single instance of ZHConverter.
	 *
	 * @param converterType 0 for traditional and 1 for simplified
	 * @return single instance of ZHConverter
	 */
	private static ZHConverter getInstance(int converterType) {

		if (converterType >= 0 && converterType < NUM_OF_CONVERTERS) {

			if (converters[converterType] == null) {
				synchronized(ZHConverter.class) {
					if (converters[converterType] == null) {
						converters[converterType] = new ZHConverter(propertyFiles[converterType]);
					}
				}
			}
			return converters[converterType];

		} else {
			return null;
		}
	}

	/**
	 * 方法说明：convert.
	 *
	 * @param propertyFile the property file
	 * @return the String
	 */
	/*private static String convert(String text, int converterType) {
		ZHConverter instance = getInstance(converterType);
		return instance.convert(text);
	}*/


	/**
	 * Instantiates a new zH converter.
	 *
	 * @param propertyFile the property file
	 */
	private ZHConverter(String propertyFile) {

	    InputStream is = null;

	    is = getClass().getResourceAsStream(propertyFile);

		//File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is));
				charMap.load(reader);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				
				e.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
					if (is != null)
						is.close();
				} catch (IOException e) {
				}
			}
		}else{
			System.out.println("【简繁转化错误】file not found!!");
		}
		initializeHelper();
	}

	/**
	 * Initialize helper.
	 */
	private void initializeHelper() {
		Map<String, Integer> stringPossibilities = new HashMap<String, Integer>();
		Iterator<Object> iter = charMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (key.length() >= 1) {

				for (int i = 0; i < (key.length()); i++) {
					String keySubstring = key.substring(0, i + 1);
					if (stringPossibilities.containsKey(keySubstring)) {
						Integer integer = (Integer)(stringPossibilities.get(keySubstring));
						stringPossibilities.put(keySubstring, new Integer(
								integer.intValue() + 1));

					} else {
						stringPossibilities.put(keySubstring, new Integer(1));
					}

				}
			}
		}

		Iterator<String> iterSec = stringPossibilities.keySet().iterator();
		while (iterSec.hasNext()) {
			String key = (String) iterSec.next();
			if (((Integer)(stringPossibilities.get(key))).intValue() > 1) {
				conflictingSets.add(key);
			}
		}
	}

	/**
	 * 方法说明：convert.
	 *
	 * @param in the in
	 * @return the String
	 */
	private String convert(String in) {
		StringBuilder outString = new StringBuilder();
		StringBuilder stackString = new StringBuilder();

		for (int i = 0; i < in.length(); i++) {

			char c = in.charAt(i);
			String key = String.valueOf(c);
			stackString.append(key);

			if (conflictingSets.contains(stackString.toString())) {
			} else if (charMap.containsKey(stackString.toString())) {
				outString.append(charMap.get(stackString.toString()));
				stackString.setLength(0);
			} else {
				CharSequence sequence = stackString.subSequence(0, stackString.length()-1);
				stackString.delete(0, stackString.length()-1);
				flushStack(outString, new StringBuilder(sequence));
			}
		}

		flushStack(outString, stackString);

		return outString.toString();
	}


	/**
	 * Flush stack.
	 *
	 * @param outString the out string
	 * @param stackString the stack string
	 */
	private void flushStack(StringBuilder outString, StringBuilder stackString) {
		while (stackString.length() > 0){
			if (charMap.containsKey(stackString.toString())) {
					outString.append(charMap.get(stackString.toString()));
					stackString.setLength(0);

				} else {
					outString.append(String.valueOf(stackString.charAt(0)));
					stackString.delete(0, 1);
			}

		}
	}


	/**
	 * 方法说明：parseOneChar.
	 *
	 * @param c the c
	 * @return the String
	 */
	String parseOneChar(String c) {

		if (charMap.containsKey(c)) {
			return (String) charMap.get(c);

		}
		return c;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main(String args []){
		System.out.println(ZHConverter.SimToTra("冯林"));
		System.out.println(ZHConverter.TraToSim("冯林"));
	}


}
