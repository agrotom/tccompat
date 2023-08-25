package net.agrotom.tccompat.data.materials;

import net.agrotom.tccompat.util.IModifierSupplier;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.TinkerModifiers;

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
		Materials.MATERIALS.forEach(material -> {
			final IModifierSupplier[] modifierSuppliers = material.getModifiers();
			
			for (int i = 0; i < modifierSuppliers.length; i++) {
				super.addDefaultTraits(material.getID(), modifierSuppliers[i].get());
			}
		});
		
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
		
		super.addDefaultTraits(Materials.REALMITE, TinkerModifiers.autosmelt.get());
	}
	
	@Override
	public void addDefaultTraits(MaterialId location, Modifier... traits) {
		super.addDefaultTraits(location, traits);
	}

}
