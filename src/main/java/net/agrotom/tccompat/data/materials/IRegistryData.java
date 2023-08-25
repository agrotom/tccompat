package net.agrotom.tccompat.data.materials;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes.Builder;

public interface IRegistryData {

	public static final IRegistryData empty = new IRegistryData() {
		
		@Override
		public net.minecraftforge.fluids.ForgeFlowingFluid.Properties changeFluidProperty(
				net.minecraftforge.fluids.ForgeFlowingFluid.Properties properties) {
			return properties;
		}
		
		@Override
		public Builder changeFluidBuilder(Builder builder) {
			return builder;
		}
		
		@Override
		public net.minecraft.item.Item.Properties changeBucketProperties(net.minecraft.item.Item.Properties properties) {
			return properties;
		}
		
		@Override
		public Properties changeBlockProperties(Properties properties) {
			return properties;
		}

		@Override
		public List<Tuple<INamedTag<Item>, String>> getTagDefinitions() {
			return Collections.emptyList();
		}
	};
	
	@Nonnull
	List<Tuple<INamedTag<Item>, String>> getTagDefinitions();
	
	@Nonnull
	ForgeFlowingFluid.Properties changeFluidProperty(@Nonnull ForgeFlowingFluid.Properties properties);
	
	@Nonnull
	FluidAttributes.Builder changeFluidBuilder(@Nonnull FluidAttributes.Builder builder);
	
	@Nonnull
	AbstractBlock.Properties changeBlockProperties(@Nonnull AbstractBlock.Properties properties);
	
	@Nonnull
	Item.Properties changeBucketProperties(@Nonnull Item.Properties properties);
	
}
