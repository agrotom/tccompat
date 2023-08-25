package net.agrotom.tccompat.data.materials.impl;

import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.IDataVisitors;
import net.agrotom.tccompat.data.materials.IMaterialData;
import net.agrotom.tccompat.data.providers.MaterialDataProvider;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.data.providers.MaterialRenderInfo;
import net.agrotom.tccompat.data.providers.MaterialSpriteProvider;
import net.agrotom.tccompat.data.providers.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.providers.MaterialTraitsDataProvider;

public class DataVisitors implements IDataVisitors {

	private final IMaterialData parent;
	
	private final BiConsumer<IMaterialData, MaterialDataProvider> data;
	private final BiConsumer<IMaterialData, MaterialSpriteProvider> sprite;
	private final BiConsumer<IMaterialData, MaterialStatsDataProvider> stats;
	private final BiConsumer<IMaterialData, MaterialTraitsDataProvider> traits;
	private final BiConsumer<IMaterialData, MaterialRecipes> recipes;
	private final BiConsumer<IMaterialData, MaterialRenderInfo> render;
	
	public static Builder builder(@Nonnull IMaterialData parent) {
		return new Builder(parent);
	}
	
	private DataVisitors(@Nonnull Builder builder) {
		this.parent = builder.parent;
		
		this.data = builder.data;
		this.sprite = builder.sprite;
		this.stats = builder.stats;
		this.traits = builder.traits;
		this.recipes = builder.recipes;
		this.render = builder.render;
	}
	
	public static class Builder {

		private final IMaterialData parent;
		
		private BiConsumer<IMaterialData, MaterialDataProvider> data = (material, provider) -> {};
		private BiConsumer<IMaterialData, MaterialSpriteProvider> sprite = (material, provider) -> {};
		private BiConsumer<IMaterialData, MaterialStatsDataProvider> stats = (material, provider) -> {};
		private BiConsumer<IMaterialData, MaterialTraitsDataProvider> traits = (material, provider) -> {};
		private BiConsumer<IMaterialData, MaterialRecipes> recipes = (material, provider) -> {};
		private BiConsumer<IMaterialData, MaterialRenderInfo> render = (material, provider) -> {};
		
		private Builder(@Nonnull IMaterialData parent) {
			this.parent = parent;
		}
		
		public Builder data(@Nonnull BiConsumer<IMaterialData, MaterialDataProvider> data) {
			this.data = data;
			
			return this;
		}

		public Builder sprite(@Nonnull BiConsumer<IMaterialData, MaterialSpriteProvider> sprite) {
			this.sprite = sprite;
			
			return this;
		}

		public Builder stats(@Nonnull BiConsumer<IMaterialData, MaterialStatsDataProvider> stats) {
			this.stats = stats;
			
			return this;
		}

		public Builder traits(@Nonnull BiConsumer<IMaterialData, MaterialTraitsDataProvider> traits) {
			this.traits = traits;
			
			return this;
		}

		public Builder recipes(@Nonnull BiConsumer<IMaterialData, MaterialRecipes> recipes) {
			this.recipes = recipes;
			
			return this;
		}

		public Builder render(@Nonnull BiConsumer<IMaterialData, MaterialRenderInfo> render) {
			this.render = render;
			
			return this;
		}
		
		public IDataVisitors build() {
			return new DataVisitors(this);
		}
	}
	
	@Override
	public IMaterialData getParent() {
		return parent;
	}

	@Override
	public void accept(@Nonnull MaterialDataProvider provider) {
		data.accept(parent, provider);
	}

	@Override
	public void accept(@Nonnull MaterialSpriteProvider provider) {
		sprite.accept(parent, provider);
	}

	@Override
	public void accept(@Nonnull MaterialStatsDataProvider provider) {
		stats.accept(parent, provider);
	}

	@Override
	public void accept(@Nonnull MaterialTraitsDataProvider provider) {
		traits.accept(parent, provider);
	}

	@Override
	public void accept(@Nonnull MaterialRecipes provider) {
		recipes.accept(parent, provider);
	}

	@Override
	public void accept(@Nonnull MaterialRenderInfo provider) {
		render.accept(parent, provider);
	}

}
