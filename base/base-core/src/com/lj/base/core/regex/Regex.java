package com.lj.base.core.regex;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * 类说明：
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
public class Regex {
	
	/** The pattern. */
	Pattern pattern = null;

	/**
	 * Constructor.
	 *
	 * @param regex the regex
	 */
	public Regex(String regex) {
		pattern = Pattern.compile(regex);
	}
	
	/**
	 * Constructor.
	 *
	 * @param regex the regex
	 * @param flag the flag
	 */
	public Regex(String regex,int flag) {
		pattern = Pattern.compile(regex,flag);
	}

	/**
	 * Description:
	 * <BR>.
	 *
	 * @param value the value
	 * @return true, if checks if is match
	 */
	public boolean isMatch(String value) {
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * Description:
	 * <BR>.
	 *
	 * @param value the value
	 * @return true, if find
	 */
	public boolean find(String value) {
		Matcher matcher = pattern.matcher(value);		
		return matcher.find();
	}
	
	/**
	 * Description:
	 * <BR>.
	 *
	 * @param value the value
	 * @param star the star
	 * @return true, if find
	 */
	public boolean find(String value,int star) {
		Matcher matcher = pattern.matcher(value);		
		return matcher.find(star);
	}
	
	
	/**
	 * Matcher.
	 *
	 * @param value the value
	 * @return the string
	 */
	public String matcher(String value){
		Matcher matcher = pattern.matcher(value);
		if(matcher.find()){
			return matcher.group();
		}
		return null;
		
	}
	
	/**
	 * Matcher.
	 *
	 * @param value the value
	 * @param star the star
	 * @return the string
	 */
	public String matcher(String value,int star){
		Matcher matcher = pattern.matcher(value);
		if(matcher.find(star)){
			return matcher.group(star);
		}
		return null;
		
	}
	
	
	
	/**
	 * Description:
	 * <BR>
	 * Creates a matcher that will match the given input against this pattern.
	 *
	 * @param value the String that will be match
	 * @return Matcher
	 */
	public Matcher getMatcher(String value){
		return pattern.matcher(value);
	}

}
