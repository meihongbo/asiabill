package com.asiabill.framework.core.config;

import java.util.Properties;

/**
 * 
 * <p>Title: Property</p>  
 * <p>Description: 系统变量Property</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月29日 下午6:06:59
 * @version v1.0
 */
public class Property {

	private static Properties property;
	  
	  static void init(Properties props)
	  {
	    property = props;
	  }
	  
	  public static String getProperty(String key)
	  {
	    if (key == null) {
	      return null;
	    }
	    return property.getProperty(key);
	  }
	  
	  public static String getProperty(String key, String defaultValue)
	  {
	    return property.getProperty(key, defaultValue);
	  }
}
