package com.boring.tools.common;

import java.util.Arrays;
import java.util.Objects;

public final class BoringObjects {

	private BoringObjects() {
	}

	public static Object firstNonNull(Object... objects) {
		return Arrays.stream(objects).filter(Objects::nonNull).findFirst().get();
	}

}
