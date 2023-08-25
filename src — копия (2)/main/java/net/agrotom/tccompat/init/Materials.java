package net.agrotom.tccompat.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.materials.IMaterialVisitor;
import net.agrotom.tccompat.data.materials.extra.IRegistryInfo;
import net.agrotom.tccompat.data.materials.extra.impl.RegistryInfo;
import net.agrotom.tccompat.data.materials.impl.MaterialVisitor;
import net.agrotom.tccompat.util.DataGenHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class Materials {
	public static final List<IMaterialVisitor> MATERIALS_VISITORS = new ArrayList<IMaterialVisitor>();
	public static final List<IRegistryInfo> MATERIALS_INFOS = new ArrayList<IRegistryInfo>();
	
	public static final MaterialId REALMITE = id("realmite");
	
	public static MaterialId id(@Nonnull String id) {
		return new MaterialId(Main.MOD_ID, id);
	}
	
	public static final IMaterialVisitor RUPEE_VISITOR = register(MaterialVisitor.builder(id("rupee"))
			.data((material, provider) -> {
				provider.addMaterial(material, 2, 0, false, 0x03ADFC, false, createModCondition("divinerpg"));
			})
			.sprite((material, provider) -> {
				provider.builder(material).fallbacks("metal").meleeHarvest().colorMapper(GreyToColorMapping.builderFromBlack().addABGR(129, 0x03ADFC).build());
			})
			.stats((material, provider) -> {
				provider.addMaterialStats(material, createHead(1500, 8.5F, 5, 5.0F), createHandle(0.8F, 1.1F, 1.25F, 1.05F), createExtra());
			})
			.traits((material, provider) -> {
				provider.addDefaultTraits(material, TinkerModifiers.antiaquatic.get());
			})
			.recipes((material, provider) -> {
				final DataGenHelper helper = new DataGenHelper(material, provider, "divinerpg");
				
				helper.createDefaultMaterialRecipes(null, "divinerpg:rupee_ingot", "divinerpg:rupee_block");
				helper.createMeltingRecipes();
				helper.createSmelteryRecipes();
			})
			.render((material, provider) -> {
				provider.builder(material).color(0x03ADFC).build();
			}).build(), RegistryInfo.builder());
	
	public static IMaterialStats createHead(int durability, float miningSpeed, int harvestLevel, float attackDamage) {
		return new HeadMaterialStats(durability, miningSpeed, harvestLevel, attackDamage);
	}
	
	public static IMaterialStats createHandle(float durability, float miningSpeed, float attackSpeed, float attackDamage) {
		return new HandleMaterialStats(durability, miningSpeed, attackSpeed, attackDamage);
	}
	
	public static IMaterialStats createExtra() {
		return new ExtraMaterialStats();
	}
	
	public static ICondition createModCondition(@Nonnull String modid) {
		return new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition(modid)); 
	}
	
	
	
	public static IMaterialVisitor register(@Nonnull IMaterialVisitor material) {
		return register(material, IRegistryInfo.defaultInfo(material.getID()));
	}
	
	public static IMaterialVisitor register(@Nonnull IMaterialVisitor material, @Nonnull IRegistryInfo info) {
		MATERIALS_VISITORS.add(material);
		MATERIALS_INFOS.add(info);
		
		return material;
	}

}
