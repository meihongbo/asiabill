package com.asiabill.filter;

import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;

public class ProviderThreadFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String rid = RpcContext.getContext().getAttachment("TRACE_ID");
		MDC.put("trace_id", rid);
		Result result = invoker.invoke(invocation);
		MDC.remove("trace_id");
		return result;
	}

}
