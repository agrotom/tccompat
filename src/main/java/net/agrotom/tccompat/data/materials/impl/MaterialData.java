package net.agrotom.tccompat.data.materials.impl;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.materials.IMaterialData;
import net.agrotom.tccompat.data.materials.IRegistryData;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialData implements IMaterialData {

	private final String name;
	private final MaterialId id;
	private final String modid;
	private final int color;
	
	private final IRegistryData registryData;
	
	public static Builder builder(@Nonnull String name) {
		return new Builder(name);
	}
	
	private MaterialData(@Nonnull Builder builder) {
		this.name = builder.name;
		this.id = builder.id;
		
		this.modid = builder.modid;
		this.color = builder.color;
		
		this.registryData = builder.registryData;
	}
	
	public static class Builder {
		
		private final String name;
		private final MaterialId id;
		
		private String modid = "";
		private int color = 0xFFFFFF;
		
		private IRegistryData registryData = IRegistryData.empty;
		
		private Builder(@Nonnull String name) {
			this.name = name;
			this.id = new MaterialId(Main.MOD_ID, name);
		}
		
		public Builder modid(@Nonnull String modid) {
			this.modid = modid;
			
			return this;
		}
		
		public Builder color(int color) {
			this.color = color;
			
			return this;
		}
		
		public Builder registry(@Nonnull IRegistryData data) {
			this.registryData = data;
			
			return this;
		}
		
		public IMaterialData build() {
			return new MaterialData(this);
		}
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MaterialId getID() {
		return id;
	}

	@Override
	public String getModID() {
		return modid;
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public IRegistryData getRegistryData() {
		return registryData;
	}

}
