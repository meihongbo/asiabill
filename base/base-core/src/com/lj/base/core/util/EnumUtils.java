package com.lj.base.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

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
 * @author 冯辉
 *   
 * CreateDate: 2017年7月12日
 */
public class EnumUtils {
	
	/**
	 * 
	 *
	 * 方法说明：获取list
	 *
	 * @param ref
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月12日
	 *
	 */
	public static <T> List<EnumKv> fromEnum(Class<T> ref){
		List<EnumKv> list = new ArrayList<EnumKv>();
		T[] ts = ref.getEnumConstants();
		for (T ec : ts) {
            try {
            	Enum<?> tempEnum = (Enum<?>) ec ; 
            	 String key = tempEnum.toString();
				 String value = (String)PropertyUtils.getProperty(ec, "name");
				 EnumKv enumKv = new EnumKv();
				 enumKv.setKey(key);
				 enumKv.setValue(value);
				 list.add(enumKv);
			}  catch (Exception e) {
			}
        }
		return list;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取value
	 *
	 * @param ref
	 * @param key
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月12日
	 *
	 */
	public static <T> String getValue(Class<T> ref,String key){
		String ret = "";
		T[] ts = ref.getEnumConstants();
		for (T ec : ts) {
            try {
            	Enum<?> tempEnum = (Enum<?>) ec ; 
            	 if(key.equals(tempEnum.toString())){
            		 ret = (String)PropertyUtils.getProperty(ec, "name");
            	 }
			}  catch (Exception e) {
			}
        }
		return ret;
	} 

}
