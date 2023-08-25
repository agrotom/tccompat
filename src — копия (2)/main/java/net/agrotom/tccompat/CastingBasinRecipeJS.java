package net.agrotom.tccompat;

import dev.latvian.kubejs.util.ListJS;

/**
 * @author LatvianModder
 */
public class CastingBasinRecipeJS extends CastingTableRecipeJS {
	@Override
	public void create(ListJS args) {
		super.create(args);
		json.add("fluid", createFluid(args.get(1).toString(), args.size() >= 3 ? ((Number) args.get(2)).intValue() : 1296));
		json.addProperty("cooling_time", 180);
	}
}
