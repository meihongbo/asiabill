package com.asiabill.framework.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiabill.framework.core.context.ContextHeaderKey;
import com.asiabill.framework.core.context.ContextSlf4jUtil;
import com.asiabill.framework.core.context.ServiceContext;

/**
 * 
 * <p>Title: ContextFilter</p>  
 * <p>Description: 生产者服务跟踪拦截器</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午10:53:42
 * @version v1.0
 */
@Activate(group={"provider"}, order=-5000)
public class ContextFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(ContextFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			ServiceContext context = ServiceContext.getContext();
			context.addHeaders(invocation.getAttachments());

			String flowNo = context.getRequestFlowNo();
			if ((flowNo == null) || (flowNo.isEmpty())) {
				flowNo = ServiceContext.getRandomFlowNo();
				this.logger.debug("flowNo=" + flowNo);
				context.addHeader(ContextHeaderKey.REQUEST_FLOWNO, flowNo);
			}
			ContextSlf4jUtil.addLogKey2MDC(context);

			return invoker.invoke(invocation);
		} finally {
			ServiceContext.removeContext();
			ContextSlf4jUtil.rmvLogKeyFromMDC();
		}
	}

}
