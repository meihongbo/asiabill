package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
public class ThreadUtils {

	/**
	 * sleep等待,单位毫秒,忽略InterruptedException.
	 *
	 * @param millis the millis
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// Ignore.
		}
	}

	/**
	 * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法.
	 * 先使用shutdown尝试执行所有任务.
	 * 超时后调用shutdownNow取消在workQueue中Pending的任务,并中断所有阻塞函数.
	 * 另对在shutdown时线程本身被调用中断做了处理.
	 *
	 * @param pool the pool
	 * @param timeout the timeout
	 * @param timeUnit the time unit
	 */
	public static void gracefulShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(timeout, timeUnit)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(timeout, timeUnit)) {
					System.err.println("Pool did not terminate");
				}
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 直接调用shutdownNow的方法.
	 *
	 * @param pool the pool
	 * @param timeout the timeout
	 * @param timeUnit the time unit
	 */
	public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
		try {
			pool.shutdownNow();
			if (!pool.awaitTermination(timeout, timeUnit)) {
				System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 自定义ThreadPool,可定制线程池的名称.
	 */
	public static class CustomizableThreadFactory implements ThreadFactory {

		/** The name prefix. */
		private final String namePrefix;

		/** The thread number. */
		private final AtomicInteger threadNumber = new AtomicInteger(1);

		/**
		 * The Constructor.
		 *
		 * @param poolName the pool name
		 */
		public CustomizableThreadFactory(String poolName) {
			namePrefix = poolName + "-";
		}

		/* (non-Javadoc)
		 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
		 */
		public Thread newThread(Runnable runable) {
			return new Thread(runable, namePrefix + threadNumber.getAndIncrement());
		}
	}
}
