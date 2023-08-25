package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.tccompat.data.materials.extra.IExtraData;
import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.util.IModifierSupplier;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;

public interface IMaterialData {
	MaterialId getID();
	@Nullable
	String getModId();
	int getTier();
	int getOrder();
	boolean isCraftable();
	int getColor();
	boolean isHidden();
	@Nullable
	ICondition getCondition();
	
	void accept(@Nonnull MaterialSpriteProvider provider);
	void accept(@Nonnull MaterialDataProvider provider);
	
	IMaterialStats[] getMaterialStats();
	IModifierSupplier[] getModifiers();
	
	IExtraData getExtraData();
	IRegistryInfo getRegistryInfo();
}
