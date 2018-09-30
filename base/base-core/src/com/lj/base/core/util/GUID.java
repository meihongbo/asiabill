package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Random;
import java.util.UUID;

import com.lj.base.core.hardware.LocalMachineInfo;

/**
 * 
 * 
 * 类说明：用算法生成全球唯一标识
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
public class GUID {
	
	
	public static void main(String args []){
		//System.out.println(GUID.generateByIP());
		for (int i = 0; i < 2; i++) {
			System.out.println(GUID.getPreUUID());
		}
		
	}
	
	/**
	 * *
	 * 根据java UUID生成全球唯一字符串（32位）.
	 *
	 * @return the string
	 */
	public static String generateByUUID(){
	    String uuid = 	UUID.randomUUID().toString();
	    return uuid.replaceAll("-","");
	}
	/**
	 * 
	 *
	 * 方法说明：适用于数据表唯一主键生成。
	 *根据java UUID生成全球唯一字符串（40位）.
	 * 前8位前缀区分体系 
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月21日
	 *
	 */
	public static String getPreUUID(){
	    return "LJ_"+ generateByUUID();
	}
	
	/**
	 * 
	 *
	 * 方法说明：适用于数据表唯一主键生成。
	 * 根据java UUID生成全球唯一字符串（40位）.
	 * 前8位前缀区分体系 
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月21日
	 *
	 */
	public static String generateCode(){
	    return getPreUUID();
	}
	
	/** ** 计数器，启动产生一个随机数. */
	private static Object SYNC_LOCK = new Object();
	
	/** The count machine. */
	private static long COUNT_MACHINE =Math.abs( new Random().nextLong() ) ;
	
	/** The IP v4_ en d_ byte. */
	private static int IPV4_END_BYTE = Integer.valueOf(String.valueOf((LocalMachineInfo.getAddressIPv4()[3]))) + 600;
	
	/**
	 * *
	 * 18位的单机算法，保证系统需要的唯一序列号
	 * （注意：限制场景为IP地址最后三位不能重复，也就意味着某个系统，其分布式的机器IP最后三位要各自不一样！）
	 * IPv4的最后3位+日期12位（到毫秒，去掉第1位）+3位校验位.
	 *
	 * @return the string
	 */
	public static String generateByIP(){
		 String guidbyIP  = null;
		//----IP最后以为------- 
		synchronized(SYNC_LOCK){
			COUNT_MACHINE++;
			guidbyIP =  IPV4_END_BYTE + String.valueOf(System.currentTimeMillis()).substring(1) +StringUtils.padLeft(String.valueOf(COUNT_MACHINE%1000),3,'0');
		} 
		return guidbyIP;
	}
 
	/**
	 * *
	 * 18位的单机算法，保证系统需要的唯一序列号
	 * （注意：限制场景为IP地址最后三位不能重复，也就意味着某个系统，其分布式的机器IP最后三位要各自不一样！）
	 * IPv4的最后3位+日期12位（到毫秒，去掉第1位）+3位校验位.
	 *
	 * @return the long
	 */
	public static Long generateDigitalByIP(){ 
		return Long.valueOf(generateByIP());
	}
	
	/**
	 * *
	 * IP在最前面+后面序列号
	 * 但可能重复哦，目前最大9位.
	 *
	 * @return the int
	 */
	public static int generateSequenceByIP(){
		int seqByIp  = 0;
		//----IP最后以为------- 
		synchronized(SYNC_LOCK){
			COUNT_MACHINE++;
			//IP在最前面+后面序列号
			seqByIp =  IPV4_END_BYTE *SEQ_DIGITAL_MULTIPLY+(int)(COUNT_MACHINE %SEQ_DIGITAL_MULTIPLY);
		} 
		return seqByIp;
	}
	
	/** * 产生多大的seq，（IP移位+序列号）. */
	private static final int SEQ_DIGITAL_MULTIPLY = 1000000;
	
}
