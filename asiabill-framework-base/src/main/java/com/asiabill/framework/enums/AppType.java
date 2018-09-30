package com.asiabill.framework.enums;

public enum AppType {

	IOS("ios", "ios"), ANDROID("android", "android");

	private String msg;
	private String code;

	private AppType(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
