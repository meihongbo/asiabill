package com.asiabill.framework.dubbo.threadpool;

import java.util.concurrent.Executor;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.support.cached.CachedThreadPool;
import org.apache.dubbo.common.utils.ConfigUtils;

public class AsiabillCachedThreadPool extends CachedThreadPool {

	@Override
	public Executor getExecutor(URL url) {
		int cores = url.getParameter("corethreads", -1);
		if (cores <= 0) {
			cores = Integer.parseInt(ConfigUtils.getProperty("asiabill.threadpool.corethreads", "10"));
			url = url.addParameter("corethreads", cores);
		}
		int alive = url.getParameter("alive", -1);
		if (alive <= 0) {
			alive = Integer.parseInt(ConfigUtils.getProperty("asiabill.threadpool.alive", "600000"));
			url = url.addParameter("alive", alive);
		}
		String threadname = url.getParameter("threadname");
		if (threadname == null) {
			url = url.addParameter("threadname", "Dubbo-asiabillCache");
		}
		return super.getExecutor(url);
	}

}
