package net.agrotom.tccompat.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;

public class CompatFluids {

	public static final List<FluidObject<ForgeFlowingFluid>> FLUID_OBJECTS = new ArrayList<>();
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Main.MOD_ID);
	
	public static final ResourceLocation MOLTEN_STILL = new ResourceLocation(TConstruct.MOD_ID, "block/fluid/molten/still");
	public static final ResourceLocation MOLTEN_FLOW = new ResourceLocation(TConstruct.MOD_ID, "block/fluid/molten/flowing");

	public static final RegistryObject<ForgeFlowingFluid.Source> REALMITE_STILL = FLUIDS.register("molten_realmite", () -> {
		return new ForgeFlowingFluid.Source(createRealmiteProps().explosionResistance(1000.0F).tickRate(20));
	});
	
	public static final RegistryObject<ForgeFlowingFluid.Flowing> REALMITE_FLOW = FLUIDS.register("flowing_molten_realmite", () -> {
		return new ForgeFlowingFluid.Flowing(createRealmiteProps().explosionResistance(1000.0F).tickRate(20));
	});
	
	public static ForgeFlowingFluid.Properties createRealmiteProps() {
		return new ForgeFlowingFluid.Properties(REALMITE_STILL, REALMITE_FLOW, getDefaultBuilder().color(0xFFFF931C).temperature(1000)).block(CompatBlocks.MOLTEN_REALMITE)
				.bucket(CompatItems.REALMITE_BUCKET);
	}
	
	public static FluidAttributes.Builder getDefaultBuilder() {
		return FluidAttributes.builder(MOLTEN_STILL, MOLTEN_FLOW).overlay(MOLTEN_STILL)
				.luminosity(15).density(3000).viscosity(6000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
	}
	
	public static Optional<FluidObject<ForgeFlowingFluid>> getFluidObjectByName(@Nonnull ResourceLocation fluidId) {
		for (final FluidObject<ForgeFlowingFluid> object : FLUID_OBJECTS) {
			if (object.getId().equals(fluidId)) {
				return Optional.of(object);
			}
		}
		
		return Optional.empty();
	}
	
}
