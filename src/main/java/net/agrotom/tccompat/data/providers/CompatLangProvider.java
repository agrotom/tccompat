package net.agrotom.tccompat.data.providers;

import java.util.function.Function;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class CompatLangProvider extends LanguageProvider {

	public CompatLangProvider(DataGenerator gen, String modid, String locale) {
		super(gen, modid, locale);
	}

	@Override
	public String getName() {
		return "TC Compat Language Provider";
	} 
	
	@Override
	protected void addTranslations() {
		final String FLUID = "fluid." + Main.MOD_ID + ".molten_";
		final Function<String, String> ITEM = name -> "item." + Main.MOD_ID + "." + name + "_bucket";
		final String MATERIAL = "material." + Main.MOD_ID + ".";
		
		Materials.MATERIALS.stream().map(material -> material.getA().getName()).forEach(name -> {
			final String capitalized = name.substring(0, 2).toUpperCase() + name.substring(1);
			
			super.add(FLUID + name, "Molten " + capitalized);
			super.add(ITEM.apply(name), "Molten " + capitalized + " Bucket");
			super.add(MATERIAL + name, capitalized);
		});
	}

}
