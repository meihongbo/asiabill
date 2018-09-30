package com.lj.base.mvc.pagination;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.ArrayList;
import java.util.Collection;

import com.lj.base.core.BaseEntity;

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
public class PaginationVO<T> extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** * 总条数. */
	private long total;
	
	/** * 当前页码. */
	private int currentPageNo;
	
	/** * 每页条数. */
	private int pageSize;
	
	/** * 总页数总页数. */
	private long totalPage;
	
	/** * 数据对象. */
	private Collection<T> data;
	
	/**
	 * The Constructor.
	 */
	public PaginationVO() {
		super();
	}

	/**
	 * The Constructor.
	 *
	 * @param data the data
	 * @param total the total
	 * @param currentPageNo the current page no
	 * @param pageMaxCount the page max count
	 */
	public PaginationVO(Collection<T> data, long total, int currentPageNo, int pageMaxCount) {
		super();
		this.currentPageNo = currentPageNo;
		this.pageSize = pageMaxCount;
		this.total = total;
		this.totalPage = (total - 1) / pageMaxCount + 1;
		this.data = data == null ? new ArrayList<T>(0) : data;
	}

	/**
	 * Gets the * 总条数.
	 *
	 * @return the * 总条数
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

	/**
	 * Gets the * 当前页码.
	 *
	 * @return the * 当前页码
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * Sets the * 当前页码.
	 *
	 * @param currentPageNo the new * 当前页码
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * Gets the * 每页条数.
	 *
	 * @return the * 每页条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the * 每页条数.
	 *
	 * @param pageSize the new * 每页条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Gets the * 总页数总页数.
	 *
	 * @return the * 总页数总页数
	 */
	public long getTotalPage() {
		return totalPage;
	}

	/**
	 * Sets the * 总页数总页数.
	 *
	 * @param totalPage the new * 总页数总页数
	 */
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * Gets the * 数据对象.
	 *
	 * @return the * 数据对象
	 */
	public Collection<T> getData() {
		return data;
	}

	/**
	 * Sets the * 数据对象.
	 *
	 * @param data the new * 数据对象
	 */
	public void setData(Collection<T> data) {
		this.data = data;
	}
	 
}
