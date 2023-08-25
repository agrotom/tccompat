package net.agrotom.tccompat.data.materials;

import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.IColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.ISpriteTransformer;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

public class MaterialSpriteProvider extends AbstractMaterialSpriteProvider {
	
	public MaterialSpriteInfoBuilder builder;
	
	@Override
	public String getName() {
		return "TC Compat Material Sprite Provider";
	}
	
	@Override
	protected void addAllMaterials() {
		Materials.MATERIALS_VISITORS.forEach(material -> {
			material.accept(this);
		});
	}
	
	public MaterialSpriteProvider builder(ResourceLocation name) {
		builder = super.buildMaterial(name);
		
		return this;
	}
	
	public MaterialSpriteProvider fallbacks(String... fallbacks) {
		builder.fallbacks(fallbacks);
		
		return this;
	}
	
	public MaterialSpriteProvider transformer(ISpriteTransformer transformer) {
		builder.transformer(transformer);
		
		return this;
	}
	
	public MaterialSpriteProvider meleeHarvest() {
		builder.meleeHarvest();
		
		return this;
	}
	
	public MaterialSpriteProvider colorMapper(IColorMapping mapping) {
		builder.colorMapper(mapping);
		
		return this;
	}
	
	public MaterialSpriteProvider statType(MaterialStatsId statId) {
		builder.statType(statId);
		
		return this;
	}
 
}
