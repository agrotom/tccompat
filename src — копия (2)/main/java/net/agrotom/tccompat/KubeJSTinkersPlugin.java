package net.agrotom.tccompat;

import dev.latvian.kubejs.KubeJSPlugin;
import dev.latvian.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.util.ResourceLocation;

public class KubeJSTinkersPlugin extends KubeJSPlugin {
	@Override
	public void addRecipes(RegisterRecipeHandlersEvent event) {
		event.register(new ResourceLocation("tconstruct:casting_table"), CastingTableRecipeJS::new);
		event.register(new ResourceLocation("tconstruct:casting_basin"), CastingBasinRecipeJS::new);
	}
	
	@Override
	public void initStartup() {
		
	}
}