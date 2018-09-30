package com.lj.base.core.hardware;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.Hashtable;
import java.util.Map;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;

/**
 * 
 * 
 * 类说明：
 * 
 * 单系统的内部控制锁，保证本系统多个线程的并发
 * 只有一个能拿到！
 * [特殊场景使用]
 * 比如统计系统mongodb的update保存
 * 因为从系统保证了分布式多机器，update x=t+1 where x=t不会有并发问题
 * 为了提供update 失败被别人update问题，作了本单机锁.
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class SingleSystemLock {
	
	/** * 锁列表. */
	private static Map<String,String> lock = new Hashtable<String,String>();

	/**
	 * *
	 * 严格控制，.
	 *
	 * @param lockName the lock name
	 * @return the strict lock
	 */
	public static String getStrictLock(String lockName){
		if(  lock.containsKey(lockName) ){
			return null;//拿不到锁
		} else{			 
			String guid = GUID.generateByUUID();
			String oldStr = lock.put(lockName, guid);
			if(StringUtils.isEmpty(oldStr)){ //原来为空
					return guid; 
			}
			else{
				//已经有别人的锁了，还回去
				lock.put(lockName, oldStr);
				return StringUtils.EMPTY;//拿不到锁
			}
		}
		
	}
	
	/**
	 * *
	 * 释放锁，有lockvalue的才能释放.
	 *
	 * @param lockName the lock name
	 * @param lockValue the lock value
	 * @return true, if release lock
	 */
	public static boolean releaseLock(String lockName,String lockValue){
		String lockValueSaved = lock.get(lockName);
		if(lockValue.equals(lockValueSaved)){
			lock.remove(lockName);
			return true;
		}
		return false;
	}
}
