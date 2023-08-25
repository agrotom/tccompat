package net.agrotom.tccompat.util;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

public class FastList {

	@SafeVarargs
	public static <T> List<T> of(@Nonnull T... elements) {
		return Arrays.asList(elements);
	}

}
