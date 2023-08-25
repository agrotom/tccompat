package net.agrotom.tccompat.data.materials.impl;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.IMaterialVisitor;
import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.data.providers.MaterialDataProvider;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.data.providers.MaterialRenderInfo;
import net.agrotom.tccompat.data.providers.MaterialSpriteProvider;
import net.agrotom.tccompat.data.providers.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.providers.MaterialTraitsDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialVisitor implements IMaterialVisitor {
	
	private final MaterialId id;
	private final IRegistryInfo registryInfo; 
	
	private final BiConsumer<MaterialId, MaterialDataProvider> visitorMaterial;
	private final BiConsumer<MaterialId, MaterialSpriteProvider> visitorSprite;
	private final BiConsumer<MaterialId, MaterialStatsDataProvider> visitorStats;
	private final BiConsumer<MaterialId, MaterialTraitsDataProvider> visitorTraits;
	private final BiConsumer<MaterialId, MaterialRecipes> visitorRecipes;
	private final BiConsumer<MaterialId, MaterialRenderInfo> visitorRenderInfo;
	
	public static Builder builder(@Nonnull MaterialId id) {
		return new Builder(id);
	}
	
	public MaterialVisitor(@Nonnull Builder builder) {
		this.id = builder.id;
		this.registryInfo = builder.registryInfo;
		
		this.visitorMaterial = builder.visitorMaterial;
		this.visitorSprite = builder.visitorSprite;
		this.visitorStats = builder.visitorStats;
		this.visitorTraits = builder.visitorTraits;
		this.visitorRecipes = builder.visitorRecipes;
		this.visitorRenderInfo = builder.visitorRenderInfo;
	}
	
	@Override
	public MaterialId getID() {
		return id;
	}
	
	@Override
	public IRegistryInfo getRegistryInfo() {
		return registryInfo;
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
	
	public static class Builder {
		private MaterialId id;
		private IRegistryInfo registryInfo;
		
		private BiConsumer<MaterialId, MaterialDataProvider> visitorMaterial = (material, provider) -> { return; };
		private BiConsumer<MaterialId, MaterialSpriteProvider> visitorSprite = (material, provider) -> { return; };
		private BiConsumer<MaterialId, MaterialStatsDataProvider> visitorStats = (material, provider) -> { return; };
		private BiConsumer<MaterialId, MaterialTraitsDataProvider> visitorTraits = (material, provider) -> { return; };
		private BiConsumer<MaterialId, MaterialRecipes> visitorRecipes = (material, provider) -> { return; };
		private BiConsumer<MaterialId, MaterialRenderInfo> visitorRenderInfo = (material, provider) -> { return; };
		
		private Builder(@Nonnull MaterialId id) {
			this.id = id;
			registryInfo = IRegistryInfo.defaultInfo(id);
		}
		
		public Builder info(@Nonnull IRegistryInfo info) {
			this.registryInfo = info;
			
			return this;
		}
		
		public Builder data(@Nonnull BiConsumer<MaterialId, MaterialDataProvider> data) {
			this.visitorMaterial = data;
			
			return this;
		}
		
		public Builder sprite(@Nonnull BiConsumer<MaterialId, MaterialSpriteProvider> sprite) {
			this.visitorSprite = sprite;
			
			return this;
		}
		
		public Builder stats(@Nonnull BiConsumer<MaterialId, MaterialStatsDataProvider> stats) {
			this.visitorStats = stats;
			
			return this;
		}
		
		public Builder traits(@Nonnull BiConsumer<MaterialId, MaterialTraitsDataProvider> traits) {
			this.visitorTraits = traits;
			
			return this;
		}
		
		public Builder recipes(@Nonnull BiConsumer<MaterialId, MaterialRecipes> recipes) {
			this.visitorRecipes = recipes;
			
			return this;
		}
		
		public Builder render(@Nonnull BiConsumer<MaterialId, MaterialRenderInfo> render) {
			this.visitorRenderInfo = render;
			
			return this;
		}
		
		public IMaterialVisitor build() {
			return new MaterialVisitor(this);
		}
	}

}
