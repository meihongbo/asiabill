package com.asiabill.framework.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiabill.framework.dubbo.container.AsiabillLogbackContainer;
import com.asiabill.framework.dubbo.container.AsiabillReportStatusContainer;

/**
 * 
 * <p>Title: Main</p>  
 * <p>Description: dubbo启动类</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午11:00:03
 * @version v1.0
 */
public class Main {

	static {
		AsiabillLogbackContainer.getInstance().start();
	}

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.info('[' + new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + "] asiabill Main start..");

		String reportFile = System.getProperty("asiabill.started.reportfile");
		AsiabillReportStatusContainer.getInstance().setReportFilePath(reportFile);

		init(args);

		logger.info('[' + new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + "] asiabill Main stop..");

		AsiabillLogbackContainer.getInstance().stop();
	}

	private static void init(String[] args) {
		org.apache.dubbo.container.Main.main(args);
	}

}
