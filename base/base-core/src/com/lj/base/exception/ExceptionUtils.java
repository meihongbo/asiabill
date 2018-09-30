package com.lj.base.exception;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */


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
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class ExceptionUtils {
	
	/**
	 * *
	 * 打印堆栈信息.
	 *
	 * @param e the e
	 * @return the string
	 */
	public static String generateStackTraceInfo(Throwable e){
		StringBuffer sb = new StringBuffer();
		sb.append(e.getMessage());
		for(StackTraceElement se : e.getStackTrace()){ 
			sb.append("["); 
			sb.append(se.getClassName()+"."+se.getMethodName()+"(Line:"+se.getLineNumber()+")");
			sb.append("]\r\n"); 
		}
		return sb.toString();
	} 
}
