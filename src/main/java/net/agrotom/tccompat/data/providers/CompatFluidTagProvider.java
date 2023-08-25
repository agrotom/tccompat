package net.agrotom.tccompat.data.providers;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CompatFluidTagProvider extends FluidTagsProvider {

	public CompatFluidTagProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
		super(generator, modId, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return "TC Compat Fluid Tag Provider";
	}
	
	@Override
	protected void addTags() {
		Materials.MATERIALS.stream().map(tuple -> tuple.getA().getName()).forEach(name -> {
			final String STILL = "molten_" + name;
			final String FLOWING = "flowing_molten_" + name;
			
			final INamedTag<Fluid> FLUID_TAG = FluidTags.bind(STILL);
			
			super.tag(FLUID_TAG).addOptional(new ResourceLocation(Main.MOD_ID, STILL));
			super.tag(FLUID_TAG).addOptional(new ResourceLocation(Main.MOD_ID, FLOWING));
		});
	}

}
