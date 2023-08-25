package net.agrotom.tccompat.data.materials.impl;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.IMaterialVisitor;
import net.agrotom.tccompat.data.materials.MaterialDataProvider;
import net.agrotom.tccompat.data.materials.MaterialRecipes;
import net.agrotom.tccompat.data.materials.MaterialRenderInfo;
import net.agrotom.tccompat.data.materials.MaterialSpriteProvider;
import net.agrotom.tccompat.data.materials.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.materials.MaterialTraitsDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialVisitor implements IMaterialVisitor {
	
	private final MaterialId id;
	
	private final BiConsumer<MaterialId, MaterialDataProvider> visitorMaterial;
	private final BiConsumer<MaterialId, MaterialSpriteProvider> visitorSprite;
	private final BiConsumer<MaterialId, MaterialStatsDataProvider> visitorStats;
	private final BiConsumer<MaterialId, MaterialTraitsDataProvider> visitorTraits;
	private final BiConsumer<MaterialId, MaterialRecipes> visitorRecipes;
	private final BiConsumer<MaterialId, MaterialRenderInfo> visitorRenderInfo;

	public MaterialVisitor(@Nonnull MaterialId id,
			@Nonnull BiConsumer<MaterialId, MaterialDataProvider> visitorMaterial,
			@Nonnull BiConsumer<MaterialId, MaterialSpriteProvider> visitorSprite,
			@Nonnull BiConsumer<MaterialId, MaterialStatsDataProvider> visitorStats,
			@Nonnull BiConsumer<MaterialId, MaterialTraitsDataProvider> visitorTraits,
			@Nonnull BiConsumer<MaterialId, MaterialRecipes> visitorRecipes,
			@Nonnull BiConsumer<MaterialId, MaterialRenderInfo> visitorRenderInfo) {
		this.id = id;
		this.visitorMaterial = visitorMaterial;
		this.visitorSprite = visitorSprite;
		this.visitorStats = visitorStats;
		this.visitorTraits = visitorTraits;
		this.visitorRecipes = visitorRecipes;
		this.visitorRenderInfo = visitorRenderInfo;
	}
	
	@Override
	public MaterialId getID() {
		return id;
	}
	
	@Override
	public void accept(@Nonnull MaterialDataProvider provider) {
		visitorMaterial.accept(id, provider);
	}

	@Override
	public void accept(@Nonnull MaterialSpriteProvider provider) {
		visitorSprite.accept(id, provider);
	}

	@Override
	public void accept(@Nonnull MaterialStatsDataProvider provider) {
		visitorStats.accept(id, provider);
	}

	@Override
	public void accept(@Nonnull MaterialTraitsDataProvider provider) {
		visitorTraits.accept(id, provider);
	}

	@Override
	public void accept(@Nonnull MaterialRecipes provider) {
		visitorRecipes.accept(id, provider);
	}

	@Override
	public void accept(MaterialRenderInfo provider) {
		visitorRenderInfo.accept(id, provider);
	}

}
