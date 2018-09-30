package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * 类说明：字符串公共处理类
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
public class StringUtils {
	
	/**
	 * 对字符串进行反转.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String reverse(String str){
		if(StringUtils.isEmpty(str))
			return str;
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-1;i>=0;i--){
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	/**
	 * *
	 * 根据指定长度左补齐.
	 *
	 * @param str the str
	 * @param len the len
	 * @param padCharacter the pad character
	 * @return the string
	 */
	public static String padLeft(String str,int len,char padCharacter){
		String padStr = pad(str,len,padCharacter);
		return padStr + str;
	}
	
	/**
	 * *
	 * 根据指定长度右补齐.
	 *
	 * @param str the str
	 * @param len the len
	 * @param padCharacter the pad character
	 * @return the string
	 */
	public static String padRight(String str,int len,char padCharacter){
		String padStr = pad(str,len,padCharacter);
		return  str + padStr;
	}
	
	/**
	 * *
	 * 补齐公共函数，返回需要补充的字符串.
	 *
	 * @param str the str
	 * @param len the len
	 * @param padCharacter the pad character
	 * @return the string
	 */
	private static String pad(String str,int len,char padCharacter){
		if(str == null) //为控制针，初始做为填充符 
			str = String.valueOf(padCharacter);
		if(str.length()>= len)
			return EMPTY;
		int padLen = len - str.length();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<padLen;i++){
			sb.append(padCharacter);
		}
		return sb.toString();
	}
	
	/** The Constant EMPTY. */
	public static final String EMPTY = "";
	
	/**
	 * *
	 * 判断字符串是否为空,为空返回true.
	 *
	 * @param str the str
	 * @return true, if checks if is empty
	 */
	public static boolean isEmpty(String str){
		if(str == null ){
			return true;
		}else{
			return str.length()==0;			
		}	
	}
	
	/**
	 * *
	 * 判断字符串是否不为空,不为空返回true.
	 *
	 * @param str the str
	 * @return true, if checks if is not empty
	 */
	public static boolean isNotEmpty(String str){
		if(str == null ){
			return false;
		}else{
			return str.length()>0;			
		}	
	}
	
	/**
	 * *
	 * 判断字符串是否为纯数字，是纯数字返回true.
	 *
	 * @param str the str
	 * @return true, if checks if is digital
	 */
	public static boolean isDigital(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}else{
			int strLen = str.length();
			for(int i = 0;i<strLen;i++){
				//逐个字符判断是否为数字
				if( Character.isDigit( str.charAt(i) ) ){
					continue;//是数字则继续判断
				}else{
					return false;//不是数字直接返回
				}
			}
			return true;//循环无退出，代表所有都是数字
		}
	}

	/** The Constant szVerCode. */
	private static final char[] szVerCode = new char[] { '1', '0', 'X', '9',
		'8', '7', '6', '5', '4', '3', '2' };
	
	/** The Constant iW. */
	private static final int[] iW = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
		5, 8, 4, 2, 1 };

	/**
	 * 检查是否18位身份证.
	 *
	 * @param ID18 the I d18
	 * @return true, if checks if is id card no
	 */
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
	 * 15位身份证转换为18位.
	 *
	 * @param ID15 the I d15
	 * @return the string
	 */
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
	 * 检查是否http地址.
	 *
	 * @param value the value
	 * @return true, if checks if is http
	 */
	public static boolean isHttp(String value) {
		return check("^(http:\\/\\/)[a-z0-9A-Z]+([\\.][a-z0-9A-Z]+)+", value,
				false);
	}



	/**
	 * 检查是否email.
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
	 * 是否含有中文.
	 *
	 * @param value the value
	 * @return true, if checks for chinese
	 */
	public static boolean hasChinese(String value) {
		return check("[\u4e00-\u9fa5]", value, true);
	}

	/**
	 * 是否全中文.
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
	 * *
	 * 检查是否匹配正则表达式.
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
	 * Checks if is null or empty.
	 *
	 * @param value the value
	 * @return true, if checks if is null or empty
	 */
	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.length()==0);
	}

	/**
	 * Equal.
	 *
	 * @param a the a
	 * @param b the b
	 * @return true, if equal
	 */
	public static boolean equal(Object a, Object b) {
		return a == b || (a != null && a.equals(b));
	}


	/**
	 * Format.
	 *
	 * @param format the format
	 * @param paramMap the param map
	 * @return the string
	 */
	public static String format(String format, Map<String,String> paramMap) {

		if(format==null){
			return null;
		}

		Set<String> keys=paramMap.keySet();
		Iterator<String> ite=keys.iterator();
		while(ite.hasNext()){
			String key=ite.next();
			String value=paramMap.get(key);
			StringBuilder sb=new StringBuilder();
			sb.append("{");
			sb.append(key);
			sb.append("}");
			format = format.replace(sb.toString(), value);			
		}		

		return format;

	}

	/**
	 * Format.
	 *
	 * @param format the format
	 * @param args the args
	 * @return the string
	 */
	public static String format(String format, Object... args) {
		if(format==null){
			return null;
		}
		for (int i = 0; i < args.length; i++) {	
			StringBuilder sb=new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");		
			String arg=(String) args[i];
			if(arg==null){
				arg="";
			}
			format = format.replace(sb.toString(), arg.toString());

		}
		return format;
	}

	/**
	 * Format.
	 *
	 * @param format the format
	 * @param arg the arg
	 * @return the string
	 */
	public static String format(String format, String arg){
		if(format==null){
			return null;
		}
		int beginIndex=format.indexOf("{");
		int endIndex=format.indexOf("}");
		StringBuilder sb=new StringBuilder();
		sb.append(format.substring(0,beginIndex));
		sb.append(arg);
		sb.append(format.substring(endIndex+1));
		return sb.toString();		
	}

	/**
	 * Format.
	 *
	 * @param format the format
	 * @param args the args
	 * @return the string
	 */
	public static String format(String format, String... args) {
		if(format==null){
			return null;
		}
		for (int i = 0; i < args.length; i++) {	
			StringBuilder sb=new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");		
			String arg= args[i];
			if(arg==null||"null".equals(arg.toLowerCase())){
				arg="";
			}
			format = format.replace(sb.toString(), arg);

		}
		return format;
	}

	/**
	 * Format no replace.
	 *
	 * @param format the format
	 * @param args the args
	 * @return the string
	 */
	public static String formatNoReplace(String format, String... args) {
		if(format==null){
			return null;
		}
		for (int i = 0; i < args.length; i++) {	
			StringBuilder sb=new StringBuilder(3);
			sb.append("{");
			sb.append(i);
			sb.append("}");		
			String arg= args[i];			
			format = format.replace(sb.toString(), arg);

		}
		return format;
	}

	/**
	 * File2 string.
	 *
	 * @param file the file
	 * @return the string
	 * @throws IOException the IO exception
	 */
	public static String file2String(File file)throws IOException{
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);	
		int bufferLength=1024;
		char[] c=new char[bufferLength];		
		int i=0;

		StringBuilder sb=new StringBuilder();
		while(true){		
			i=br.read(c,0,bufferLength);			
			if(i==-1){
				break;
			}else if(i<bufferLength){
				sb.append(c, 0, i);
				break;
			}
			sb.append(c);
		}
		br.close();
		fr.close();	

		return sb.toString();
	}

	/**
	 * Stream2 string.
	 *
	 * @param is the is
	 * @return the string
	 * @throws IOException the IO exception
	 */
	public static String stream2String(InputStream is)throws IOException{
		if(is==null){
			return null;
		}
		InputStreamReader reader=new InputStreamReader(is);	
		BufferedReader br=new BufferedReader(reader);	   

		int bufferLength=1024;
		char[] c=new char[bufferLength];		
		int i=0;

		StringBuilder sb=new StringBuilder();
		while(true){		
			i=br.read(c,0,bufferLength);			
			if(i==-1){
				break;
			}else if(i<bufferLength){
				sb.append(c, 0, i);
				break;
			}
			sb.append(c);
		}
		br.close();
		reader.close();


		return sb.toString();
	}


	/**
	 * Stream2 string.
	 *
	 * @param is the is
	 * @param charsetName the charset name
	 * @return the string
	 * @throws IOException the IO exception
	 */
	public static String stream2String(InputStream is, String charsetName)throws IOException{
		if(is==null){
			return null;
		}

		StringBuilder sb=new StringBuilder();
		InputStreamReader isr=new InputStreamReader(is,charsetName);
		BufferedReader br=new BufferedReader(isr);
		int length=1024;
		char[] cbuf=new char[length];
		int len=0;

		while((len=br.read(cbuf, 0, length))!=-1){
			sb.append(cbuf,0,len);
		} 
		if(br != null)
			br.close();
		if(isr != null)
			isr.close();
		return sb.toString();
	}



	/**
	 * To string.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String toString(String str){
		if(str == null){
			return "";
		}else{
			return str;
		}
	}

	/**
	 * To string.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String toString(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}

	/**
	 * 方法说明：obj为NULL，则返回NULL.
	 *
	 * @param obj the obj
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String toStringNull(Object obj){
		if(obj == null){
			return null;
		}else{
			return obj.toString();
		}
	}

	/**
	 * 方法说明：如果str为null或空串，则返回defaultValue，否则返回str.
	 *
	 * @param str the str
	 * @param defaultValue the default value
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String toString(String str, String defaultValue) {
		return isEmpty(str) ? defaultValue : str;
	}

	/**
	 * 方法说明：1、object为字符串类型时，如果object为null或空串，则返回defaultValue，否则返回object
	 * 2、object不是字符串类型时，如果object为null，则返回defaultValue，否则返回object.toString()
	 *
	 * @param object the object
	 * @param defaultValue the default value
	 * @return the string
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String toString(Object object, String defaultValue) {
		if(object instanceof String) {
			return (object == null || object.toString().length() == 0) ? defaultValue : object.toString();
		} else {
			return object == null ? defaultValue : object.toString();
		}
	}

	/**
	 * Format int.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String formatInt(String str){	   
		return formatInt(str,"");
	}


	/**
	 * Format int.
	 *
	 * @param str the str
	 * @param defaultValue the default value
	 * @return the string
	 */
	public static String formatInt(String str,String defaultValue){

		if(str==null||str.length()==0){
			return defaultValue;
		}	   

		char[] chars=str.toCharArray();
		int length=chars.length;
		double x=length/3d;
		int distLength=length+(int)Math.floor(x);
		char[] dist=new char[distLength];

		for(int i=1,j=1;i<=length;i++,j++){		   
			dist[distLength-j]=chars[length-i];
			if(i%3==0&&i<length){
				j++;
				dist[distLength-j]=',';
			}
		}

		return new String(dist).trim(); 
	}

	/**
	 * Format double.
	 *
	 * @param d the d
	 * @return the string
	 */
	public static String formatDouble(String d){
		DecimalFormat df = new DecimalFormat("#,##0.00");
		if(d==null||d.length()==0){
			return "&nbsp;";
		}else{
			return df.format(Double.parseDouble(d));
		}

	}

	/**
	 * Format double.
	 *
	 * @param d the d
	 * @return the string
	 */
	public static String formatDouble(double d){
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return Double.isNaN(d) || Double.isInfinite(d) ? "&nbsp;" : df.format(d);
	}

	/**
	 * Format double.
	 *
	 * @param d the d
	 * @param pattern the pattern
	 * @return the string
	 */
	public static String formatDouble(double d,String pattern){
		if(pattern==null){
			return formatDouble(d);
		}else{
			DecimalFormat df = new DecimalFormat(pattern);

			return Double.isNaN(d) || Double.isInfinite(d) ? "&nbsp;" : df.format(d);		   
		}

	}

	/**
	 * 方法说明：格式化金额 (保留两位小数).
	 *
	 * @param amount 	金额，单位为分
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String formatAmount(long amount){
		BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(bd.doubleValue());
	}

	/**
	 * 方法说明：格式化金额 (保留两位小数).
	 *
	 * @param amount 	金额，单位为分
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String formatAmount(String amount){
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount));
	}

	/**
	 * 方法说明：格式化金额 (保留两位小数).
	 *
	 * @param amount 	金额，单位为分
	 * @param pattern 格式
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String formatAmount(long amount, String pattern){
		if(pattern==null){
			return formatAmount(amount);
		}else{
			BigDecimal bd = new BigDecimal(amount).divide(new BigDecimal("100"));
			DecimalFormat df = new DecimalFormat(pattern);
			return df.format(bd.doubleValue());		   
		}
	}

	/**
	 * 方法说明：格式化金额 (保留两位小数).
	 *
	 * @param amount 	金额，单位为分
	 * @param pattern 格式
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String formatAmount(String amount, String pattern){
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount), pattern);
	}

	/**
	 * 方法说明：格式化金额 (不带小数).
	 *
	 * @param amount 	金额，单位为分
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String formatAmountNoDecimal(String amount){
		amount = toString(amount, "0");
		return formatAmount(Long.valueOf(amount), "##0");
	}
	

	
	/**
	 * 方法说明：格式化证件号：身份证 只显示前6位加最后一位，如：432524***********7.
	 *
	 * @param certNo the cert no
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳 
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String formatCertNo(String certNo) throws Exception{
		if(!StringUtils.isEmpty(certNo)){
			int length = certNo.length();
			if(length>= 7){
				return StringUtils.rpad(certNo.substring(0,6), "*", length-1) + certNo.charAt(length-1) ;
			}
		}
		return certNo;
	}


	/**
	 * format the float to crresponding String.
	 *
	 * @param val the val
	 * @param percision the percision
	 * @return the string
	 */
	public static String formatFloat(float val, int percision) {
		int count = 1;
		for (int i = 0; i < percision; i++) {
			count *= 10;
		}

		double result =  ((double)Math.round(val * count) / count);        
		return Double.toString(result);
	}

	/**
	 * Format float.
	 *
	 * @param val the val
	 * @param percision the percision
	 * @return the string
	 */
	public static String formatFloat(Float val, int percision) {
		float valFloat = val.floatValue();
		return formatFloat(valFloat, percision);
	}

	/**
	 * Format date.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @return the string
	 */
	public static String formatDate(Date date,String pattern){	   
		SimpleDateFormat sdf=null;
		if(pattern==null){
			sdf=new SimpleDateFormat();
		}else{
			sdf=new SimpleDateFormat(pattern);   
		}
		return sdf.format(date);
	}

	/**
	 * Format date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String formatDate(Date date){	   
		SimpleDateFormat sdf=new SimpleDateFormat();	   
		return sdf.format(date);
	}   

	/**
	 * Parses the date.
	 *
	 * @param source the source
	 * @param pattern the pattern
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String source,String pattern)throws ParseException{	   
		SimpleDateFormat sdf=null;
		if(pattern==null){
			sdf=new SimpleDateFormat();
		}else{
			sdf=new SimpleDateFormat(pattern);   
		}
		return sdf.parse(source);
	}

	/**
	 * Parses the date.
	 *
	 * @param source the source
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String source)throws ParseException{	  
		SimpleDateFormat sdf=new SimpleDateFormat();
		return sdf.parse(source);
	}

	/**
	 * Parses the date.
	 *
	 * @param source the source
	 * @param defaultDate the default date
	 * @return the date
	 */
	public static Date parseDate(String source,Date defaultDate){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat();
			Date date= sdf.parse(source);
			return date;
		}catch(ParseException e){
			//todo
		}
		return defaultDate;
	}

	/**
	 * Parses the date.
	 *
	 * @param source the source
	 * @param pattern the pattern
	 * @param defaultDate the default date
	 * @return the date
	 */
	public static Date parseDate(String source,String pattern,Date defaultDate){
		try{
			SimpleDateFormat sdf=null;
			if(pattern==null){
				sdf=new SimpleDateFormat();
			}else{
				sdf=new SimpleDateFormat(pattern);   
			}
			return sdf.parse(source);	  
		}catch(ParseException e){
			//todo	   
		}
		return defaultDate;
	}

	/**
	 * Try parse.
	 *
	 * @param source the source
	 * @return true, if try parse
	 * @throws ParseException the parse exception
	 */
	public static boolean tryParse(String source)throws ParseException{
		try{
			Integer.parseInt(source);
			return true;
		}catch(NumberFormatException e){}
		return false;

	}

	/**
	 * Parses the int.
	 *
	 * @param source the source
	 * @param defaultReturn the default return
	 * @return the int
	 */
	public static int parseInt(String source,int defaultReturn){
		try{
			return Integer.parseInt(source);	   
		}catch(NumberFormatException e){}
		return defaultReturn;

	}


	/**
	 * Regex match.
	 *
	 * @param source the source
	 * @param regex the regex
	 * @return true, if regex match
	 */
	public static boolean regexMatch(String source,String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}

	/**
	 * Regex catch.
	 *
	 * @param source the source
	 * @param regex the regex
	 * @param index the index
	 * @return the string
	 */
	public static String regexCatch(String source,String regex,int index){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if( matcher.find()){
			return matcher.group(index);
		}
		return null;
	}

	/**
	 * Regex catch more.
	 *
	 * @param source the source
	 * @param regex the regex
	 * @param indexs the indexs
	 * @return the string[]
	 */
	public static String[] regexCatchMore(String source,String regex,int[] indexs){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		String[] results=new String[indexs.length];
		if( matcher.find()){
			for(int i=0;i<indexs.length;i++){
				results[i]= matcher.group(indexs[i]);   
			}

		}
		return results;
	}

	/**
	 * Regex catch.
	 *
	 * @param source the source
	 * @param regex the regex
	 * @return the string
	 */
	public static String regexCatch(String source,String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		if( matcher.find()){
			return matcher.group();
		}
		return null;
	}
	
	
	/**
	 * 方法说明：返回的错误信息中的分转化为元.
	 *
	 * @param errorMsg the error msg
	 * @return the string
	 * @author 彭阳 
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String replaceMsgFenToYuan(String errorMsg) {
		String regex = "\\d+分";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(errorMsg );  
		while (matcher.find()) {  
			String str = matcher.group();  
			errorMsg = errorMsg.replaceFirst(str, StringUtils.formatAmount(str.substring(0, str.length()-1),"#0.00")+"元");
		}
		return errorMsg;
	}

	/**
	 * To string.
	 *
	 * @param params the params
	 * @return the string
	 */
	public static String toString(Object ... params){
		String result="";
		if(params!=null){
			StringBuilder sb=new StringBuilder();
			for(Object param:params){
				if(param!=null){
					sb.append(param.toString());
				}else{
					sb.append("null");
				}
				sb.append(",");			   
			}		   
			int length=sb.length();
			if(length>0){
				sb.deleteCharAt(length-1);
			}
			result=sb.toString();
		}
		return result;

	}

	/**
	 * Prints the.
	 *
	 * @param strings the strings
	 */
	public static void print(String[] strings){
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i]);
			System.out.print(",");
		}


	}

	/**
	 * Prints the.
	 *
	 * @param strings the strings
	 */
	public static void print(String[][] strings){		   
		for(String[] s : strings){
			for(String r:s){
				System.out.print(r);
				System.out.print(",");
			}
			System.out.println();
		}

	}

	/**
	 * **
	 * 身份证、手机号、卡号隐藏信息.
	 *
	 * @param cardNo the card no
	 * @return the string
	 */
	/***
	 * 显示卡末四位
	 * @param cardNo
	 * @return
	 */
	public static String showCardInfo(String cardNo){ 
		//非卡，都返回原值，只有16位数字返回末四位
		if(StringUtils.isNullOrEmpty(cardNo))
			return cardNo;
		if(cardNo.length()!=16)
			return cardNo;
		if(!StringUtils.isDigital(cardNo))
			return cardNo;
		return cardNo.substring(12,16);
	} 
	
	/**
	 * *
	 * 显示身份证号 4***************9.
	 *
	 * @param idNo the id no
	 * @return the string
	 */
	public static String showIDInfo(String idNo){ 
		if(StringUtils.isNullOrEmpty(idNo))
			return idNo; 
		return idNo.substring(0,1)+"****************"+idNo.substring(17,18);
	} 
	
	/**
	 * *
	 * 手机号转换为133****5678
	 * 彭阳.
	 *
	 * @param mobileNO the mobile no
	 * @return the string
	 */
	public static String showMobileNoInfo(String mobileNO){ 
		if(StringUtils.isNullOrEmpty(mobileNO))
			return mobileNO;
		return mobileNO.substring(0,3)+"****"+mobileNO.substring(0,11);
	} 

	/**
	 * Description:
	 * <BR>
	 * filter the same string in array.
	 *
	 * @param strings the strings
	 * @return the string[]
	 */
	public static String[] distinct(String[] strings) {
		if (strings == null)
			return null;
		int size = strings.length;
		String[] temps = new String[size];

		int current = 0;

		//for source array
		for (int i = 0; i < size; i++) {
			String src = strings[i];
			//for target array
			for (int j = 0; j <= current; j++) {
				String target = temps[j];
				//if the value in array is null,then put the value in source array
				if (target == null) {
					temps[j] = src;
					current = j + 1;
					break;
				} else if (src.equals(target)) {// when the value is same in source and target array, then ignore it. 
					break;
				}
			}
		}
		String[] results=new String[current];
		System.arraycopy(temps, 0, results, 0, current);	
		return results;
	}

	/**
	 * 方法说明：左填充.
	 *
	 * @param orgin 原始字符串
	 * @param fill 填充字符
	 * @param length 字符串长度
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String lpad(String orgin , String fill, int length) throws  Exception{
		AssertUtils.notNull(orgin);
		AssertUtils.notNullAndEmpty(length);

		if(fill.length() != 1)
			throw new Exception("填充字符串只支持单个字符。");

		if(orgin.length() > length)
			throw new Exception("原始字符串长度不能大于设定值！");


		while(orgin.length() < length){
			orgin = fill + orgin;
		}

		return orgin;
	} 


	/**
	 * 方法说明：左填充,默认使用 空格。.
	 *
	 * @param orgin the orgin
	 * @param length the length
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String lpad(String orgin , int length) throws  Exception{
		return lpad(orgin, " ", length);
	} 

	/**
	 * 方法说明：右填充.
	 *
	 * @param orgin 原始字符串
	 * @param fill 填充字符
	 * @param length 字符串长度
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String rpad(String orgin , String fill, int length) throws  Exception{
		AssertUtils.notNull(orgin);
		AssertUtils.notNullAndEmpty(length);

		if(fill.length() != 1)
			throw new Exception("填充字符串只支持单个字符。");

		if(orgin.length() > length)
			throw new Exception("原始字符串长度不能大于设定值！");

		while(orgin.length() < length){
			orgin = orgin + fill;
		}

		return orgin;
	} 


	/**
	 * 方法说明：右填充,默认使用 空格。.
	 *
	 * @param orgin the orgin
	 * @param length the length
	 * @return the string
	 * @throws Exception the exception
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static String rpad(String orgin , int length) throws  Exception{
		return rpad(orgin, " ", length);
	}  

	/**
	 * *
	 * 多变量替换(在content里头，替换previousPart + key + afterPart的字符串为value.
	 *
	 * @param replaceMap the replace map
	 * @param content the content
	 * @param previousPart the previous part
	 * @param afterPart the after part
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	public static String replaceAll(Map<String, String> replaceMap, String content, String 	
			previousPart, String afterPart) {
		Set<String> keys = replaceMap.keySet();
		String key = null;
		String value = null;
		for(Iterator it = keys.iterator(); it.hasNext();) {
			key = (String) it.next();
			value = replaceMap.get(key);
			content = content.replaceAll(previousPart + key + afterPart, value);
		}
		return content;
	}
	
	/**
	 * *
	 * utf-8字节流 异常返回null.
	 *
	 * @param str the str
	 * @return the byte[]
	 */
	public static byte[] toUtf8Bytes(String str){
		try {
			//转utf-8
			return str.getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * *
	 * utf-8字节流转换为字符串.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	public static String toUtf8String(byte []bytes){
		try {
			return new String(bytes,DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/** The Constant DEFAULT_CHARSET. */
	public static final String DEFAULT_CHARSET = "utf-8";

	/**
	 * Trim.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String trim(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * Equals.
	 *
	 * @param str1 the str1
	 * @param str2 the str2
	 * @return true, if equals
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
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
	public static String mapToString(Map<String, Object> map) {
		if(map == null || map.isEmpty()) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			buffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}
		return buffer.toString().substring(1);
	}



	/**
	 * New illegal state exception.
	 *
	 * @param charsetName the charset name
	 * @param e the e
	 * @return the illegal state exception
	 */
	private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
		return new IllegalStateException(charsetName + ": " + e);
	}


	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the given charset.
	 * <p>
	 * This method catches {@link UnsupportedEncodingException} and re-throws it as {@link IllegalStateException}, which
	 * should never happen for a required charset name. Use this method when the encoding is required to be in the JRE.
	 * </p>
	 *
	 * @param bytes The bytes to be decoded into characters, may be <code>null</code>
	 * @param charsetName The name of a required {@link java.nio.charset.Charset}
	 * @return A new <code>String</code> decoded from the specified array of bytes using the given charset,
	 * or <code>null</code> if the input byte array was <code>null</code>.
	 * @see CharEncoding
	 * @see String#String(byte[], String)
	 */
	public static String newString(byte[] bytes, String charsetName) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			throw StringUtils.newIllegalStateException(charsetName, e);
		}
	}

	/**
	 * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-8 charset.
	 *
	 * @param bytes The bytes to be decoded into characters
	 * @return A new <code>String</code> decoded from the specified array of bytes using the UTF-8 charset,
	 * or <code>null</code> if the input byte array was <code>null</code>.
	 */
	public static String newStringUtf8(byte[] bytes) {
		return StringUtils.newString(bytes, CharEncoding.UTF_8);
	}


	/**
	 * *
	 * 判断是否数值型（可带小数点）.
	 *
	 * @param numStr the num str
	 * @return true, if checks if is numeric
	 */
	public static boolean isNumeric(String numStr){
		int posDot = numStr.indexOf(".");
		if(posDot<0)
			return isDigital(numStr);
		else{
			return isDigital(numStr.substring(0,posDot))
					&&  isDigital(numStr.substring(posDot+1));
		}
	}

	/**
	 * 将url参数转换成map.
	 *
	 * @param param aa=11&bb=22&cc=33
	 * @return the url params
	 */
	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<String, String>(0);
		if (StringUtils.isNullOrEmpty(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url.
	 *
	 * @param map the map
	 * @return the url params by map
	 */
	public static String getUrlParamsByMap(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = substringBeforeLast(s, "&");
		}
		return s;
	}

	/**
	 * <p>Gets the substring before the last occurrence of a separator.
	 * The separator is not returned.</p>
	 *
	 * <p>A <code>null</code> string input will return <code>null</code>.
	 * An empty ("") string input will return the empty string.
	 * An empty or <code>null</code> separator will return the input string.</p>
	 *
	 * <pre>
	 * StringUtils.substringBeforeLast(null, *)      = null
	 * StringUtils.substringBeforeLast("", *)        = ""
	 * StringUtils.substringBeforeLast("abcba", "b") = "abc"
	 * StringUtils.substringBeforeLast("abc", "c")   = "ab"
	 * StringUtils.substringBeforeLast("a", "a")     = ""
	 * StringUtils.substringBeforeLast("a", "z")     = "a"
	 * StringUtils.substringBeforeLast("a", null)    = "a"
	 * StringUtils.substringBeforeLast("a", "")      = "a"
	 * </pre>
	 *
	 * @param str  the String to get a substring from, may be null
	 * @param separator  the String to search for, may be null
	 * @return the substring before the last occurrence of the separator,
	 *  <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringBeforeLast(String str, String separator) {
		if (isEmpty(str) || isEmpty(separator)) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * *
	 * 首字母大写.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String firstToUpperCase(String str){
		if (isEmpty(str))
			return str;
		return Character.toUpperCase(str.charAt(0))+str.substring(1);
	}
	
	/**
	 * *
	 * 首字母小写.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String firstToLowerCase(String str){
		if (isEmpty(str))
			return str;
		return Character.toLowerCase(str.charAt(0))+str.substring(1);
	}
	
}
