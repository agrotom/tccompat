package net.agrotom.tccompat.data.materials.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.IRegistryData;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class RegistryData implements IRegistryData {

	private final List<Tuple<INamedTag<Item>, String>> tags;
	
	private final UnaryOperator<ForgeFlowingFluid.Properties> fluidProperty;
	private final UnaryOperator<FluidAttributes.Builder> fluidBuilder;
	private final UnaryOperator<AbstractBlock.Properties> blockProperty;
	private final UnaryOperator<Item.Properties> bucketProperty;
	
	public static Builder builder() {
		return new Builder();
	}
	
	private RegistryData(@Nonnull Builder builder) {
		this.tags = builder.tags;
		
		this.fluidProperty = builder.fluidProperty;
		this.fluidBuilder = builder.fluidBuilder;
		this.blockProperty = builder.blockProperty;
		this.bucketProperty = builder.bucketProperty;
	}
	
	public static class Builder {
		private List<Tuple<INamedTag<Item>, String>> tags = Collections.emptyList();
		
		private UnaryOperator<ForgeFlowingFluid.Properties> fluidProperty = property -> property;
		private UnaryOperator<FluidAttributes.Builder> fluidBuilder = builder -> builder;
		private UnaryOperator<AbstractBlock.Properties> blockProperty = property -> property;
		private UnaryOperator<Item.Properties> bucketProperty = property -> property;
		
		private Builder() {}
		
		@SafeVarargs
		public final Builder tags(@Nonnull Tuple<INamedTag<Item>, String>... tags) {
			this.tags = Arrays.asList(tags);
			
			return this;
		}
		
		public Builder fluidProperty(@Nonnull UnaryOperator<ForgeFlowingFluid.Properties> property) {
			this.fluidProperty = property;
			
			return this;
		}
		
		public Builder fluidBuilder(@Nonnull UnaryOperator<FluidAttributes.Builder> builder) {
			this.fluidBuilder = builder;
			
			return this;
		}
		
		public Builder blockProperty(@Nonnull UnaryOperator<AbstractBlock.Properties> property) {
			this.blockProperty = property;
			
			return this;
		}
		
		public Builder bucketProperty(@Nonnull UnaryOperator<Item.Properties> property) {
			this.bucketProperty = property;
			
			return this;
		}
		
		public IRegistryData build() {
			return new RegistryData(this);
		}
	}

	@Override
	public List<Tuple<INamedTag<Item>, String>> getTagDefinitions() {
		return tags;
	}

	@Override
	public ForgeFlowingFluid.Properties changeFluidProperty(ForgeFlowingFluid.Properties properties) {
		return fluidProperty.apply(properties);
	}

	@Override
	public FluidAttributes.Builder changeFluidBuilder(FluidAttributes.Builder builder) {
		return fluidBuilder.apply(builder);
	}

	@Override
	public AbstractBlock.Properties changeBlockProperties(
			AbstractBlock.Properties properties) {
		return blockProperty.apply(properties);
	}

	@Override
	public Item.Properties changeBucketProperties(Item.Properties properties) {
		return bucketProperty.apply(properties);
	}

}
