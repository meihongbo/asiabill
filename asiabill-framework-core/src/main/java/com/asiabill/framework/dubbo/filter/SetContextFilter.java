package com.asiabill.framework.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiabill.framework.core.context.ContextHeaderKey;
import com.asiabill.framework.core.context.ServiceContext;

/**
 * 
 * <p>Title: SetContextFilter</p>  
 * <p>Description: 消费者服务跟踪拦截器</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午10:33:58
 * @version v1.0
 */
@Activate(group={"consumer"}, order=-5000)
public class SetContextFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(SetContextFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		ServiceContext context = ServiceContext.getContext();

		boolean genFlowFlag = false;
		long startTime = System.currentTimeMillis();
		long endTime;
		String flowNo = null;
		try {
			flowNo = context.getRequestFlowNo();
			if ((flowNo == null) || (flowNo.isEmpty())) {
				flowNo = ServiceContext.getRandomFlowNo();
				logger.debug("flowNo=" + flowNo);
				context.addHeader(ContextHeaderKey.REQUEST_FLOWNO, flowNo);
				genFlowFlag = true;
			}
			String localIp = RpcContext.getContext().getLocalHost();
			String remoteIp = RpcContext.getContext().getRemoteHost();
			int port = RpcContext.getContext().getRemotePort();
			logger.info("[RQ-BEIGN][服务:{}.{}][流水号:{}][本地IP:{}][远端IP:{}:{}].",
					new Object[] { invoker.getInterface().getName(), invocation.getMethodName(), flowNo, localIp,
							remoteIp, Integer.valueOf(port) });

			context.addHeader(ContextHeaderKey.CONSUMER_IP, localIp);

			invocation.getAttachments().putAll(context.getCloneHeaders());
			
			return invoker.invoke(invocation);
		} finally {
			endTime = System.currentTimeMillis();
			logger.info("[RQ-END][服务:{}.{}][流水号:{}][耗时: time={} ms].", new Object[] { invoker.getInterface().getName(),
					invocation.getMethodName(), flowNo, Long.valueOf(endTime - startTime) });
			if (genFlowFlag) {
				context.removeHeader(ContextHeaderKey.REQUEST_FLOWNO);
			}
		}
	}

}
