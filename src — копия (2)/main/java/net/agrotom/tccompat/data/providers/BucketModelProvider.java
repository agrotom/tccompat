package net.agrotom.tccompat.data.providers;

import java.util.Optional;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.init.Materials;
import net.agrotom.tccompat.util.FluidFind;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BucketModelProvider extends ItemModelProvider {
	
	public BucketModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return "TC Compat Bucket Model Provider";
	}

	@Override
	protected void registerModels() {
		Materials.MATERIALS_INFOS.forEach(material -> {
			final String materialName = material.getMaterialId().getPath();
			
			final Optional<Fluid> materialFluid = FluidFind.getFluidByName(new ResourceLocation(Main.MOD_ID, "molten_" + materialName));
			
			materialFluid.ifPresent(fluid -> {
				this.withExistingParent(materialName + "_bucket", new ResourceLocation("forge:item/bucket_drip"))
				.customLoader((builder, helper) -> DynamicBucketModelBuilder.begin(builder, helper))
				.fluid(fluid).end();
			});
		});
	}

}
