package net.agrotom.tccompat.data.providers;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.json.MaterialJson.Redirect;

public class MaterialDataProvider extends AbstractMaterialDataProvider {

	public MaterialDataProvider(@Nonnull DataGenerator gen) {
		super(gen);
	}

	@Override
	public String getName() {
		return "TC Compat Materials";
	}

	@Override
	protected void addMaterials() {
		Materials.MATERIALS.stream().map(material -> material.getB()).forEach(visitor -> visitor.accept(this));
	}
	
	@Override
	public void addMaterial(MaterialId location, int tier, int order, boolean craftable, int color, boolean hidden,
			ICondition condition, Redirect... redirect) {
		super.addMaterial(location, tier, order, craftable, color, hidden, condition, redirect);
	}

}
