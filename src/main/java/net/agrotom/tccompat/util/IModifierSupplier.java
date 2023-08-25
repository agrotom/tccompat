package net.agrotom.tccompat.util;

import java.util.function.Supplier;

import slimeknights.tconstruct.library.modifiers.Modifier;

@FunctionalInterface
public interface IModifierSupplier extends Supplier<Modifier> {
	
}
