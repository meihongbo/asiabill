package com.lj.base.mvc.bean;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

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
public class BeanMapConverter {
	static{
		//加载立即注册日期转换器
		ConvertUtils.register(new DateConverter(), Date.class);
		//枚举
		ConvertUtils.register(new EnumConverter(), Enum.class); 
		//Boolean
		ConvertUtils.register(new BooleanConverter(), Boolean.class); 
	}
	
	/**
	 * *
	 * object 2 map.
	 *
	 * @param obj the obj
	 * @return the map
	 */
	@SuppressWarnings("rawtypes")
	public static Map bean2map(Object obj){ 
		Map map = null;
		try {
			map = BeanUtils.describe(obj);
			BeanUtils.copyProperties(map, obj);
		} catch (Throwable e) { 
			//异常，返回null
		}		
		return map;
	}
	
	/**
	 * *
	 * map2bean.
	 *
	 * @param map the map
	 * @param cls the cls
	 * @return the object
	 */
	@SuppressWarnings("rawtypes")
	public static Object map2bean(Map map,Class cls){ 
		Object obj = null;
		try {
			obj = cls.newInstance();
			BeanUtils.copyProperties(obj, map);
		} catch (Throwable e) {
			// 异常，返回null
			e.printStackTrace();
		} 
		return obj;
	}
	
	/*public static void main(String args []){
		Map req = new HashMap();
		req.put("testStr",null);
		BeanMapConverter.Test id = (BeanMapConverter.Test) BeanMapConverter.map2bean(req ,BeanMapConverter.Test.class);
		System.out.println(id);
	}
	
	public static class Test{
		private Boolean testStr;

		public Boolean getTestStr() {
			return testStr;
		}

		public void setTestStr(Boolean testStr) {
			this.testStr = testStr;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Test [testStr=").append(testStr).append("]");
			return builder.toString();
		}
		
	}*/
	
	
}
