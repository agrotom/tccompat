package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

public interface IMaterialVisitor {

	MaterialId getID();
	
	void accept(@Nonnull MaterialDataProvider provider);
	void accept(@Nonnull MaterialSpriteProvider provider);
	void accept(@Nonnull MaterialStatsDataProvider provider);
	void accept(@Nonnull MaterialTraitsDataProvider provider);
	void accept(@Nonnull MaterialRecipes provider);
	void accept(@Nonnull MaterialRenderInfo provider);
	
}
