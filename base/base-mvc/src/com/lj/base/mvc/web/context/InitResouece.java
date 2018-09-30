package com.lj.base.mvc.web.context;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

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
public class InitResouece {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(InitResouece.class);
	
	/** The Constant DEFAULT_APPLICATION_PATH. */
	public static final String DEFAULT_APPLICATION_PATH = "/application.properties";
	
	/** The default props. */
	private static Properties defaultProps = null;

	static {

		try {
			ClassPathResource resource = new ClassPathResource(
					DEFAULT_APPLICATION_PATH, InitResouece.class);
			defaultProps = null;
			defaultProps = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException ex) {
			// throw new
			// IllegalStateException("Could not load 'application.properties': "
			// + ex.getMessage());
			String errorMsg = "can't load application.properties and ignore it,you can put application.properties in class path";
			// System.err.println(errorMsg);
			logger.warn(errorMsg);
		}

		if (defaultProps != null) {
			// set locale
			String language = defaultProps.getProperty("application.language");
			String country = defaultProps.getProperty("application.country");
			if (language != null && !"".equals(language.trim())) {
				Locale defaultLocal = null;
				if (language != null && !"".equals(language.trim())) {
					defaultLocal = new Locale(language, country);
				} else {
					defaultLocal = new Locale(language);
				}
				Locale.setDefault(defaultLocal);
			}

		}

	}

	/**
	 * Description: <BR>.
	 *
	 * @param contextConfigLocation the context config location
	 * @return the config locations
	 */
	public static String[] getConfigLocations(String contextConfigLocation) {

		String[] configLocations = new String[1];

		// load all msite-*.xml in every msite child project
//		configLocations[0] = "classpath*:plugin/msite-*.xml";

		if (contextConfigLocation == null) {
			// application.xml will import the xml that needed
			configLocations[0] = "classpath:application.xml";

		} else {
			configLocations[0] = contextConfigLocation;
		}

		return configLocations;

	}

}
