package com.lj.base.mvc.web.context;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 
 * 类说明：
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
public class RequestContextListener implements ServletRequestListener {

	/** The Constant REQUEST_ATTRIBUTES_ATTRIBUTE. */
	private static final String REQUEST_ATTRIBUTES_ATTRIBUTE = RequestContextListener.class
			.getName() + ".REQUEST_ATTRIBUTES";

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent requestEvent) {
		if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
			throw new IllegalArgumentException(
					"Request is not an HttpServletRequest: "
							+ requestEvent.getServletRequest());
		}
		HttpServletRequest request = (HttpServletRequest) requestEvent
				.getServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(
				request);
		request.setAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE, attributes);
		LocaleContextHolder.setLocale(request.getLocale());
		RequestContextHolder.setRequestAttributes(attributes);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) requestEvent
				.getServletRequest().getAttribute(REQUEST_ATTRIBUTES_ATTRIBUTE);
		ServletRequestAttributes threadAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (threadAttributes != null) {
			// We're assumably within the original request thread...
			if (attributes == null) {
				attributes = threadAttributes;
			}
			LocaleContextHolder.resetLocaleContext();
			RequestContextHolder.resetRequestAttributes();
		}
		if (attributes != null) {
			attributes.requestCompleted();
		}
	}
}
