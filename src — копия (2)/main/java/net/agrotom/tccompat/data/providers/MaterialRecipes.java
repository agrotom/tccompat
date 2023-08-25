package net.agrotom.tccompat.data.providers;

import java.util.function.Consumer;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.tools.data.material.MaterialRecipeProvider;

public class MaterialRecipes extends MaterialRecipeProvider
		implements IConditionBuilder, IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, IRecipeHelper {

	public final String MATERIAL_FOLDER = "tools/materials/";
	public final String MELTING_FOLDER = "smeltery/melting/";
	
	private Consumer<IFinishedRecipe> recipe = null;
	
	public MaterialRecipes(DataGenerator generator) {
		super(generator);
	}

	@Override
	public String getName() {
		return "TC Compat Material Recipes Provider";
	}
	
	@Override
	public String getModId() {
		return Main.MOD_ID;
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipe) {
		final String castingFolder = "smeltery/casting/metal/";
		
		final String alloyFolder = "smeltery/alloys/";
		
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
		
		this.recipe = recipe;
		
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
	}
	
	@Override
	public Consumer<IFinishedRecipe> withCondition(Consumer<IFinishedRecipe> consumer, ICondition... conditions) {
		return super.withCondition(consumer, conditions);
	}
	
	public Consumer<IFinishedRecipe> getRecipe() {
		return recipe;
	}

}
