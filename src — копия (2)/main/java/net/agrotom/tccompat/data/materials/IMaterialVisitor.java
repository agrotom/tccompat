package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.data.providers.MaterialDataProvider;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.data.providers.MaterialRenderInfo;
import net.agrotom.tccompat.data.providers.MaterialSpriteProvider;
import net.agrotom.tccompat.data.providers.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.providers.MaterialTraitsDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public interface IMaterialVisitor {

	MaterialId getID();
	IRegistryInfo getRegistryInfo();
	
	
	void accept(@Nonnull MaterialDataProvider provider);
	void accept(@Nonnull MaterialSpriteProvider provider);
	void accept(@Nonnull MaterialStatsDataProvider provider);
	void accept(@Nonnull MaterialTraitsDataProvider provider);
	void accept(@Nonnull MaterialRecipes provider);
	void accept(@Nonnull MaterialRenderInfo provider);
	
}
