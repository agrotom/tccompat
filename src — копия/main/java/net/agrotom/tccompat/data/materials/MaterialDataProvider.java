package net.agrotom.tccompat.data.materials;

import javax.annotation.Nonnull;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
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
		Materials.MATERIALS.forEach(material -> {
			super.addMaterial(material.getID(), material.getTier(), material.getOrder(), material.isCraftable(), material.getColor(),
					material.isHidden(), material.getCondition());
		});
		
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
		
		super.addMaterial(Materials.REALMITE, 1, ORDER_GENERAL, false, 0xFF931C, false,
				new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("divinerpg")));
	}
	
	@Override
	public void addMaterial(MaterialId location, int tier, int order, boolean craftable, int color, boolean hidden,
			ICondition condition, Redirect... redirect) {
		super.addMaterial(location, tier, order, craftable, color, hidden, condition, redirect);
	}

}
