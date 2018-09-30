package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.lang.reflect.Field;

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
public class ReflectUtils {
	
	 /**
 	 * 获取obj对象fieldName的Field.
 	 *
 	 * @param obj the obj
 	 * @param fieldName the field name
 	 * @return the field by field name
 	 */  
    public static Field getFieldByFieldName(Object obj, String fieldName) {  
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass  
                .getSuperclass()) {  
            try {  
                return superClass.getDeclaredField(fieldName);  
            } catch (NoSuchFieldException e) {  
            }  
        }  
        return null;  
    }  
  
    /**
     * 获取obj对象fieldName的属性值.
     *
     * @param obj the obj
     * @param fieldName the field name
     * @return the value by field name
     * @throws SecurityException the security exception
     * @throws NoSuchFieldException the no such field exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */  
    public static Object getValueByFieldName(Object obj, String fieldName)  
            throws SecurityException, NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException {  
        Field field = getFieldByFieldName(obj, fieldName);  
        Object value = null;  
        if(field!=null){  
            if (field.isAccessible()) {  
                value = field.get(obj);  
            } else {  
                field.setAccessible(true);  
                value = field.get(obj);  
                field.setAccessible(false);  
            }  
        }  
        return value;  
    }  
  
    /**
     * 设置obj对象fieldName的属性值.
     *
     * @param obj the obj
     * @param fieldName the field name
     * @param value the value
     * @throws SecurityException the security exception
     * @throws NoSuchFieldException the no such field exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException the illegal access exception
     */  
    public static void setValueByFieldName(Object obj, String fieldName,  
            Object value) throws SecurityException, NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException {  
        Field field = obj.getClass().getDeclaredField(fieldName);  
        if (field.isAccessible()) {  
            field.set(obj, value);  
        } else {  
            field.setAccessible(true);  
            field.set(obj, value);  
            field.setAccessible(false);  
        }  
    }  


}
