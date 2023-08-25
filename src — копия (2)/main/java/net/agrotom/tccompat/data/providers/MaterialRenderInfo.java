package net.agrotom.tccompat.data.providers;

import net.agrotom.tccompat.init.Materials;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialRenderInfo extends AbstractMaterialRenderInfoProvider {

	public AbstractMaterialRenderInfoProvider.RenderInfoBuilder builder;
	
	public MaterialRenderInfo(DataGenerator gen, AbstractMaterialSpriteProvider materialSprites) {
		super(gen, materialSprites);
	}

	@Override
	public String getName() {
		return "TC Compat Material Render Info";
	}

	@Override
	protected void addMaterialRenderInfo() {
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
	}

	public MaterialRenderInfo builder(MaterialId materialId) {
		builder = super.buildRenderInfo(materialId);
		
		return this;
	}
	
	public MaterialRenderInfo color(int color) {
		builder.color(color);
		
		return this;
	}
	
	public void build() {
		builder.build();
	}
	
}
