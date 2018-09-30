package com.asiabill.ccmm.dto;

import java.util.Date;

import com.asiabill.framework.base.BaseEntity;

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
public class TraderecordDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 流水订单号
	 */
	private String tradeNo;
	
	/*
	 * 商户订单号
	 */
	private String merOrderNo;
	
	/*
	 * 交易币种
	 */
	private String orderCurrency;
	
	/*
	 * 交易金额
	 */
	private String orderAmount;
	
	/*
	 * 收单币种
	 */
	private String bankCurrency;
	
	/*
	 * 收单金额
	 */
	private String bankAmount;
	
	/*
	 * 交易状态
	 */
	private Integer tradeStatus;
	
	/*
	 * 交易时间
	 */
	private Date tradeTimes;
	
	/*
	 * 支付通道
	 */
	private String bankChannal;

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

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBankCurrency() {
		return bankCurrency;
	}

	public void setBankCurrency(String bankCurrency) {
		this.bankCurrency = bankCurrency;
	}

	public String getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(String bankAmount) {
		this.bankAmount = bankAmount;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getTradeTimes() {
		return tradeTimes;
	}

	public void setTradeTimes(Date tradeTimes) {
		this.tradeTimes = tradeTimes;
	}

	public String getBankChannal() {
		return bankChannal;
	}

	public void setBankChannal(String bankChannal) {
		this.bankChannal = bankChannal;
	}
	
}
