package com.boring.tools.fake;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodInfo {
	private final FakeCglibInterceptor<?> interceptor;
	private final Method method;
	private final Object[] args;

	public MethodInfo(FakeCglibInterceptor<?> interceptor, Method method, Object[] args) {
		this.interceptor = interceptor;
		this.method = method;
		this.args = args;
	}

	@Override
	public String toString() {
		return "{interceptor: " + interceptor + ", Method: " + method + ", args: " + Arrays.toString(args) + "}";
	}

	@Override
	public boolean equals(final Object other) {
		return this.hashCode() == other.hashCode();
	}

	@Override
	public int hashCode() {
		return interceptor.hashCode() + method.hashCode() + Arrays.hashCode(args);
	}

	public void put(Object value) {
		interceptor.put(this, value);
	}
}