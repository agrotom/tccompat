package net.agrotom.tccompat.util;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.init.CompatFluids;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class DataGenHelper {

	private final MaterialId material;
	private final MaterialRecipes provider;
	
	private final String modid;
	
	public DataGenHelper(@Nonnull MaterialId material, @Nonnull MaterialRecipes provider) {
		this(material, provider, null);
	}
	
	public DataGenHelper(@Nonnull MaterialId material, @Nonnull MaterialRecipes provider, @Nullable String modid) {
		this.material = material;
		this.provider = provider;
		this.modid = modid;
	}
	
	/**
	 * @param nugget - item id
	 * @param ingot - item id, should be like "minecraft:iron_ingot"
	 * @param block - item id
	 * @param modid - if recipes should be depend by a mod loading
	 */
	public void createDefaultMaterialRecipes(@Nullable String nugget, @Nullable String ingot, @Nullable String block) {
		
		Consumer<IFinishedRecipe> recipe = provider.getRecipe();
		
		final String path = provider.MATERIAL_FOLDER + material.getPath();
		
		if (recipe == null) {
			LOGGER.error("Can't gen " + material.toString() + " material recipe because provider returned a null as recipe!");
			return;
		}
		
		if (modid != null) {
			recipe = provider.withCondition(recipe, createModCondition(modid));
		}
		
		if (nugget != null) {
			provider.materialRecipe(recipe, material, ItemNameIngredient.from(new ResourceLocation(nugget)), 1, 9, path + "_nugget");
		}
		
		if (ingot != null) {
			provider.materialRecipe(recipe, material, ItemNameIngredient.from(new ResourceLocation(ingot)), 1, 1, path + "_ingot");
		}
		
		if (block != null) {
			provider.materialRecipe(recipe, material, ItemNameIngredient.from(new ResourceLocation(block)), 9, 1, path + "_block");
		}
		
	}
	
	public void createMeltingRecipes() {
		Consumer<IFinishedRecipe> recipe = provider.getRecipe();
		
		if (recipe == null) {
			LOGGER.error("Can't gen " + material.toString() + " material recipe because provider returned a null as recipe!");
			return;
		}
		
		if (modid != null) {
			recipe = provider.withCondition(recipe, createModCondition(modid));
		}
		
		final Optional<FluidObject<ForgeFlowingFluid>> fluidOptional = CompatFluids.getFluidObjectByName(new ResourceLocation(Main.MOD_ID,
				"molten_" + material.getPath()));
		
		if (fluidOptional.isPresent()) {
			provider.materialMeltingCasting(recipe, material, fluidOptional.get(), provider.MATERIAL_FOLDER);
		}
		
	}
	
	public void createSmelteryRecipes() {
		Consumer<IFinishedRecipe> recipe = provider.getRecipe();
		
		final String materialName = material.getPath();
		
		for (int i = 0; i < 100; i++) {
			System.out.println("molten_" + materialName);
		}
		
		FluidFind.getFluidByName(new ResourceLocation(Main.MOD_ID, "molten_" + materialName)).ifPresent(fluid -> {
			
			provider.metalMelting(recipe, fluid,
					materialName, false, provider.MELTING_FOLDER, false);
		});
		
	}
	
	public static ICondition createModCondition(@Nonnull String modid) {
		return new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition(modid)); 
	}
	
	private static final Logger LOGGER = LogManager.getLogger(DataGenHelper.class);

}
