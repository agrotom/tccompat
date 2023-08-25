package net.agrotom.tccompat.data.materials.impl;

import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.tccompat.data.materials.IMaterialData;
import net.agrotom.tccompat.data.materials.MaterialDataProvider;
import net.agrotom.tccompat.data.materials.MaterialSpriteProvider;
import net.agrotom.tccompat.data.materials.extra.IExtraData;
import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.util.IModifierSupplier;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class MaterialData implements IMaterialData {

	private final MaterialId id;
	private final String modId;
	private final int tier;
	private final int order;
	private final boolean craftable;
	private final int color;
	private final boolean hidden;
	private final ICondition condition;
	
	private final IMaterialStats[] stats;
	private final IModifierSupplier[] modifiers;
	
	private final IExtraData data;
	private final IRegistryInfo registryInfo;
	
	public static Builder builder(@Nonnull MaterialId materialId, @Nonnull IExtraData data) {
		return new Builder(materialId, data);
	}
	
	/*
	@Deprecated
	public MaterialData(@Nonnull MaterialId id, int tier, int order, boolean craftable, int color, boolean hidden,
			@Nullable ICondition condition, IMaterialStats[] stats, IModifierSupplier[] mods, @Nonnull IExtraData extraData) {
		this.id = id;
		this.tier = tier;
		this.order = order;
		this.craftable = craftable;
		this.color = color;
		this.hidden = hidden;
		this.condition = condition;
		
		this.stats = stats;
		this.modifiers = mods;
		
		this.data = extraData;
	}*/
	
	public MaterialData(@Nonnull Builder builder) {
		this.id = builder.id;
		this.tier = builder.tier;
		this.order = builder.order;
		this.craftable = builder.craftable;
		this.color = builder.color;
		this.hidden = builder.hidden;
		
		this.data = builder.data;
		this.registryInfo = builder.registryInfo;
		
		this.modifiers = builder.modifiers;
		
		this.modId = builder.modid;
		
		if (builder.modid != null) {
			if (builder.condition == null) {
				this.condition = new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("divinerpg"));
			}
			else {
				this.condition = new AndCondition(
						new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("divinerpg")),
						builder.condition);
			}
		}
		else {
			this.condition = builder.condition;
		}
		
		if (builder.isAutoStats) {
			this.stats = new IMaterialStats[3];
			
			this.stats[0] = new HeadMaterialStats(data.getHeadDurability(), data.getHeadMiningSpeed(),
					data.getHeadHarvestLevel(), data.getHeadAttackDamage());
			
			this.stats[1] = new HandleMaterialStats(data.getHandleDurability(), data.getHandleMiningSpeed(),
					data.getHandleAttackSpeed(), data.getHandleAttackDamage());
			
			this.stats[2] = new ExtraMaterialStats();
			
		}
		else {
			Objects.requireNonNull(builder.stats, id.getPath() + " material stats is null! Add some manually or automatically by #autostats "
					+ "method at net.agrotom.tccompat.data.materials.ExtraData#Builder class!");
			
			this.stats = builder.stats;
		}
	}
	
	@Override
	public MaterialId getID() {
		return id;
	}
	
	@Nullable
	@Override
	public String getModId() {
		return modId;
	}

	@Override
	public int getTier() {
		return tier;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean isCraftable() {
		return craftable;
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

	@Nullable
	@Override
	public ICondition getCondition() {
		return condition;
	}

	@Override
	public IMaterialStats[] getMaterialStats() {
		return stats;
	}

	@Override
	public IModifierSupplier[] getModifiers() {
		return modifiers;
	}

	@Override
	public IExtraData getExtraData() {
		return data;
	}
	
	@Override
	public IRegistryInfo getRegistryInfo() {
		return registryInfo;
	}
	
	@Override
	public void accept(@Nonnull MaterialSpriteProvider provider) {
		
	}
	
	@Override
	public void accept(@Nonnull MaterialDataProvider provider) {
		
	}
	
	public static class Builder {
		private final MaterialId id;
		private String modid = null;
		
		private int tier = 1;
		private int order = AbstractMaterialDataProvider.ORDER_GENERAL;
		private boolean craftable = false;
		private int color = 0xFFFFFF;
		private boolean hidden = false;
		private ICondition condition = null;
		
		private IMaterialStats[] stats = null;
		private IModifierSupplier[] modifiers = new IModifierSupplier[] {
				() -> TinkerModifiers.empty.get()
		};
		
		private IExtraData data = null;
		private IRegistryInfo registryInfo = IRegistryInfo.defaultInfo;
		
		private boolean isAutoStats = false;
		
		private Builder(@Nonnull MaterialId materialId, @Nonnull IExtraData data) {
			this.id = materialId;
			this.data = data;
		}

		/**
		 * Vanilla tool tiers:
		 * 0 - wood, gold,
		 * 1 - stone,
		 * 2 - iron,
		 * 3 - diamond,
		 * 4 - netherite
		 */
		public Builder tier(int tier) {
			this.tier = tier;
			
			return this;
		}

		public Builder order(int order) { // TODO: comment
			this.order = order;
			
			return this;
		}

		/**
		 * Is it getting by crafting parts at tinker tables or by a smeltery?
		 */
		public Builder craftable(boolean craftable) {
			this.craftable = craftable;
			
			return this;
		}

		public Builder color(int color) {
			this.color = color;
			
			return this;
		}

		public Builder hidden(boolean hidden) { // TODO: comment
			this.hidden = hidden;
			
			return this;
		}

		/**
		 * You can create mod condition faster by calling {@link #modCondition(String)}
		 */
		public Builder conditions(@Nonnull ICondition condition) {
			this.condition = condition;
			
			return this;
		}
		
		/**
		 * Provide modid to create material's mod dependency shortly.
		 */
		public Builder modCondition(@Nonnull String modid) {
			this.modid = modid;
			
			return this;
		}

		public Builder stats(@Nonnull IMaterialStats... stats) {
			this.stats = stats; 
			
			return this;
		}

		public Builder modifiers(@Nonnull IModifierSupplier... modifiers) {
			this.modifiers = modifiers;
			
			return this;
		}
		
		/**
		 *  <h2>CAREFULLY: THE METHOD REPLACES OTHER STATS!</h2> Creates material stats automatically according to given {@link IExtraData} instance. False by default.
		 */
		public Builder autoStats(boolean isAutoStats) {
			this.isAutoStats = isAutoStats;
			
			return this;
		}
		
		public Builder registryInfo(@Nonnull IRegistryInfo registryInfo) {
			this.registryInfo = registryInfo;
			
			return this;
		}
		
		public IMaterialData build() {
			return new MaterialData(this);
		}
		
	}

}
