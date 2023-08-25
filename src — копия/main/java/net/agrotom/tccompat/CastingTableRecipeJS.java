package net.agrotom.tccompat;

import dev.latvian.kubejs.item.ingredient.IngredientJS;
import dev.latvian.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class CastingTableRecipeJS extends TinkersRecipeJS {
	// id != null && id.getPath().equals("smeltery/casting/metal/silicon_bronze/ingot_sand_cast")

	@Override
	public void create(ListJS args) {
		outputItems.add(parseResultItem(args.get(0)));
		inputItems.add(IngredientJS.of("#tconstruct:casts/multi_use/ingot"));
		json.add("fluid", createFluid(args.get(1).toString(), args.size() >= 3 ? ((Number) args.get(2)).intValue() : 144));
		json.addProperty("cooling_time", 60);
	}

	@Override
	public void deserialize() {
		outputItems.add(parseResultItem(json.get("result")));

		if (json.has("cast")) {
			inputItems.add(parseIngredientItem(json.get("cast")));
		}
	}

	public CastingTableRecipeJS cast(IngredientJS in) {
		if (inputItems.isEmpty()) {
			inputItems.add(in);
		} else {
			inputItems.set(0, in);
		}

		serializeInputs = true;
		save();
		return this;
	}

	public CastingTableRecipeJS cast(IngredientJS in, boolean consume) {
		return cast(in).consumeCast(consume);
	}

	public CastingTableRecipeJS noCast() {
		inputItems.remove(0);
		serializeInputs = true;
		save();
		return this;
	}

	public CastingTableRecipeJS multiUseCast(String cast) {
		return cast(IngredientJS.of("#tconstruct:casts/multi_use/" + cast));
	}

	public CastingTableRecipeJS singleUseCast(String cast) {
		return cast(IngredientJS.of("#tconstruct:casts/single_use/" + cast)).consumeCast();
	}

	public CastingTableRecipeJS consumeCast(boolean consume) {
		json.addProperty("cast_consumed", consume);
		save();
		return this;
	}

	public CastingTableRecipeJS consumeCast() {
		return consumeCast(true);
	}

	public CastingTableRecipeJS switchSlots() {
		json.addProperty("switch_slots", true);
		save();
		return this;
	}

	public CastingTableRecipeJS coolingTime(int t) {
		json.addProperty("cooling_time", t);
		save();
		return this;
	}

	public CastingTableRecipeJS fluid(String id, int amount) {
		json.add("fluid", createFluid(id, amount));
		save();
		return this;
	}

	@Override
	public void serialize() {
		if (serializeOutputs) {
			json.addProperty("result", outputItems.get(0).getId());
		}

		if (serializeInputs) {
			if (inputItems.isEmpty()) {
				json.remove("cast");
			} else {
				json.add("cast", inputItems.get(0).toJson());
			}
		}
	}
}
