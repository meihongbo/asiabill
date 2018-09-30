package com.lj.base.core.hardware;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaRuntimeException;

/**
 * 
 * 
 * 类说明：
 * 
 * 获取本机相关信息，getCpu演示了如何通过Java代码执行shell脚本获取输出
 * 获取CPU暂时只对windows有效.
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class LocalMachineInfo {
	
	/**
	 * *
	 * 获取IP地址（IPv4，x.x.x.x）
	 *
	 * @return the address
	 */
	public static String getAddress(){ 
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();//String.valueOf(addr.getAddress()[3]);
		} catch (UnknownHostException e) {
			
			throw new TsfaRuntimeException("tsfacore-getAddress","UnknownHost:"+e);
		} 
	    
	}


    /**
     * Description: linux下获得本机IPv4 IP<br>.
     *
     * @return the address i pv4
     * @see
     */
    public static byte[] getAddressIPv4(){  
		try {
		    //获取所有网卡
			Enumeration<NetworkInterface> e1  = (Enumeration<NetworkInterface>)NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()){
	                NetworkInterface ni = e1.nextElement(); 
                    Enumeration<InetAddress> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()){
                        InetAddress ia = e2.nextElement();
                        if (ia instanceof Inet6Address) //IPv6，不要
                            continue; 
                        if(127 == ia.getAddress()[0]) //127.0.0.1不要
                        	continue;
                        return ia.getAddress();
                    } //地址遍历
	        } //网卡遍历
		} catch (SocketException e) {
			throw new TsfaRuntimeException("getip-error","获取IP地址错误"+e);
		} 
		throw new TsfaRuntimeException("getip-noiperror","本机无IP！");
    }
    
	/**
	 * *
	 * 获取主机名称.
	 *
	 * @return the host name
	 */
	public static String getHostName(){
		try {
			InetAddress addr = InetAddress.getLocalHost(); 
			return addr.getHostName();
		} catch (UnknownHostException e) {
			
			throw new TsfaRuntimeException("tsfacore-getAddress","UnknownHost:"+e);
		} 
	}
	
	/**
	 * *
	 * 通过执行cmd获取输出，截取关键字拿到CPU ID
	 * windows有效.
	 *
	 * @return the cpu
	 */
	public static String getCpu(){  
		try {
			//windows有效
			String []cmdArray = new String[] { "wmic", "cpu", "get", "ProcessorId" };
			 Process process = Runtime.getRuntime().exec( cmdArray ); 
			  process.getOutputStream().close();  //Process类的输入输出流都在InputStream
			  process.getErrorStream().close();  
 
			  //逐行读取的类（Scanner）
			  Scanner sc = new Scanner(process.getInputStream());  
			  String property = sc.next();  //命令wmic cpu get ProcessorId 第一行
			  AssertUtils.state(cmdArray[3].equals(property));
			  String serial = sc.next();   //第二行，CPU序列号
			  
			  sc.close();  
			  return serial;
		} catch (IOException e) {
			throw new TsfaRuntimeException("getCpu-error","获取CPU序列号错误"+e);
		}  
	 

	} 
}




