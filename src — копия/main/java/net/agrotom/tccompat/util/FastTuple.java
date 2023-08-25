package net.agrotom.tccompat.util;

import javax.annotation.Nonnull;

import net.minecraft.util.Tuple;

public class FastTuple {

	public static <T, U> Tuple<T, U> of(@Nonnull T first, @Nonnull U second) {
		return new Tuple<>(first, second);
	}

}
