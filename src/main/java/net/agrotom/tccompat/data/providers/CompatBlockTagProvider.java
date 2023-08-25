package net.agrotom.tccompat.data.providers;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CompatBlockTagProvider extends BlockTagsProvider {

	public CompatBlockTagProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
		super(generator, modId, existingFileHelper);
	}
	
	@Override
	public String getName() {
		return "TC Compat Block Tags Provider";
	}
	
	@Override
	protected void addTags() {
		
	}

}
