package com.asiabill.container;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.container.Container;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContainer implements Container {

	
	private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);
	public static final String SPRING_CONFIG = "dubbo.spring.config";
	public static final String DEFAULT_SPRING_CONFIG = "classpath*:spring/*.xml";
	static ClassPathXmlApplicationContext context;
	
	public static ClassPathXmlApplicationContext getContext() { 
		return context; 
	}

	@Override
	public void start() {
		String configPath = ConfigUtils.getProperty(SPRING_CONFIG);
		if (configPath == null || configPath.length() == 0) {
			configPath = DEFAULT_SPRING_CONFIG;
		}
		context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
		context.start();
	}

	@Override
	public void stop() {
		try {
			if (context != null) {
				context.stop();
				context.close();
				context = null;
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}
