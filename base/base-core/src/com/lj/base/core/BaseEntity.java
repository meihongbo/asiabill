package com.lj.base.core;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.Serializable;
 

/**
 * 
 * 
 * 类说明：基础领域模型类
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
public abstract class BaseEntity implements Serializable {		
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
	/** The _id. */
//	private String _id ;
	

	/**
	 * Get_id.
	 *
	 * @return the _id
	 */
	/*public String get_id() {
		return _id;
	}*/


	/**
	 * Set_id.
	 *
	 * @param _id the _id
	 */
	/*public void set_id(String _id) {
		this._id = _id;
	}*/


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//PermGen OOM(9/28凌晨3点报OOM PermGen,本函数引起)
		return "Nothing Printed to You,You should override toString().";
		//利用反射枚举所有属性并打印
//		StringBuilder buf = new StringBuilder();
//		Method[] methods = this.getClass().getMethods();
//		boolean isFirst = true;
//		for (int i = 0, n = methods.length; i < n; i++) {
//			try {
//				Method method = methods[i];
//				if ((method.getModifiers() & Modifier.PUBLIC) == 1
//						&& method.getDeclaringClass() != Object.class
//						&& (method.getParameterTypes() == null || method
//								.getParameterTypes().length == 0)) {
//					String methodName = method.getName();
//					String property = null;
//					if (methodName.startsWith("get")) {
//						property = methodName.substring(3, 4).toLowerCase()
//								+ methodName.substring(4);
//					} else if (methodName.startsWith("is")) {
//						property = methodName.substring(2, 3).toLowerCase()
//								+ methodName.substring(3);
//					}
//					if (property != null) {
//						Object value = method.invoke(this, new Object[0]);
//						if (isFirst)
//							isFirst = false;
//						else
//							buf.append(",");
//						buf.append(property);
//						buf.append(":");
//						if (value instanceof String)
//							buf.append("\"");
//						buf.append(value);
//						if (value instanceof String)
//							buf.append("\"");
//					}
//				}
//			} catch (Exception e) {
//				// ignore
//			}
//		}
//		return "{" + buf.toString() + "}";
	}

}
