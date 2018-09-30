package com.asiabill.framework.core.context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiabill.framework.base.TerminalEnvironment;
import com.asiabill.framework.core.util.JacksonUtil;

/**
 * 
 * <p>Title: ServiceContext</p>  
 * <p>Description: TODO</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午9:58:53
 * @version v1.0
 */
public class ServiceContext {

	private static Logger logger = LoggerFactory.getLogger(ServiceContext.class);

	private static final ThreadLocal<ServiceContext> contexts = new ThreadLocal<ServiceContext>() {
		protected ServiceContext initialValue() {
			return new ServiceContext();
		}
	};

	private static final String headPrefix = "k.";
	private Map<String, String> headers = new HashMap<>();

	public static ServiceContext getContext() {
		return contexts.get();
	}

	public static ServiceContext getContext(boolean initFlowNo) {
		ServiceContext c = contexts.get();
		if (!initFlowNo) {
			return c;
		}
		c.setRequestFlowNo(getRandomFlowNo());
		return c;
	}

	public static ServiceContext getContext(String prefix) {
		ServiceContext c = contexts.get();
		if (prefix == null) {
			return c;
		}
		c.setRequestFlowNo(prefix + getRandomFlowNo());
		return c;
	}

	public static String getRandomFlowNo() {
		int index = (int) (System.currentTimeMillis() % 13L);
		return UUID.randomUUID().toString().replaceAll("-", "").substring(index, index + 16);
	}

	public void initBy(ServiceContext parentContext) {
		if ((parentContext == null) || (parentContext == this)) {
			return;
		}
		this.headers.clear();
		this.headers.putAll(parentContext.headers);
	}

	public static void removeContext() {
		contexts.remove();
	}

	public String getHeader(String key) {
		return this.headers.get(key);
	}

	public Map<String, String> getHeaders() {
		return this.headers;
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public Map<String, String> getCloneHeaders() {
		Map<String, String> m = new HashMap<>(this.headers.size());
		for (Map.Entry<String, String> e : this.headers.entrySet()) {
			m.put(headPrefix + (String) e.getKey(), e.getValue());
		}
		return m;
	}

	public void removeHeader(String key) {
		this.headers.remove(key);
	}

	public void addHeaders(Map<String, String> h) {
		for (Map.Entry<String, String> e : h.entrySet()) {
			String key = (String) e.getKey();
			if ((key != null) && (key.startsWith(headPrefix))) {
				addHeader(key.substring(headPrefix.length()), (String) e.getValue());
			}
		}
	}

	public String getRequestFlowNo() {
		return getHeader(ContextHeaderKey.REQUEST_FLOWNO);
	}

	public String getConsumerIp() {
		return getHeader(ContextHeaderKey.CONSUMER_IP);
	}

	public String getTokenId() {
		return getHeader(ContextHeaderKey.TOKEN_ID);
	}

	public TerminalEnvironment getTerminalEnvironment() {
		String envJson = getHeader(ContextHeaderKey.TERMINAL_ENVIRONMENT);
		if (StringUtils.isBlank(envJson)) {
			return null;
		}
		try {
			return (TerminalEnvironment) JacksonUtil.jsonToBean(getHeader(ContextHeaderKey.TERMINAL_ENVIRONMENT),
					TerminalEnvironment.class);
		} catch (Exception e) {
			logger.error("transfer to TerminalEnvironment error,json=" + envJson, e);
		}
		return null;
	}
	
	public void setRequestFlowNo(String str)
	  {
	    this.headers.put(ContextHeaderKey.REQUEST_FLOWNO, str);
	  }
	  
	  public void setConsumerIp(String str)
	  {
	    this.headers.put(ContextHeaderKey.CONSUMER_IP, str);
	  }
	  
	  public void setTerminalEnvironment(TerminalEnvironment env)
	  {
	    try
	    {
	      this.headers.put(ContextHeaderKey.TERMINAL_ENVIRONMENT, JacksonUtil.beanToJson(env));
	    }
	    catch (Exception e)
	    {
	      logger.error("transfer from TerminalEnvironment error! TerminalEnvironment = " + env, e);
	      this.headers.put(ContextHeaderKey.TERMINAL_ENVIRONMENT, null);
	    }
	  }
	  
	  public void setTokenId(String str)
	  {
	    this.headers.put(ContextHeaderKey.TOKEN_ID, str);
	  }
}
