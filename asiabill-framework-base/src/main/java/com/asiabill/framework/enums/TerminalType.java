package com.asiabill.framework.enums;

public enum TerminalType {

	APP("app", "app"), WEB("web", "web"), H5("h5", "h5");

	private String code;
	private String desc;

	private TerminalType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static TerminalType judgeValue(String code) {
		TerminalType[] terminalTypes = values();
		for (TerminalType terminalType : terminalTypes) {
			if (terminalType.getCode().equals(code)) {
				return terminalType;
			}
		}
		return null;
	}
}
