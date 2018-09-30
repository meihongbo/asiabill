

package com.lj.base.cache;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：简单的本地缓存，业务实现接口对象
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
public interface ICache {
	
	/**
	 * *
	 * 缓存获取最新数据.
	 *
	 * @param <T> the generic type
	 * @return the newest data
	 */
	public <T> T getNewestData();

}
