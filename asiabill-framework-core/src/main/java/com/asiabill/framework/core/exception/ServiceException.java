package com.asiabill.framework.core.exception;

import java.text.MessageFormat;

import com.asiabill.framework.base.ErrorCode;
import com.asiabill.framework.core.config.Property;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3884793453152581556L;

	private String code;
	private String msg;
	private String extMsg;

	public ServiceException(ErrorCode errorCode, String[] param, String... extMsg) {
		super(null == errorCode ? "" : errorCode.getCode());
		init(errorCode, param, extMsg);
	}

	private void init(ErrorCode errorCode, Object[] param, String... extMsg) {
		this.code = (null == errorCode ? "" : errorCode.getCode());
		String msgPatten = Property.getProperty(errorCode.getCode());
		msgPatten = msgPatten == null ? "" : msgPatten;
		this.msg = (null == errorCode ? "" : MessageFormat.format(msgPatten, param));
		StringBuilder builder = new StringBuilder(100);
		builder.append(this.msg);
		if (null != extMsg) {
			for (String ext : extMsg) {
				builder.append("[").append(ext).append("]");
			}
		}
		this.extMsg = builder.toString();
	}

	public ServiceException(String code, String msg) {
		super(code + ":" + msg);
		this.code = code;
		this.msg = msg;
	}

	public ServiceException(ErrorCode errorCode, Throwable e, String[] param, String... extMsg) {
		super(null == errorCode ? "" : errorCode.getCode(), e);
		init(errorCode, param, extMsg);
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public String getExtMsg() {
		return this.extMsg;
	}

	public String getMessage() {
		return super.getMessage() + "," + this.extMsg;
	}

}
