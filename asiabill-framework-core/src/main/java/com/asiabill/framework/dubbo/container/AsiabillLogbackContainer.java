package com.asiabill.framework.dubbo.container;

import org.apache.dubbo.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>Title: AsiabillLogbackContainer</p>  
 * <p>Description: 日志容器启动类</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午10:32:39
 * @version v1.0
 */
public class AsiabillLogbackContainer implements Container {

	public static final String DEFAULT_ADAPTER = "slf4j";
	private static AsiabillLogbackContainer instance = new AsiabillLogbackContainer();
	private volatile boolean hasStart = false;
	private volatile Logger logger = null;

	public static AsiabillLogbackContainer getInstance() {
		return instance;
	}

	@Override
	public synchronized void start() {
		if (instance.hasStart) {
			return;
		}
		instance.hasStart = true;
		System.setProperty("dubbo.application.logger", "slf4j");

		this.logger = LoggerFactory.getLogger(AsiabillLogbackContainer.class);
		this.logger.warn("Container[" + getClass().getSimpleName() + "] started.");
	}

	@Override
	public synchronized void stop() {
		if (!instance.hasStart) {
			return;
		}
		instance.hasStart = false;
		this.logger.warn("Container[" + getClass().getSimpleName() + "] stopped.");
	}

}
