package com.lj.base.core.pagination;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 
 * 类说明：分页查询返回模型
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
public class Page<T> implements IPage<T> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6570192533192942163L;

	/** 起始行. */
	private int start;
	
	/** * 每页条数. */
	private int limit;
	
	/** * 总条数. */
	private long total;
	
	/** * 数据集合. */
	private Collection<T> rows;
	
	/** 其他数据，如统计内容. */
	private Object otherData;
	
	/**
	 * The Constructor.
	 */
	public Page() {
		super();
	}

	/**
	 * The Constructor.
	 *
	 * @param rows the rows
	 * @param total the total
	 * @param start the start
	 * @param limit the limit
	 */
	public Page(Collection<T> rows, long total, int start, int limit) {
		super();
		this.start = start;
		this.limit = limit;
		this.total = total;
		this.rows = rows == null ? new ArrayList<T>(0) : rows;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param rows the rows
	 * @param total the total
	 * @param start the start
	 * @param limit the limit
	 * @param otherData the other data
	 */
	public Page(Collection<T> rows, long total, int start, int limit, Object otherData) {
		super();
		this.start = start;
		this.limit = limit;
		this.total = total;
		this.rows = rows == null ? new ArrayList<T>(0) : rows;
		this.otherData = otherData;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param rows the rows
	 * @param total the total
	 * @param param the param
	 */
	public Page(Collection<T> rows, long total, PageParamEntity param) {
		super();
		if(param != null) {
			this.start = param.getStart();
			this.limit = param.getLimit();
		}
		this.total = total;
		this.rows = rows == null ? new ArrayList<T>(0) : rows;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param rows the rows
	 * @param total the total
	 * @param param the param
	 * @param otherData the other data
	 */
	public Page(Collection<T> rows, long total, PageParamEntity param, Object otherData) {
		super();
		if(param != null) {
			this.start = param.getStart();
			this.limit = param.getLimit();
		}
		this.total = total;
		this.rows = rows == null ? new ArrayList<T>(0) : rows;
		this.otherData = otherData;
	}

	/* (non-Javadoc)
	 * @see com.lj.base.core.pagination.IPage#getStart()
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Sets the 起始行.
	 *
	 * @param start the new 起始行
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/* (non-Javadoc)
	 * @see com.lj.base.core.pagination.IPage#getLimit()
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the * 每页条数.
	 *
	 * @param limit the new * 每页条数
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/* (non-Javadoc)
	 * @see com.lj.base.core.pagination.IPage#getTotal()
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Sets the * 总条数.
	 *
	 * @param total the new * 总条数
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see com.lj.base.core.pagination.IPage#getRows()
	 */
	public Collection<T> getRows() {
		return rows;
	}

	/**
	 * Sets the * 数据集合.
	 *
	 * @param rows the new * 数据集合
	 */
	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see com.lj.base.core.pagination.IPage#getOtherData()
	 */
	public Object getOtherData() {
		return otherData;
	}

	/**
	 * Sets the 其他数据，如统计内容.
	 *
	 * @param otherData the new 其他数据，如统计内容
	 */
	public void setOtherData(Object otherData) {
		this.otherData = otherData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [start=" + start + ", limit=" + limit + ", total=" + total
				+ ", rows=" + rows + ", otherData=" + otherData + "]";
	}

}
