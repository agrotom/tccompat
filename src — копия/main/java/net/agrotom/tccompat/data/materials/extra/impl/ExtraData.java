package net.agrotom.tccompat.data.materials.extra.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.extra.IExtraData;
import net.agrotom.tccompat.data.materials.extra.IMaterialIngredient;
import net.minecraft.util.Tuple;

public class ExtraData implements IExtraData {
	
	private final int headDurability;
	private final float headMiningSpeed;
	private final int headHarvestLevel;
	private final float headAttackDamage;
	private final float handleDurability;
	private final float handleMiningSpeed;
	private final float handleAttackSpeed;
	private final float handleAttackDamage;
	private final int extraDurability;
	
	private final List<IMaterialIngredient> materialIngredients;
	
	private final List<Tuple<String, Integer>> modifiers;

	public static DataBuilder builder() {
		return new DataBuilder();
	}
	
	public ExtraData(@Nonnull DataBuilder builder) {
		this.headDurability = builder.headDurability;
		this.headMiningSpeed = builder.headMiningSpeed;
		this.headHarvestLevel = builder.headHarvestLevel;
		this.headAttackDamage = builder.headAttackDamage;
		this.handleDurability = builder.handleDurability;
		this.handleMiningSpeed = builder.handleMiningSpeed;
		this.handleAttackSpeed = builder.handleAttackSpeed;
		this.handleAttackDamage = builder.handleAttackDamage;
		this.extraDurability = builder.extraDurability;
		
		this.materialIngredients = builder.materialIngredients;
		
		this.modifiers = builder.modifiers;
	}

	@Override
	public int getHeadDurability() {
		return headDurability;
	}

	@Override
	public float getHeadMiningSpeed() {
		return headMiningSpeed;
	}

	@Override
	public int getHeadHarvestLevel() {
		return headHarvestLevel;
	}

	@Override
	public float getHeadAttackDamage() {
		return headAttackDamage;
	}

	@Override
	public float getHandleDurability() {
		return handleDurability;
	}

	@Override
	public float getHandleMiningSpeed() {
		return handleMiningSpeed;
	}

	@Override
	public float getHandleAttackSpeed() {
		return handleAttackSpeed;
	}

	@Override
	public float getHandleAttackDamage() {
		return handleAttackDamage;
	}

	@Override
	public int getExtraDurability() {
		return extraDurability;
	}
	
	@Override
	public List<IMaterialIngredient> getMaterialIngredients() {
		return null;
	}

	@Override
	public List<Tuple<String, Integer>> getModifiers() {
		return modifiers;
	}
	
	public static class DataBuilder {
		
		private int headDurability = 0;
		private float headMiningSpeed = 0.0F;
		private int headHarvestLevel = 0;
		private float headAttackDamage = 0.0F;
		private float handleDurability = 0.0F;
		private float handleMiningSpeed = 0.0F;
		private float handleAttackSpeed = 0.0F;
		private float handleAttackDamage = 0.0F;
		private int extraDurability = 0;
		
		private List<IMaterialIngredient> materialIngredients = Collections.emptyList();
		
		private List<Tuple<String, Integer>> modifiers = null;

		public DataBuilder headDurability(int headDurability) {
			this.headDurability = headDurability;
			return this;
		}

		public DataBuilder headMiningSpeed(float headMiningSpeed) {
			this.headMiningSpeed = headMiningSpeed;
			return this;
		}

		public DataBuilder headHarvestLevel(int headHarvestLevel) {
			this.headHarvestLevel = headHarvestLevel;
			return this;
		}

		public DataBuilder headAttackDamage(float headAttackDamage) {
			this.headAttackDamage = headAttackDamage;
			return this;
		}

		public DataBuilder handleDurability(float handleDurability) {
			this.handleDurability = handleDurability;
			return this;
		}

		public DataBuilder handleMiningSpeed(float handleMiningSpeed) {
			this.handleMiningSpeed = handleMiningSpeed;
			return this;
		}

		public DataBuilder handleAttackSpeed(float handleAttackSpeed) {
			this.handleAttackSpeed = handleAttackSpeed;
			return this;
		}

		public DataBuilder handleAttackDamage(float handleAttackDamage) {
			this.handleAttackDamage = handleAttackDamage;
			return this;
		}

		public DataBuilder extraDurability(int extraDurability) {
			this.extraDurability = extraDurability;
			return this;
		}

		public DataBuilder modifiers(@Nonnull List<Tuple<String, Integer>> modifiers) {
			this.modifiers = modifiers;
			return this;
		}
		
		public DataBuilder materialIngredients(@Nonnull IMaterialIngredient... materialIngredients) {
			this.materialIngredients = Arrays.asList(materialIngredients);
			
			return this;
		}

		public IExtraData build() {
			return new ExtraData(this);
		}

	}

}
