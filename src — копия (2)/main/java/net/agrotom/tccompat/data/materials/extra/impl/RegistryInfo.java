package net.agrotom.tccompat.data.materials.extra.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class RegistryInfo implements IRegistryInfo {

	private final ResourceLocation materialId;
	private final String modid;
	private final int materialColor;
	
	private final List<Tuple<INamedTag<Item>, String>> tags;
	
	private final UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties;
	private final UnaryOperator<FluidAttributes.Builder> fluidBuilder;
	private final UnaryOperator<AbstractBlock.Properties> blockProperties;
	private final UnaryOperator<Item.Properties> bucketProperties;
	
	public static Builder builder(@Nonnull ResourceLocation materialId) {
		return new Builder(materialId);
	}
	
	public RegistryInfo(@Nonnull Builder builder) {
		this.materialId = builder.materialId;
		this.modid = builder.modid;
		this.materialColor = builder.materialColor;
		
		this.tags = builder.tags;
		
		this.fluidProperties = builder.fluidProperties;
		this.fluidBuilder = builder.fluidBuilder;
		this.blockProperties = builder.blockProperties;
		this.bucketProperties = builder.bucketProperties;
	}
	
	@Override
	public ResourceLocation getMaterialId() {
		return materialId;
	}
	
	@Nullable
	@Override
	public String getModID() {
		return modid;
	}
	
	@Override
	public int getColor() {
		return materialColor;
	}

	@Override
	public ForgeFlowingFluid.Properties changeFluidProperty(ForgeFlowingFluid.Properties properties) {
		return fluidProperties.apply(properties);
	}

	@Override
	public FluidAttributes.Builder changeFluidBuilder(FluidAttributes.Builder builder) {
		return fluidBuilder.apply(builder);
	}

	@Override
	public AbstractBlock.Properties changeBlockProperties(AbstractBlock.Properties properties) {
		return blockProperties.apply(properties);
	}

	@Override
	public Item.Properties changeBucketProperties(Item.Properties properties) {
		return bucketProperties.apply(properties);
	}
	
	public static class Builder {
		
		private ResourceLocation materialId;
		private String modid = null;
		private int materialColor = 0xFFFFFF;
		
		private List<Tuple<INamedTag<Item>, String>> tags = Collections.emptyList();
		
		private UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties = null;
		private UnaryOperator<FluidAttributes.Builder> fluidBuilder = null;
		private UnaryOperator<AbstractBlock.Properties> blockProperties = null;
		private UnaryOperator<Item.Properties> bucketProperties = null;
		
		private Builder(@Nonnull ResourceLocation materialId) {
			this.materialId = materialId;
		}
		
		public Builder mod(@Nonnull String modid) {
			this.modid = modid;
			
			return this;
		}
		
		public Builder color(int color) {
			this.materialColor = color;
			
			return this;
		}
		
		public Builder setFluidProperties(@Nonnull UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties) {
			this.fluidProperties = fluidProperties;
			
			return this;
		}

		public Builder setFluidBuilder(@Nonnull UnaryOperator<FluidAttributes.Builder> fluidBuilder) {
			this.fluidBuilder = fluidBuilder;
			
			return this;
		}

		public Builder setBlockProperties(@Nonnull UnaryOperator<AbstractBlock.Properties> blockProperties) {
			this.blockProperties = blockProperties;
			
			return this;
		}

		public Builder setBucketProperties(@Nonnull UnaryOperator<Item.Properties> bucketProperties) {
			this.bucketProperties = bucketProperties;
			
			return this;
		}
		
		/**
		 * 
		 * @param tags - Item tag, item id
		 */
		@SuppressWarnings("unchecked")
		public Builder tags(@Nonnull Tuple<INamedTag<Item>, String>... tags) {
			this.tags = Arrays.asList(tags);
			
			return this;
		}

		public IRegistryInfo build() {
			return new RegistryInfo(this);
		}
	}

	@Override
	public List<Tuple<INamedTag<Item>, String>> getTagDefinitions() {
		return tags;
	}

}
