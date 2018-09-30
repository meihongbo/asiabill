package com.lj.base.mvc.dao;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 * 【不支持多数据源！】
 * 对mybatis的封装，可用于独立使用
 * classpath需要有mybatis-config.xml
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class BaseDao { 
	
	/** The sql session factory. */
	private SqlSessionFactory sqlSessionFactory ;
	
	/**
	 * Gets the sql session factory.
	 *
	 * @return the sql session factory
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	/**
	 * Sets the sql session factory.
	 *
	 * @param sqlSessionFactory the sql session factory
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * *
	 * 从classpath配置文件mybatis-config.xml组建SqlSessionFactory
	 *
	 * @return the sql session factory
	 */
	public static SqlSessionFactory initSqlSessionFactory(){
		SqlSessionFactory sqlSessionFactory  =null ;
		try {
 
			sqlSessionFactory = 
			new SqlSessionFactoryBuilder().build( 
					  Resources.getResourceAsStream("mybatis-config.xml")
					);
		} catch (IOException e) {
			//-----------
		}
		return sqlSessionFactory;
	} 
	
	/**
	 * 插入数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 * @returns
	 */
	public int insert(String sqlId,Object params){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return session.insert(sqlId, params);
		} finally {
		  session.close();
		}
	}
	
	/**
	 * 更新数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 */
	public int update(String sqlId,Object params){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return session.update(sqlId, params);
		} finally {
		  session.close();
		}
	}
	
	/**
	 * *
	 * 删除数据，返回受影响的函数.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the int
	 */
	public int delete(String sqlId,Object params){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return session.delete(sqlId, params);
		} finally {
		  session.close();
		}
	}
	
	/**
	 * *
	 * 查询返回结果集.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the list
	 */
	public  List selectList(String sqlId,Object params){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return  session.selectList(sqlId, params);
		} finally {
		  session.close();
		}
	}
	
	/**
	 * *
	 * 查询单条记录.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @return the object
	 */
	public  Object selectOne(String sqlId,Object params){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return  session.selectOne(sqlId, params);
		} finally {
		  session.close();
		}
	}
	
	/**
	 * *
	 * 查询返回Map对象.
	 *
	 * @param sqlId the sql id
	 * @param params the params
	 * @param keyColumn the key column
	 * @return the map
	 */
	public  Map selectMap(String sqlId,Object params,String keyColumn){
		SqlSession session = sqlSessionFactory.openSession();
		try {
		  return  session.selectMap(sqlId, params,keyColumn);
		} finally {
		  session.close();
		}
	}

}
