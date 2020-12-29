package com.boring.tools.fake;

public class FackInjector {

	public FackInjector(Object methodInfo) {
	}

	public void thenReturn(final Object mockResult) {
		FakeCglibInterceptor.put(mockResult);
	}

}
