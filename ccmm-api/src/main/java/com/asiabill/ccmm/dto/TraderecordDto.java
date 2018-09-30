package com.asiabill.ccmm.dto;

import java.util.Date;

import com.asiabill.framework.base.BaseDto;

/**
 * 
 * <p>Title: TraderecordDto</p>  
 * <p>Description: 交易记录</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 下午5:49:24
 * @version v1.0
 */
public class TraderecordDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 商户号
	 */
	private String merNo;
	
	/*
	 * 网关号
	 */
	private String gwNo;
	
	/*
	 * 流水订单号
	 */
	private String tradeNo;
	
	/*
	 * 商户订单号
	 */
	private String merOrderNo;
	
	/*
	 * 邮箱
	 */
	private String email;
	
	/*
	 * 交易状态
	 */
	private Integer tradeStatus;
	
	/*
	 * 交易开始时间
	 */
	private Date tradeTimeStart;
	
	/*
	 * 交易结束时间
	 */
	private Date tradeTimeEnd;

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getGwNo() {
		return gwNo;
	}

	public void setGwNo(String gwNo) {
		this.gwNo = gwNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMerOrderNo() {
		return merOrderNo;
	}

	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getTradeTimeStart() {
		return tradeTimeStart;
	}

	public void setTradeTimeStart(Date tradeTimeStart) {
		this.tradeTimeStart = tradeTimeStart;
	}

	public Date getTradeTimeEnd() {
		return tradeTimeEnd;
	}

	public void setTradeTimeEnd(Date tradeTimeEnd) {
		this.tradeTimeEnd = tradeTimeEnd;
	}
	
}
