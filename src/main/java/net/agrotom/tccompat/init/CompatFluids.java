package net.agrotom.tccompat.init;

import java.util.HashMap;
import java.util.Map;
import net.agrotom.tccompat.Main;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.object.FluidObject;

public class CompatFluids {

	public static final Map<String, FluidObject<ForgeFlowingFluid>> FLUID_OBJECTS = new HashMap<>();
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Main.MOD_ID);
	
}
