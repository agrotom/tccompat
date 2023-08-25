package net.agrotom.tccompat.data.materials.extra;

public enum IngredientType {
	ITEM,
	TAG;
	
	public String lowerCaseName() {
		return name().toLowerCase();
	}
}
