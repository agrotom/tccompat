package net.agrotom.tccompat.data.materials.extra.impl;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.extra.IIngredient;
import net.agrotom.tccompat.data.materials.extra.IngredientType;

public class Ingredient implements IIngredient {

	private final String name;
	private final IngredientType type;
	
	public static IIngredient create(@Nonnull String name, @Nonnull IngredientType type) {
		return new Ingredient(name, type);
	}
	
	private Ingredient(@Nonnull String name, @Nonnull IngredientType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IngredientType getType() {
		return type;
	}

}
