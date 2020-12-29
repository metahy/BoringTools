package com.boring.tools.fake;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class FakeCglibInterceptor<T> implements MethodInterceptor {

	private Map<MethodInfo, Object> MOCKED_METHODS = new HashMap<MethodInfo, Object>();

	private static final ThreadLocal<MethodInfo> STUB_METHOD_INFO = new ThreadLocal<>();

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		MethodInfo info = new MethodInfo(this, method, args);
		STUB_METHOD_INFO.set(info);
		return MOCKED_METHODS.get(info);
	}

	@SuppressWarnings("unchecked")
	public T getInstance(final Class<T> t) {
		return (T) Enhancer.create(t, this);
	}

	public static void put(Object value) {
		STUB_METHOD_INFO.get().put(value);
	}

	protected void put(MethodInfo methodInfo, Object value) {
		MOCKED_METHODS.put(methodInfo, value);
	}

}
