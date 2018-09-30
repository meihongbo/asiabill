package com.asiabill.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.asiabill.utils.ThreadUtils;

public class MyThread extends AbstractRunnable {
	
	private static Logger logger = LoggerFactory.getLogger(MyThread.class);

	public MyThread() {
		super(ThreadUtils.getTraceId(), ThreadUtils.getLocalHost());
	}

	@Override
	protected void thread() {
		logger.info("thread name:{}  ,trace_id:{}  ,local_ip:{}",new String[] { Thread.currentThread().getName(), MDC.get(Constant.TRANC_ID), MDC.get(Constant.TRANC_ID)});
	}

}
