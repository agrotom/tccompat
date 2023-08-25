package net.agrotom.tccompat.data.providers;

import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class MaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {

	public MaterialTraitsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
		super(gen, materials);
	}

	@Override
	public String getName() {
		return "TC Compat Stats";
	}

	@Override
	protected void addMaterialTraits() {
		Materials.MATERIALS.stream().map(material -> material.getB()).forEach(visitor -> visitor.accept(this));
	}
	
	@Override
	public void addDefaultTraits(MaterialId location, Modifier... traits) {
		super.addDefaultTraits(location, traits);
	}

}
