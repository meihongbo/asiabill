package com.asiabill.framework.base;

import java.util.List;


public class Result<T> implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String status;

	private Long totalNum;

	// 数据列表
	private List<T> dataList;
	
	private int pageSize;
	
	private int pageNo;

	/**
	 * @return the totalNum
	 */
	public Long getTotalNum() {
		return totalNum;
	}

	/**
	 * @param totalNum
	 *            the totalNum to set
	 */
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	
	/**
	 * @return the dataList
	 */
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
