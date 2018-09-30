package com.lj.base.mvc.dao;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

/**
 * 
 * 
 * 类说明：数据接口
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
public interface IDao {
	
	/**
	 * 插入数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 * @returns
	 */
	public int insert(String sqlId,Object params);
	
	/**
	 * 更新数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 */
	public int update(String sqlId,Object params);
	
	/**
	 * *
	 * 删除数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 */
	public int delete(String sqlId,Object params);
	
	/**
	 * *
	 * 查询返回结果集.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the list
	 */
	public  List selectList(String sqlId,Object params);
	
	/**
	 * *
	 * 查询单条记录.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the object
	 */
	public  Object selectOne(String sqlId,Object params);
	
	/**
	 * *
	 * 查询返回Map对象.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @param keyColumn the key column
	 * @return the map
	 */
	public  Map selectMap(String sqlId,Object params,String keyColumn);

}
