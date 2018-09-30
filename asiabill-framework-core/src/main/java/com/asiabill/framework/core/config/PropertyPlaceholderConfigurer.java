package com.asiabill.framework.core.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * <p>Title: PropertyPlaceholderConfigurer</p>  
 * <p>Description: TODO</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午9:59:11
 * @version v1.0
 */
public class PropertyPlaceholderConfigurer
		extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	private static Properties props;

	public Properties mergeProperties() throws IOException {
		props = super.mergeProperties();
		Property.init(props);
		return props;
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
}
