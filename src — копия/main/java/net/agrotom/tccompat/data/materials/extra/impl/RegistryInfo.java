package net.agrotom.tccompat.data.materials.extra.impl;

import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class RegistryInfo implements IRegistryInfo {

	private final UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties;
	private final UnaryOperator<FluidAttributes.Builder> fluidBuilder;
	private final UnaryOperator<AbstractBlock.Properties> blockProperties;
	private final UnaryOperator<Item.Properties> bucketProperties;
	
	public static Builder builder() {
		return new Builder();
	}
	
	public RegistryInfo(@Nonnull Builder builder) {
		this.fluidProperties = builder.fluidProperties;
		this.fluidBuilder = builder.fluidBuilder;
		this.blockProperties = builder.blockProperties;
		this.bucketProperties = builder.bucketProperties;
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
		private UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties = null;
		private UnaryOperator<FluidAttributes.Builder> fluidBuilder = null;
		private UnaryOperator<AbstractBlock.Properties> blockProperties = null;
		private UnaryOperator<Item.Properties> bucketProperties = null;
		
		public void setFluidProperties(@Nonnull UnaryOperator<ForgeFlowingFluid.Properties> fluidProperties) {
			this.fluidProperties = fluidProperties;
		}

		public void setFluidBuilder(@Nonnull UnaryOperator<FluidAttributes.Builder> fluidBuilder) {
			this.fluidBuilder = fluidBuilder;
		}

		public void setBlockProperties(@Nonnull UnaryOperator<AbstractBlock.Properties> blockProperties) {
			this.blockProperties = blockProperties;
		}

		public void setBucketProperties(@Nonnull UnaryOperator<Item.Properties> bucketProperties) {
			this.bucketProperties = bucketProperties;
		}

		public IRegistryInfo build() {
			return new RegistryInfo(this);
		}
	}

}
