package com.asiabill.framework.base;

public class BaseDto extends BaseEntity
	implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	// 商户ID
	private Long merOprId;
	
	// 当前操作的菜单模块ID
	private Integer sysOprMid;
	
	// 当前操作人客户端IP
	private String sysOprIp;
	
	// 前台分页对象
    private DataTablePageReq page;

	//应用ID
	private String appId;
	
	//签名串
	private String sign;
	
	//签名时间戳
	private String timestamp;

	public Long getMerOprId() {
		return merOprId;
	}

	public void setMerOprId(Long merOprId) {
		this.merOprId = merOprId;
	}

	public DataTablePageReq getPage() {
		return page;
	}

	public void setPage(DataTablePageReq page) {
		this.page = page;
	}

	public Integer getSysOprMid() {
		return sysOprMid;
	}

	public void setSysOprMid(Integer sysOprMid) {
		this.sysOprMid = sysOprMid;
	}
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSysOprIp() {
		return sysOprIp;
	}

	public void setSysOprIp(String sysOprIp) {
		this.sysOprIp = sysOprIp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
