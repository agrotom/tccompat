package net.agrotom.tccompat.util;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.init.CompatFluids;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class FluidFind {

	private FluidFind() {}
	
	public static final Optional<Fluid> getFluidByName(@Nonnull ResourceLocation name) {
		for (final RegistryObject<Fluid> fluid : CompatFluids.FLUIDS.getEntries()) {
			if (fluid.getId().equals(name)) {
				return Optional.of(fluid.get());
			}
		}
		
		return Optional.empty();
	}

}
