package net.agrotom.tccompat.data.providers;

import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.data.tags.ItemTagProvider;

public class CompatItemTagProvider extends ItemTagProvider {

	public CompatItemTagProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider,
			ExistingFileHelper existingFileHelper) {
		super(generatorIn, blockTagProvider, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		Materials.MATERIALS_INFOS.forEach(info -> {
			info.getTagDefinitions().forEach(tuple -> {
				tag(tuple.getA()).addOptional(new ResourceLocation(tuple.getB()));
			});
		});
	}

}
