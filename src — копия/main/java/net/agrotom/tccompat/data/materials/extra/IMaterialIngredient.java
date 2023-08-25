package net.agrotom.tccompat.data.materials.extra;

import java.util.List;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

public interface IMaterialIngredient {
	MaterialId getMaterial();
	
	String getName();
	
	List<IIngredient> getIngredients();
	IIngredient getLeftover();
	
	int getValue();
	int getNeeded();
}
