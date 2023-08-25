package net.agrotom.tccompat.data.materials.extra.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.tccompat.data.materials.extra.IIngredient;
import net.agrotom.tccompat.data.materials.extra.IMaterialIngredient;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialIngredient implements IMaterialIngredient {

	private final String name;
	
	private final List<IIngredient> ingredients;
	private final IIngredient leftover;
	private final int value;
	private final int needed;
	
	public static Builder builder(@Nonnull String name) {
		return new Builder(name);
	}
	
	public MaterialIngredient(@Nonnull Builder builder) {
		this.name = builder.name;
		
		this.ingredients = builder.ingredients;
		
		if (ingredients.isEmpty()) {
			throw new RuntimeException("Ingredients list is empty.");
		}
		
		this.leftover = builder.leftover;
		this.value = builder.value;
		this.needed = builder.needed;
	}
	
	@Override
	public MaterialId getMaterial() {
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<IIngredient> getIngredients() {
		return ingredients;
	}

	@Nullable
	@Override
	public IIngredient getLeftover() {
		return leftover;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNeeded() {
		return needed;
	}
	
	public static class Builder {
		private String name;
		
		private List<IIngredient> ingredients;
		private IIngredient leftover = null;
		private int value = 1;
		private int needed = 1;
		
		private Builder(@Nonnull String name) {
			this.name = name;
		}
		
		public Builder ingredients(@Nonnull IIngredient... ingredients) {
			this.ingredients = Arrays.asList(ingredients);
			
			return this;
		}
		
		public Builder leftover(@Nonnull IIngredient ingredient) {
			this.leftover = ingredient;
			
			return this;
		}
		
		public Builder value(int value) {
			this.value = value;
			
			return this;
		}
		
		public Builder needed(int needed) {
			this.needed = needed;
			
			return this;
		}
		
		public IMaterialIngredient build() {
			return new MaterialIngredient(this);
		}
	}

}
