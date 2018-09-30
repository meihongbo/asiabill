package com.lj.base.mvc.base.json;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;



/**
 * 
 * 
 * 类说明：处理json的工具类，负责json数据转换成java对象和java对象转换成json
 * 
 * <p>
 * 详细描述：
 *  需要四个包：  org.apache.commons(3.2以上版本)
 *	org.apache.oro
 *	net.sf.ezmorph(ezmorph-1.0.4.jar)
 *	json-lib  
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class JsonUtils{   

	/**
	 * *
	 * 将obj转换为json格式.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String jsonFromObject(Object obj) {
		AssertUtils.notNull(obj, "tsfa-mvc-jsonutils需要转换的json对象为空"); 
		JSONObject ja = JSONObject.fromObject(obj);
		return  ja.toString() ;  
	}

	/**
	 * *
	 * list转换为json.
	 *
	 * @param list the list
	 * @return the string
	 */
	@SuppressWarnings("rawtypes")
	public static String jsonFromList(List list){  
		StringBuffer  json = new StringBuffer();
		json.append("[");
		if(list!=null){
			int idx = 0;
			for(Object obj : list){
				if(idx>0)
					json.append(",");
				json.append(jsonFromObject_AllToString(obj));
				idx++;
			}
		}
		json.append("]");
		return json.toString();
	}

	/**
	 * 方法说明：将json字符串转换为数组.
	 *
	 * @param json the json
	 * @param clazz the clazz
	 * @return the list
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	@SuppressWarnings("rawtypes")
	public static List listFromJson(String json, Class clazz) {
		JSONArray jsonarray = JSONArray.fromObject(json);
		return (List) JSONArray.toCollection(jsonarray, clazz);
	}

	/**
	 * *
	 * 
	 * 
	 * 方法说明：将obj转换为json格式.
	 *
	 * @param obj the obj
	 * @param config the config
	 * @return the string
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static String jsonFromObject(Object obj, JsonConfig config) {
		AssertUtils.notNull(obj, "tsfa-mvc-jsonutils需要转换的json对象为空"); 
		JSONObject ja = JSONObject.fromObject(obj,config);
		return  ja.toString() ;  
	}

	/**
	 * 方法说明：将obj转换为json格式,Long值转化为String输出.
	 *
	 * @param obj the obj
	 * @return the string
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 */
	public static String jsonFromObject_LongToString(Object obj) {
		AssertUtils.notNull(obj, "tsfa-mvc-jsonutils需要转换的json对象为空"); 
		JsonConfig config = new JsonConfig();  
		config.registerJsonValueProcessor(Long.class,new LongJsonValueProcessor());
		JSONObject ja = JSONObject.fromObject(obj,config);
		return  ja.toString() ;  
	}

	/**
	 * 方法说明：将obj转换为json格式,所有值转化为String输出.
	 *
	 * @param obj the obj
	 * @return the string
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 */
	public static String jsonFromObject_AllToString(Object obj) {
		AssertUtils.notNull(obj, "tsfa-mvc-jsonutils需要转换的json对象为空"); 
		JsonConfig config = new JsonConfig();  
		config.registerJsonValueProcessor(Long.class,new LongJsonValueProcessor());
		config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor());
		config.registerJsonValueProcessor(Float.class,new FloatJsonValueProcessor());
		JSONObject ja = JSONObject.fromObject(obj,config);
		return  ja.toString() ;  
	}

	/**
	 * 方法说明：JSON对象转换Map.
	 *
	 * @param json the json
	 * @return the map< string, string>
	 * @author 彭阳
	 * CreateDate: 2017-7-1
	 */
	public static Map<String, String> mapFromJson(String json){
		JSONObject ja = JSONObject.fromObject(json);    
		return toMap(ja);
	} 

	/**
	 * *
	 * json字符串.
	 *
	 * @param json the json
	 * @param cls the cls
	 * @return the object
	 */
	@SuppressWarnings("rawtypes")
	public static Object objectFromJson(String json,Class cls){
		return objectFromJson(json, cls, null);	 
	} 

	/**
	 * *
	 * 
	 * 
	 * 方法说明：将json串转为复杂对象（对象属性包括List、 Map、ArrayList、自定义类型）.
	 *
	 * @param json the json
	 * @param cls the cls
	 * @param classMap 复杂类型集合，key为属性名、value为属性类型
	 * @return the object
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	@SuppressWarnings("rawtypes")
	public static Object objectFromJson(String json, Class cls, Map<String, Class> classMap){
		if(String.class.equals(cls))	//如果转换为字符串，则直接返回
			return json;
		JSONObject ja = JSONObject.fromObject(json);
		if( Map.class.equals(cls) ) 
			return toMap(ja);
		else return JSONObject.toBean(ja, cls, classMap);		 
	} 

	/**
	 * *
	 * JSON对象转换Map.
	 *
	 * @param jsonObject the json object
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked") 
	private static Map<String, String> toMap(JSONObject jsonObject)  { 
		Map<String, String> result = new HashMap<String, String>(); 
		Iterator<String> iterator = jsonObject.keys(); 
		String key = null; 
		String value = null; 
		while (iterator.hasNext()) { 
			key = iterator.next(); 
			value = jsonObject.getString(key); 
			result.put(key, "null".equalsIgnoreCase(value) ? null : value); 
		} 
		return result; 
	}



	public static void main(String args []){
		//测试代码
		/*Map<String, Class> classMap = new HashMap<String, Class>();
		//classMap.put("startDate", Date.class);
		//classMap.put("endDate", Date.class);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" ,"yyyy-MM-dd HH:mm"})); 
		String paramJson = "{\"startDate\":\"2016-06-01 12:29:00\",\"endDate\":\"2016-06-02 12:29\"}";
		JsonTestBean addLeaveInfo = (JsonTestBean)objectFromJson(paramJson , JsonTestBean.class, classMap);
		System.out.println(addLeaveInfo);
		System.out.println(DateUtils.formatDate(addLeaveInfo.getStartDate(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss) );
		System.out.println(DateUtils.formatDate(addLeaveInfo.getEndDate(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss) );*/
	}
}      
