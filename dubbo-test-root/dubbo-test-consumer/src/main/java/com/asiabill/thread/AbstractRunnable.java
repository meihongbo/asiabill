package com.asiabill.thread;

import org.slf4j.MDC;

public abstract class AbstractRunnable implements Runnable {

	private String traceid;
	private String ip;

	public AbstractRunnable(String traceid, String ip) {
		this.traceid = traceid;
		this.ip = ip;
	}

	private void log() {
		MDC.put(Constant.TRANC_ID, traceid);
		MDC.put(Constant.LOCAL_IP, ip);
	}

	protected abstract void thread();

	public void run() {
		log();
		thread();
	}

	public String getTraceid() {
		return traceid;
	}

	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
