package net.agrotom.tccompat.data.materials;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

public interface IMaterialData {
	
	String getName();
	MaterialId getID();
	String getModID();
	int getColor();
	
	IRegistryData getRegistryData();
	
}
