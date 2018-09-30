package com.asiabill.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MyThread extends AbstractRunnable {
	
	private static Logger logger = LoggerFactory.getLogger(MyThread.class);

	public MyThread(String traceid, String ip) {
		super(traceid, ip);
	}

	@Override
	protected void thread() {
		logger.info("thread name:{}  ,trace_id:{}  ,local_ip:{}",new String[] { Thread.currentThread().getName(), MDC.get(Constant.TRANC_ID), MDC.get(Constant.TRANC_ID)});

	}

}
