package com.asiabill.framework.core.context;

import org.slf4j.MDC;

/**
 * 
 * <p>Title: ContextSlf4jUtil</p>  
 * <p>Description: TODO</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午9:58:47
 * @version v1.0
 */
public class ContextSlf4jUtil {
	
	public static void addLogKey2MDC(ServiceContext context)
	  {
	    if (context == null) {
	      return;
	    }
	    MDC.put(ContextHeaderKey.REQUEST_FLOWNO, context.getRequestFlowNo());
	    MDC.put(ContextHeaderKey.CONSUMER_IP, context.getConsumerIp());
	  }
	  
	  public static void rmvLogKeyFromMDC()
	  {
	    MDC.remove(ContextHeaderKey.REQUEST_FLOWNO);
	    MDC.remove(ContextHeaderKey.CONSUMER_IP);
	  }
}
