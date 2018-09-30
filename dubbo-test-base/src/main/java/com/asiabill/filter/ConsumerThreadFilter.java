package com.asiabill.filter;

import java.util.UUID;

import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

public class ConsumerThreadFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String rid = RpcContext.getContext().getAttachment("TRACE_ID");
		rid = StringUtils.isNotEmpty(rid) ? rid : UUID.randomUUID().toString().substring(0, 10);
		RpcContext.getContext().setAttachment("TRACE_ID", rid);
		System.out.println(rid + "=========================");
		return invoker.invoke(invocation);
	}

}
