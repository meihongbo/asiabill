package com.lj.base.core.pagination;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.Serializable;


/**
 * 
 * 
 * 类说明：分页查询参数模型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年7月1日
 */
public class PageParamEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5413436619988169890L;
	
	/** The Constant START. */
	private static final int START = 0;
	
	/** The Constant LIMIT. */
	private static final int LIMIT = 10;
	
	/** 起始行. */
	private int start = START;
	
	/** 每页条数. */
	private int limit = LIMIT;
	
	/** 排序字段. */
	private String sortBy;
	
	/** 排序类型：顺序asc、倒序desc. */
	private PageSortType sortDir = PageSortType.desc;
	
	/**
	 * Instantiates a new page param entity.
	 */
	public PageParamEntity() {
		super();
	}
	
	/**
	 * Instantiates a new page param entity.
	 *
	 * @param start the start row
	 * @param limit the page size
	 */
	public PageParamEntity(int start, int limit) {
		super();
		this.start = start <= 0 ? START : start;
		this.limit = limit <= 0 ? LIMIT : limit;
	}
	
	/**
	 * Gets the 起始行.
	 *
	 * @return the 起始行
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
		this.start = start <= 0 ? START : start;
	}

	/**
	 * Gets the 每页条数.
	 *
	 * @return the 每页条数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets the 每页条数.
	 *
	 * @param limit the new 每页条数
	 */
	public void setLimit(int limit) {
		this.limit = limit <= 0 ? LIMIT : limit;
	}

	/**
	 * Gets the 排序字段.
	 *
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * Sets the 排序字段.
	 *
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * Gets the 排序类型：顺序asc、倒序desc.
	 *
	 * @return the sortDir
	 */
	public PageSortType getSortDir() {
		return sortDir;
	}

	/**
	 * Sets the 排序类型：顺序asc、倒序desc.
	 *
	 * @param sortDir the sortDir to set
	 */
	public void setSortDir(PageSortType sortDir) {
		this.sortDir = sortDir;
	}

}
