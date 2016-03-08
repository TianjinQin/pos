package com.pos.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {

	private static final String DIAN = ".";
	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	
		// TODO Auto-generated method stub
		Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Object result = null;
		String cacheKey = getCacheKey(invocation);

		Element element = cache.get(cacheKey);
		if (null == element) {
			result = invocation.proceed();
			element = new Element(cacheKey, result);
			cache.put(element);
		}
		// TODO Auto-generated method stub
		return element.getObjectValue();
	}

	/**
	 * 组装cachekey
	 * 
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 * @return
	 */
	private String getCacheKey(MethodInvocation invocation) {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(DIAN).append(methodName);
		for (int i = 0; i < arguments.length; i++) {
			sb.append(DIAN).append(arguments[i]);
		}
		return sb.toString();
	}

}
