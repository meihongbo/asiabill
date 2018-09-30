package com.asiabill.utils;

import java.util.UUID;

import org.apache.dubbo.common.utils.NetUtils;

/**
 * 
 * <p>Title: TraceIdUtil</p>  
 * <p>Description: 线程工具类</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月29日 上午10:54:19
 * @version v1.0
 */
public class ThreadUtils {
	
	private ThreadUtils() {}
	
	/**
	 * 
	  * @author: mhb
	  * @Title getTraceId 
	  * @Time: 2018年9月29日 上午10:57:15
	  * @Description: 获取TraceId
	  * @return: String
	  * @param 
	 */
	public static String getTraceId() {
		long currentTimeMillis = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 4, uuid.length());
		return currentTimeMillis + uuid;
	}
	
	public static String getLocalHost() {
		return NetUtils.getLocalHost();
	}
	
	public static void main(String[] args) {
		System.out.println(getTraceId());
	}

}
