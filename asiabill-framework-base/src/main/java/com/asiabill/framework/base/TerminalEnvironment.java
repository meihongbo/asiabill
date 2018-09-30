package com.asiabill.framework.base;

import com.asiabill.framework.enums.AppType;
import com.asiabill.framework.enums.TerminalType;

public class TerminalEnvironment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TerminalType terminalType;
	private String productType;
	private String productName;
	private String appName;
	private AppType appType;
	private String appVersion;
	private String channelCode;
	private String appChannel;
	private String subAppChannel;
	private String userAgentCust;
	private String referCust;
	private String deviceUniqueId;
	private String blackBox;
	private String intranelIp;
	private String smartId;
	private String ip;
	private String gpsAddress;
	private Double longiTude;
	private Double latiTude;
	private String mac;
	private String imei;
	private String phoneOperator;
	private String phoneMarker;
	private String phoneModel;
	private String computerHost;
	private String operationSys;
	private String operationSysVersion;
	private String resolution;
	private String font;
	private String fontSize;
	private String browserType;
	private String browserVersion;
	private String isEmulator;
	
	public TerminalType getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public AppType getAppType() {
		return appType;
	}
	public void setAppType(AppType appType) {
		this.appType = appType;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getAppChannel() {
		return appChannel;
	}
	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}
	public String getSubAppChannel() {
		return subAppChannel;
	}
	public void setSubAppChannel(String subAppChannel) {
		this.subAppChannel = subAppChannel;
	}
	public String getUserAgentCust() {
		return userAgentCust;
	}
	public void setUserAgentCust(String userAgentCust) {
		this.userAgentCust = userAgentCust;
	}
	public String getReferCust() {
		return referCust;
	}
	public void setReferCust(String referCust) {
		this.referCust = referCust;
	}
	public String getDeviceUniqueId() {
		return deviceUniqueId;
	}
	public void setDeviceUniqueId(String deviceUniqueId) {
		this.deviceUniqueId = deviceUniqueId;
	}
	public String getBlackBox() {
		return blackBox;
	}
	public void setBlackBox(String blackBox) {
		this.blackBox = blackBox;
	}
	public String getIntranelIp() {
		return intranelIp;
	}
	public void setIntranelIp(String intranelIp) {
		this.intranelIp = intranelIp;
	}
	public String getSmartId() {
		return smartId;
	}
	public void setSmartId(String smartId) {
		this.smartId = smartId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getGpsAddress() {
		return gpsAddress;
	}
	public void setGpsAddress(String gpsAddress) {
		this.gpsAddress = gpsAddress;
	}
	public Double getLongiTude() {
		return longiTude;
	}
	public void setLongiTude(Double longiTude) {
		this.longiTude = longiTude;
	}
	public Double getLatiTude() {
		return latiTude;
	}
	public void setLatiTude(Double latiTude) {
		this.latiTude = latiTude;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPhoneOperator() {
		return phoneOperator;
	}
	public void setPhoneOperator(String phoneOperator) {
		this.phoneOperator = phoneOperator;
	}
	public String getPhoneMarker() {
		return phoneMarker;
	}
	public void setPhoneMarker(String phoneMarker) {
		this.phoneMarker = phoneMarker;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	public String getComputerHost() {
		return computerHost;
	}
	public void setComputerHost(String computerHost) {
		this.computerHost = computerHost;
	}
	public String getOperationSys() {
		return operationSys;
	}
	public void setOperationSys(String operationSys) {
		this.operationSys = operationSys;
	}
	public String getOperationSysVersion() {
		return operationSysVersion;
	}
	public void setOperationSysVersion(String operationSysVersion) {
		this.operationSysVersion = operationSysVersion;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getIsEmulator() {
		return isEmulator;
	}
	public void setIsEmulator(String isEmulator) {
		this.isEmulator = isEmulator;
	}

}
