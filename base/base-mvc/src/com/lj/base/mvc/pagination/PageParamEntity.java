package com.lj.base.mvc.pagination;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.BaseEntity;


/**
 * 
 * 
 * 类说明：分页查询参数基础模型
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
public class PageParamEntity extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5413436619988169890L;
	
	/** The Constant PAGE_INDEX. */
	private static final int PAGE_INDEX = 1;
	
	/** The Constant PAGE_SIZE. */
	private static final int PAGE_SIZE = 10;

	/** * 当前页码. */
	private int currentPageNo = PAGE_INDEX;
	
	/** 每页条数. */
	private int pageSize = PAGE_SIZE;
	
	/**
	 * The Constructor.
	 */
	public PageParamEntity() {
		super();
	}
	
	/**
	 * The Constructor.
	 *
	 * @param currentPageNo the current page no
	 * @param pageSize the page size
	 */
	public PageParamEntity(int currentPageNo, int pageSize) {
		super();
		this.currentPageNo = currentPageNo <= 0 ? PAGE_INDEX : currentPageNo;
		this.pageSize = pageSize <= 0 ? PAGE_SIZE : pageSize;
	}
	
	/**
	 * 方法说明：得到查询起始行.
	 *
	 * @return the offset index
	 */
	public int getOffsetIndex() {
		return (this.currentPageNo - 1) * this.pageSize;
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
		this.currentPageNo = currentPageNo <= 0 ? PAGE_INDEX : currentPageNo;
	}

	/**
	 * Gets the 每页条数.
	 *
	 * @return the 每页条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the 每页条数.
	 *
	 * @param pageSize the new 每页条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? PAGE_SIZE : pageSize;
	}
	
}
