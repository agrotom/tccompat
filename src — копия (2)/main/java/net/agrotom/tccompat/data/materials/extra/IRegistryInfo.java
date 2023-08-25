package net.agrotom.tccompat.data.materials.extra;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidAttributes.Builder;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;

/**
 * Provide it to {@link IExtraData} if you want to change it's fluid, block or bucket of it.
 */
public interface IRegistryInfo {
	
	public static IRegistryInfo defaultInfo(@Nonnull ResourceLocation materialId) {
		return new IRegistryInfo() {
			
			@Override
			public ResourceLocation getMaterialId() {
				return materialId;
			}
			
			@Override
			public String getModID() {
				return null;
			}
			
			@Override
			public int getColor() {
				return 0xFFFFFF;
			}
			
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

			@Override
			public List<Tuple<INamedTag<Item>, String>> getTagDefinitions() {
				return Collections.emptyList();
			}
			
		};
	}
	
	@Nonnull
	ResourceLocation getMaterialId();
	
	@Nullable
	String getModID();
	
	int getColor();
	
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
