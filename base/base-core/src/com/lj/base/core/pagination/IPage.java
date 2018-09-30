package com.lj.base.core.pagination;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * 
 * 类说明：分页返回
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
public interface IPage<T> extends Serializable {

	/**
	 * 方法说明：起始行.
	 *
	 * @return the start
	 */
	public int getStart();

	/**
	 * 方法说明：每页条数.
	 *
	 * @return the limit
	 */
	public int getLimit();
	
	/**
	 * 方法说明：总记录数.
	 *
	 * @return the total
	 */
	public long getTotal();
	
	/**
	 * 方法说明：数据集合.
	 *
	 * @return the rows
	 */
	public Collection<T> getRows();
	
	/**
	 * 方法说明：其他数据，如统计内容.
	 *
	 * @return the other data
	 * @author 彭阳
	 * CreateDate: 2017-6-26
	 */
	public Object getOtherData();
}
