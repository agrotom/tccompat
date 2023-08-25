package net.agrotom.tccompat.data.materials.extra;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.util.Tuple;

/**
 * Normally uses for creating data files automatically.
 */
public interface IExtraData {
	
	int getHeadDurability();
	float getHeadMiningSpeed();
	int getHeadHarvestLevel();
	float getHeadAttackDamage();
	
	float getHandleDurability();
	float getHandleMiningSpeed();
	float getHandleAttackSpeed();
	float getHandleAttackDamage();
	
	int getExtraDurability();

	@Nullable
	List<IMaterialIngredient> getMaterialIngredients();
	
	List<Tuple<String, Integer>> getModifiers();
	
}
