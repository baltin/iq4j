package com.iq4j.utils.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.iq4j.utils.service.ElapsedTimeStatistics;

@ElapsedTime @Interceptor 
public class ElapsedTimeInterceptor {

	@Inject ElapsedTimeStatistics elapsedTimeStatistics;
	
	@AroundInvoke
	public Object measure(InvocationContext ctx) throws Exception {
		
		Long start = System.nanoTime();
		Object result =  ctx.proceed();
		Long elapsed = System.nanoTime() - start;
		elapsedTimeStatistics.put(generateMethodKey(ctx), elapsed);
		return result;
		
	}
	
	private String generateMethodKey(InvocationContext ctx) {
		return ctx.getMethod().getDeclaringClass().getName() + "." + ctx.getMethod().getName(); 
	}
	
}
