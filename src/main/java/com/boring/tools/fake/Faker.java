package com.boring.tools.fake;

import java.util.List;
import java.util.Map;

public class Faker {

	public static FackInjector when(Object methodCall) {
		return new FackInjector(methodCall);
	}

	public static <T> T fake(final Class<T> t) {
		return new FakeCglibInterceptor<T>().getInstance(t);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		final List<String> myMockList1 = fake(List.class);
		final List<String> myMockList2 = fake(List.class);
		final Map<Integer, String> myMockMap = fake(Map.class);

		Faker.when(myMockList1.get(0)).thenReturn("Hello, I am James");
		Faker.when(myMockList1.get(2)).thenReturn("Hello, I am Billy");
		Faker.when(myMockList2.get(0)).thenReturn("Hello, I am Tom");
		Faker.when(myMockMap.get(10)).thenReturn("Hello, I am Bob");

		System.out.println("myMockList1.get(0) = " + myMockList1.get(0));
		System.out.println("myMockList1.get(2) = " + myMockList1.get(2));
		System.out.println("myMockList2.get(0) = " + myMockList2.get(0));
		System.out.println("myMockMap.get(10) = " + myMockMap.get(10));
		System.out.println("myMockMap.get(9) = " + myMockMap.get(9));
	}
}
