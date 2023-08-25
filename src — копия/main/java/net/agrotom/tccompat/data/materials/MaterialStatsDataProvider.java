package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

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
		Materials.MATERIALS.forEach(material -> {
			super.addMaterialStats(material.getID(), material.getMaterialStats());
		});
		
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
		
		super.addMaterialStats(Materials.REALMITE,
				new HeadMaterialStats(1500, 8.5F, 5, 5.0F),
				new HandleMaterialStats(0.8F, 1.1F, 1.25F, 1.05F),
				new ExtraMaterialStats());
	}
	
	@Override
	public void addMaterialStats(MaterialId location, IMaterialStats... stats) {
		super.addMaterialStats(location, stats);
	}

}
