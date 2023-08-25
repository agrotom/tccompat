package net.agrotom.tccompat.util;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.init.CompatFluids;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FluidObject;

public class FluidFind {

	private FluidFind() {}
	
	public static Optional<Fluid> getFluidByName(@Nonnull ResourceLocation name) {
		return CompatFluids.FLUIDS.getEntries().stream().filter(registry -> registry.getId().equals(name)).map(registry -> registry.get()).findFirst();
	}
	
	public static Optional<FluidObject<ForgeFlowingFluid>> getFluidObjectByName(@Nonnull String name) {
		return Optional.ofNullable(CompatFluids.FLUID_OBJECTS.get(name));
	}

}
