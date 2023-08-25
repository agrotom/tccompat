package net.agrotom.tccompat.data.materials.extra;

import javax.annotation.Nonnull;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidAttributes.Builder;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;

/**
 * Provide it to {@link IExtraData} if you want to change it's fluid, block or bucket of it.
 */
public interface IRegistryInfo {
	public static final IRegistryInfo defaultInfo = new IRegistryInfo() {

		@Override
		public Properties changeFluidProperty(Properties properties) {
			return properties;
		}

		@Override
		public Builder changeFluidBuilder(Builder builder) {
			return builder;
		}

		@Override
		public AbstractBlock.Properties changeBlockProperties(
				AbstractBlock.Properties properties) {
			return properties;
		}

		@Override
		public Item.Properties changeBucketProperties(Item.Properties properties) {
			return properties;
		}
		
	};
	
	@Nonnull
	ForgeFlowingFluid.Properties changeFluidProperty(@Nonnull ForgeFlowingFluid.Properties properties);
	@Nonnull
	FluidAttributes.Builder changeFluidBuilder(@Nonnull FluidAttributes.Builder builder);
	
	@Nonnull
	AbstractBlock.Properties changeBlockProperties(@Nonnull AbstractBlock.Properties properties);
	
	@Nonnull
	Item.Properties changeBucketProperties(@Nonnull Item.Properties properties);
}
