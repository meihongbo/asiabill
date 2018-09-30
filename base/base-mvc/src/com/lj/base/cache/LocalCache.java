package com.lj.base.cache;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.exception.TsfaRuntimeException;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 * 简单的本地缓存，【适合变化频率非常低，分布式系统各自存自己的副本不会出问题的场景】
 * 缓存获取数据，业务系统实现ICache
 * LocalCache定时自动刷新
 * 
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class LocalCache {
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);

	/** The cache data. */
	private static Map cacheData = new HashMap();
    
    /** * 系统会根据key保存四个数据，key对应缓存数据，TIMEOUT_PREFIX+Key对应缓存时长 LAST_UPDATE_PREFIX对应缓存最后更新时间 ICACHE_PREFIX对于缓存失效后更新数据的对象. */
    private static final String TIMEOUT_PREFIX = "CACHE_TIMEOUT_";
    
    /** The Constant LAST_UPDATE_PREFIX. */
    private static final String LAST_UPDATE_PREFIX = "CACHE_LAST_UPDATE_";
    
    /** The Constant ICACHE_PREFIX. */
    private static final String ICACHE_PREFIX = "CACHE_OBJECT_";
    
    /** The Constant SYNC_LOCKER. */
    private static final Object SYNC_LOCKER = new Object();//锁
    
    /** * 默认过期时间为1小时. */
    public static final long DEFAULT_TIMEOUT_MILLISECONDS = 1000*60*60;
    
    /** * 一天的毫秒数. */
    public static final long ONEDAY_TIMEOUT_MILLISECONDS = 1000*60*60*24;
    
    /**
     * *
     * 本地缓存，将cache.getNewestData放入缓存
     * 并且缓存了最长缓存失效时间millisecondsTimeout，及cache对象
     *
     * @param key the key
     * @param millisecondsTimeout 失效时间，即数据每次被缓存后millisecondsTimeout毫秒失效
     * @param cache the cache
     */
    public static void pushCache(String key,long millisecondsTimeout,ICache cache){ 
    	//获取最新数据并缓存
    	cacheData.put(key,cache.getNewestData());
    	//设置过期时间
    	cacheData.put(TIMEOUT_PREFIX+key, millisecondsTimeout);
    	//更新时间
    	cacheData.put(LAST_UPDATE_PREFIX+key, System.currentTimeMillis());
    	//缓存ICache对象
    	cacheData.put(ICACHE_PREFIX+key, cache);
//    	logger.debug("pushCacheSuccess:"+cacheData);
    }
    
    /**
     * *
     * 私有方法，暗含已经根据pushCache(String key,long millisecondsTimeout,ICache cache)
     * 放入数据，进行重新刷新缓存.
     *
     * @param key the key
     */
    private static void pushCache(String key){      	
    	synchronized(SYNC_LOCKER){ 
    		ICache cacheObj = (ICache)cacheData.get(ICACHE_PREFIX+key);
    		cacheData.put(key, cacheObj.getNewestData());
    		//更新最后更改日期
    		cacheData.put(LAST_UPDATE_PREFIX+key,System.currentTimeMillis());
//    		logger.debug("refressCacheSuccess:"+cacheData);
    	}
    	
    }
    
    /**
     * 判断是否过期.
     *
     * @param key the key
     * @return true, if checks if is timeout
     */
    private static boolean isTimeout(String key){
    	long timeoutmills = (long)cacheData.get(TIMEOUT_PREFIX+key);
    	long lastupdatemills = (long)cacheData.get(LAST_UPDATE_PREFIX+key);
    	return  System.currentTimeMillis() - lastupdatemills > timeoutmills;
    }
    
    /**
     * *
     * 获取缓存.
     *
     * @param <T> the generic type
     * @param key the key
     * @return the cache
     */
	public static <T> T getCache(String key){
		//有数据对象
		if(cacheData.containsKey(ICACHE_PREFIX+key)){
			if( cacheData.get(key)==null  ||  LocalCache.isTimeout(key)){
				logger.debug("refreshing data"+key);
				LocalCache.pushCache(key);//刷新数据
			}
		}
		else{
			throw new TsfaRuntimeException("tsfa-mvc-localcache-nonekeys","没有定义过"+key+"的缓存数据");
		} 
		return (T)cacheData.get(key);
	}

	/**
	 * 方法说明：手动刷新数据.
	 *
	 * @param key the key
	 * @author 彭阳
	 * 
	 * CreateDate: 2017-7-1
	 */
	public static void refresh(String key) {
		LocalCache.pushCache(key);	//刷新数据
	}
}
