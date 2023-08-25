package net.agrotom.tccompat.data.providers;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;

public class MaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

	public MaterialStatsDataProvider(@Nonnull DataGenerator gen, @Nonnull AbstractMaterialDataProvider materials) {
		super(gen, materials);
	}

	@Override
	public String getName() {
		return "TC Compat Stats";
	}

	@Override
	protected void addMaterialStats() {
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
	}
	
	@Override
	public void addMaterialStats(MaterialId location, IMaterialStats... stats) {
		super.addMaterialStats(location, stats);
	}

}
