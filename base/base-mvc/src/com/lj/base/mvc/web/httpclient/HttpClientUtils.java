package com.lj.base.mvc.web.httpclient;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaRuntimeException;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：对httpclient包的封装简化,直接进行get/post请求处理（注意，不考虑session）
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class HttpClientUtils {
	
	/** The Constant DEFAULT_ENCODING. */
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * *
	 * 根据url请求获取web内容
	 * 注意，url参数要经过urlEncode编码.
	 *
	 * @param url the url
	 * @return the from web
	 */
	public static String getFromWeb(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse httpresponse = httpclient.execute(httpget);
			return StringUtils.stream2String(
					httpresponse.getEntity().getContent(),
					httpresponse.getEntity().getContentEncoding()==null?DEFAULT_ENCODING:httpresponse.getEntity().getContentEncoding().getValue()
                   );
			
		} catch (ClientProtocolException e) {
			
			throw new TsfaRuntimeException("tsfa-mvc-getFromWeb","getFromWeb error:"+e);
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new TsfaRuntimeException("tsfa-mvc-getFromWeb","getFromWeb error:"+e);
		}

	}
	
	/**
	 * *
	 * POST提交(UTF-8).
	 *
	 * @param url the url
	 * @param params the params
	 * @return the string
	 */
	public  static String postToWeb(String url,Map<String,String> params){
		return postToWeb(url,DEFAULT_ENCODING,params);
	}
	
	
	/**
	 * 方法说明：POST提交.
	 *
	 * @param url the url
	 * @param enc the enc
	 * @param params the params
	 * @param responseEnc 返回编码
	 * @return the string
	 * 
	 * @author 彭阳 
	 * CreateDate: 2017年7月1日
	 */
	public static String postToWeb(String url,String enc, Map<String,String>  params,String responseEnc){
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url); 
			
			HttpEntity entity = new UrlEncodedFormEntity(convertMapToPostParams(params),enc);
			httppost.setEntity(entity);
			HttpResponse httpresponse = httpclient.execute(httppost);
			return StringUtils.stream2String(
					httpresponse.getEntity().getContent(),
					httpresponse.getEntity().getContentEncoding()==null?responseEnc:httpresponse.getEntity().getContentEncoding().getValue()
                   );
			
		} catch (ClientProtocolException e) {
			
			throw new TsfaRuntimeException("tsfa-mvc-getFromWeb","getFromWeb error:"+e);
		} catch (IOException e) {
			
			throw new TsfaRuntimeException("tsfa-mvc-getFromWeb","getFromWeb error:"+e);
		}
	}
	
	/**
	 * *
	 * POST提交.
	 *
	 * @param url the url
	 * @param enc the enc
	 * @param params the params
	 * @return the string
	 */
	public static String postToWeb(String url,String enc, Map<String,String>  params) { 
		return postToWeb(url,DEFAULT_ENCODING,params,DEFAULT_ENCODING);
		

	}
	
	/**
	 * Convert map to post params.
	 *
	 * @param params the params
	 * @return the list< name value pair>
	 */
	private static List<NameValuePair> convertMapToPostParams(Map<String,String> params){
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for(String key : params.keySet()){ 
			formparams.add(new BasicNameValuePair(key, params.get(key))); 
		}
		return formparams;
	}
	
	/**
	 * *
	 * 对字符串进行http url编码(UTF-8).
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String urlEncode(String str){
		try {
			return URLEncoder.encode(str, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			
			throw new TsfaRuntimeException("tsfa-mvc-urlEncode","urlencode error:"+e);
		}
	}
	
	/**
	 * *
	 * 对字符串进行http url编码(enc).
	 *
	 * @param str the str
	 * @param enc the enc
	 * @return the string
	 */
	public static String urlEncode(String str,String enc){
		try {
			return URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			
			throw new TsfaRuntimeException("tsfa-mvc-urlEncode","urlencode"+" "+enc+"  error:"+e);
		}
	}
	
	/**
	 * 方法说明：获取客户端IP.
	 *
	 * @param request the request
	 * @return the ip address
	 * @author 彭阳
	 * 
	 * CreateDate: 2017年7月1日
	 */
	public static String getIpAddress(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
            ip = request.getHeader("X-Forwarded-For"); 
        } 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
        	ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if(StringUtils.isNullOrEmpty(ip) || ip.equalsIgnoreCase("unknown")) { 
            ip = request.getRemoteAddr(); 
        } 
        if(StringUtils.isNotEmpty(ip) && ip.indexOf(",") > 0) { 
        	String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if(null != ips[i] && !"unknown".equalsIgnoreCase(ips[i])){
					ip = ips[i];
					break;
				}
			}
        } 
        return ip; 
    } 
}
