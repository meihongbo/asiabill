package com.asiabill.framework.dubbo.container;

import java.io.File;
import java.io.IOException;

import org.apache.dubbo.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>Title: AsiabillReportStatusContainer</p>  
 * <p>Description: 服务状态报告启动类</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午10:33:07
 * @version v1.0
 */
public class AsiabillReportStatusContainer implements Container {

	private static AsiabillReportStatusContainer instance = new AsiabillReportStatusContainer();
	private static final Logger LOGGER = LoggerFactory.getLogger(AsiabillReportStatusContainer.class);
	private volatile boolean hasStart = false;
	private String reportFilePath = "";

	public static AsiabillReportStatusContainer getInstance() {
		return instance;
	}

	public void setReportFilePath(String strpath) {
		instance.reportFilePath = strpath;
	}

	@Override
	public synchronized void start() {
		if (instance.hasStart) {
			LOGGER.info("This container has started.");
			return;
		}
		instance.hasStart = true;
		if ((instance.reportFilePath == null) || (instance.reportFilePath.isEmpty())) {
			LOGGER.info("Report file path is empty.");
			return;
		}
		File f = new File(instance.reportFilePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			LOGGER.warn("Create report file failed: " + f.getAbsolutePath(), e);
		}
		LOGGER.warn("Container[" + getClass().getSimpleName() + "] started.");
	}

	@Override
	public synchronized void stop() {
		instance.hasStart = false;
		LOGGER.warn("Container[" + getClass().getSimpleName() + "] stopped.");
	}

}
