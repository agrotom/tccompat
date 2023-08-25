package net.agrotom.tccompat.util;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.init.CompatBlocks;
import net.agrotom.tccompat.init.CompatFluids;
import net.agrotom.tccompat.init.CompatItems;
import net.agrotom.tccompat.init.Materials;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;

public class FastRegistry {

	public static final ResourceLocation MOLTEN_STILL = new ResourceLocation(TConstruct.MOD_ID,
			"block/fluid/molten/still");
	public static final ResourceLocation MOLTEN_FLOW = new ResourceLocation(TConstruct.MOD_ID,
			"block/fluid/molten/flowing");

	public static void registerMaterials() {

		// Materials.register(materialID, tier, order, craftable, color, hidden,
		// condition, stats, modifiers, extraData);

		Materials.MATERIALS_INFOS.forEach(info -> {
			final String modid = info.getModID();
			
			if (modid != null && !ModList.get().isLoaded(modid)) {
				return;
			}

			final String materialID = info.getMaterialId().getPath();
			final int materialColor = info.getColor();

			final RegistryWrapper<ForgeFlowingFluid.Source> tempStill = new RegistryWrapper<>();
			final RegistryWrapper<ForgeFlowingFluid.Flowing> tempFlow = new RegistryWrapper<>();

			final RegistryWrapper<FlowingFluidBlock> tempBlock = new RegistryWrapper<>();
			final RegistryWrapper<Item> tempItem = new RegistryWrapper<>();

			tempStill.setRegistryObject(CompatFluids.FLUIDS.register("molten_" + materialID, () -> {
				return new ForgeFlowingFluid.Source(createMaterialProperties(tempStill, tempFlow, tempBlock, tempItem,
						materialColor, info));
			}));

			tempFlow.setRegistryObject(CompatFluids.FLUIDS.register("flowing_molten_" + materialID, () -> {
				return new ForgeFlowingFluid.Flowing(createMaterialProperties(tempStill, tempFlow, tempBlock, tempItem,
						materialColor, info));
			}));

			final Supplier<Source> stillMaterialFluid = tempStill.getRegistryObject();

			tempBlock.setRegistryObject(CompatBlocks.BLOCKS.register("molten_" + materialID + "_block",
					() -> new FlowingFluidBlock(stillMaterialFluid, info
							.changeBlockProperties(Block.Properties.of(Material.LAVA).lightLevel((state) -> {
								return 15;
							}).randomTicks().strength(100.0F).noDrops()))));

			tempItem.setRegistryObject(CompatItems.ITEMS.register(materialID + "_bucket",
					() -> new BucketItem(stillMaterialFluid, info
							.changeBucketProperties(getDefaultBucketProperties(Main.MOD_ID)))));
			
			CompatFluids.FLUID_OBJECTS.add(new FluidObject<>(new ResourceLocation(Main.MOD_ID, "molten_" + materialID), materialID,
					tempStill.registryObject, tempFlow.registryObject, tempBlock.registryObject));
		});

	}

	public static Item.Properties getDefaultBucketProperties(@Nonnull String modid) {
		return new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1)
				.tab(ModList.get().isLoaded(modid) ? ItemGroup.TAB_MISC : null);
	}

	public static ForgeFlowingFluid.Properties createMaterialProperties(
			@Nonnull RegistryWrapper<ForgeFlowingFluid.Source> wrapperStill,
			@Nonnull RegistryWrapper<ForgeFlowingFluid.Flowing> wrapperFlow,
			@Nonnull RegistryWrapper<? extends FlowingFluidBlock> wrapperBlock,
			@Nonnull RegistryWrapper<Item> wrapperBucket, int color, @Nonnull IRegistryInfo registryInfo) {
		return registryInfo.changeFluidProperty(
				new ForgeFlowingFluid.Properties(wrapperStill.getRegistryObject(), wrapperFlow.getRegistryObject(),
						registryInfo
								.changeFluidBuilder(getDefaultBuilder().color(0xFF000000 + color).temperature(1000)))
						.explosionResistance(1000.0F).tickRate(20).block(wrapperBlock.getRegistryObject())
						.bucket(wrapperBucket.getRegistryObject()));
	}

	public static FluidAttributes.Builder getDefaultBuilder() {
		return FluidAttributes.builder(MOLTEN_STILL, MOLTEN_FLOW).overlay(MOLTEN_STILL).luminosity(15).density(3000)
				.viscosity(6000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
	}

	public static class RegistryWrapper<T> {
		private Supplier<T> registryObject = null;

		public RegistryWrapper() {

		}

		public RegistryWrapper(@Nonnull Supplier<T> registryObject) {
			this.registryObject = registryObject;
		}

		public void setRegistryObject(@Nonnull Supplier<T> registryObject) {
			this.registryObject = registryObject;
		}

		public Supplier<T> getRegistryObject() {
			return registryObject;
		}
	}

}
