package net.agrotom.tccompat.data.materials;

import java.util.function.Consumer;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.materials.extra.IMaterialIngredient;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.tools.data.material.MaterialRecipeProvider;

public class MaterialRecipes extends MaterialRecipeProvider
		implements IConditionBuilder, IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, IRecipeHelper {

	public MaterialRecipes(DataGenerator generator) {
		super(generator);
	}

	@Override
	public String getModId() {
		return "TC Compat Material Recipes Provider";
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipe) {
		final String castingFolder = "smeltery/casting/metal/";
		final String meltingFolder = "smeltery/melting/metal/";
		final String alloyFolder = "smeltery/alloys/";
		final String materialFolder = "tools/materials/";
		final String compositeFolder = "tools/parts/composite/";
		final String modifierFolder = "tools/modifiers/";
		final String salvageFolder = "tools/modifiers/salvage/";
		final String slotlessFolder = "tools/modifiers/slotless/";
		final String spillFolder = "tools/spilling/";
		final String toolFolder = "tools/building/";
		final String partFolder = "tools/parts/";
		final String castFolder = "smeltery/casts/";
		final String armorFolder = "armor/building/";
		final String armorRepairFolder = "armor/repair/";

		for (final IMaterialData material : Materials.MATERIALS) {
			final String materialModID = material.getModId();
			for (final IMaterialIngredient ingredient : material.getExtraData().getMaterialIngredients()) {
				if (materialModID != null) {
					materialRecipe(
							withCondition(recipe,
									new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS,
											new ModLoadedCondition(materialModID))),
							material.getID(), ItemNameIngredient.from(), ingredient.getValue(), ingredient.getNeeded(), materialFolder);
				}
			}
		}
	}

}
