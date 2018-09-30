package com.asiabill.framework.dubbo.filter;

import java.lang.reflect.Method;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcResult;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiabill.framework.core.exception.ServiceException;

/**
 * 
 * <p>Title: ExceptionFilter</p>  
 * <p>Description: 错误拦截器</p>  
 * <p>Copyright: Copyright (c) 2018</p>  
 * <p>Company: www.asiabill.com</p>  
 * @author mhb
 * @date 2018年9月30日 上午10:54:53
 * @version v1.0
 */
@Activate(group={"provider"})
public class ExceptionFilter implements Filter {

	private final Logger logger;

	public ExceptionFilter() {
		this(LoggerFactory.getLogger(ExceptionFilter.class));
	}

	public ExceptionFilter(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			Result result = invoker.invoke(invocation);
			if ((result.hasException()) && (GenericService.class != invoker.getInterface())) {
				try {
					Throwable exception = result.getException();
					if ((!(exception instanceof RuntimeException)) && ((exception instanceof Exception))) {
						return result;
					}
					try {
						Method method = invoker.getInterface().getMethod(invocation.getMethodName(),
								invocation.getParameterTypes());
						Class<?>[] exceptionClassses = method.getExceptionTypes();
						for (Class<?> exceptionClass : exceptionClassses) {
							if (exception.getClass().equals(exceptionClass)) {
								return result;
							}
						}
					} catch (NoSuchMethodException e) {
						return result;
					}
					String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
					String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
					if ((serviceFile == null) || (exceptionFile == null) || (serviceFile.equals(exceptionFile))) {
						return result;
					}
					String className = exception.getClass().getName();
					if ((className.startsWith("java.")) || (className.startsWith("javax."))) {
						return result;
					}
					if ((exception instanceof RpcException)) {
						return result;
					}
					if ((exception instanceof ServiceException)) {
						return result;
					}
					return new RpcResult(new RuntimeException(StringUtils.toString(exception)));
				} catch (Throwable e) {
					this.logger.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost()
							+ ". service: " + invoker.getInterface().getName() + ", method: "
							+ invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": "
							+ e.getMessage(), e);

					return result;
				}
			}
			return result;
		} catch (RuntimeException e) {
			this.logger.error("Got unchecked and undeclared exception which called by "
					+ RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName()
					+ ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": "
					+ e.getMessage(), e);

			throw e;
		}
	}

}
