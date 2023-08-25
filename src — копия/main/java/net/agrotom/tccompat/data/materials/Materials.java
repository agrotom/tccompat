package net.agrotom.tccompat.data.materials;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.agrotom.tccompat.data.materials.extra.IngredientType;
import net.agrotom.tccompat.data.materials.extra.impl.ExtraData;
import net.agrotom.tccompat.data.materials.extra.impl.Ingredient;
import net.agrotom.tccompat.data.materials.extra.impl.MaterialIngredient;
import net.agrotom.tccompat.data.materials.impl.MaterialData;
import net.agrotom.tccompat.data.materials.impl.MaterialVisitor;
import net.agrotom.tccompat.util.FastList;
import net.agrotom.tccompat.util.FastTuple;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class Materials {

	public static final List<IMaterialData> MATERIALS = new ArrayList<IMaterialData>();
	public static final List<IMaterialVisitor> MATERIALS_VISITORS = new ArrayList<IMaterialVisitor>();
	
	public static final MaterialId REALMITE = id("realmite");
	
	public static MaterialId id(@Nonnull String id) {
		return new MaterialId(Main.MOD_ID, id);
	}
	
	/*FastRegistry.registerMaterial("rupee", 1, 4, false, 0x03ADFC, false,
	new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("divinerpg")), new IMaterialStats[] {
			new HeadMaterialStats(1500, 8.5F, 5, 5.0F),
			new HandleMaterialStats(0.8F, 1.1F, 1.25F, 1.05F),
			new ExtraMaterialStats()
	}, () -> new Modifier[] {
			TinkerModifiers.autosmelt.get()
	}, new ExtraData(DataBuilder.builder().mod("divinerpg").headDurability(1500).headMiningSpeed(8.5F)
			.headHarvestLevel(5).headAttackDamage(5.0F).handleDurability(0.8F).handleMiningSpeed(1.1F)
			.handleAttackSpeed(1.25F).handleAttackDamage(1.05F).extraDurability(250).type(CraftingType.MELTING)
			.modifiers(FastList.of(FastTuple.of("tconstruct:autosmelt", 1)))));*/
	
	/*public static final IMaterialData RUPEE = register(MaterialData.builder(id("rupee"), ExtraData.builder().headDurability(1500).headMiningSpeed(8.5F)
			.headHarvestLevel(5).headAttackDamage(5.0F).handleDurability(0.8F).handleMiningSpeed(1.1F).handleAttackSpeed(1.25F).handleAttackDamage(1.05F)
			.extraDurability(250).modifiers(FastList.of(FastTuple.of("autosmelt", 1)))
			.materialIngredients(MaterialIngredient.builder("ingot").value(1).needed(1)
					.ingredients(Ingredient.create("divinerpg:rupee_ingot", IngredientType.ITEM)).build()).build())
			
			.tier(2).order(0).craftable(false).color(0x03ADFC).hidden(false).autoStats(true).modCondition("divinerpg").build());*/
	
	public static final IMaterialVisitor RUPEE_VISITOR = register(new MaterialVisitor(id("rupee"),
			(material, provider) -> {
				provider.addMaterial(material, 2, 0, false, 0x03ADFC, false, createModCondition());
			},
			(material, provider) -> {
				provider.builder(material).fallbacks("metal").meleeHarvest().colorMapper(GreyToColorMapping.builderFromBlack().addABGR(129, 0x03ADFC).build());
			},
			(material, provider) -> {
				provider.addMaterialStats(material, createHead(1500, 8.5F, 5, 5.0F), createHandle(0.8F, 1.1F, 1.25F, 1.05F), createExtra());
			},
			(material, provider) -> {
				provider.addDefaultTraits(material, TinkerModifiers.antiaquatic.get());
			},
			(material, provider) -> {
				
			},
			(material, provider) -> {
				provider.builder(material).color(0x03ADFC).build();
			}));
	
	public static IMaterialStats createHead(int durability, float miningSpeed, int harvestLevel, float attackDamage) {
		return new HeadMaterialStats(durability, miningSpeed, harvestLevel, attackDamage);
	}
	
	public static IMaterialStats createHandle(float durability, float miningSpeed, float attackSpeed, float attackDamage) {
		return new HandleMaterialStats(durability, miningSpeed, attackSpeed, attackDamage);
	}
	
	public static IMaterialStats createExtra() {
		return new ExtraMaterialStats();
	}
	
	public static ICondition createModCondition() {
		return new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("divinerpg")); 
	}
	
	/*@Deprecated
	public static IMaterialData register(@Nonnull String id, int tier, int order, boolean craftable, int color, boolean hidden, @Nullable ICondition condition,
			@Nonnull IMaterialStats[] stats, @Nonnull IModifierSupplier[] modifiers, @Nonnull IExtraData extraData) {
		final IMaterialData material = new MaterialData(new MaterialId(Main.MOD_ID, id),
				tier, order, craftable, color, hidden, condition, stats, modifiers, extraData);
		
		MATERIALS.add(material);
		
		return material;
	}*/
	
	public static IMaterialData register(@Nonnull IMaterialData material) {
		MATERIALS.add(material);
		
		return material;
	}
	
	public static IMaterialVisitor register(@Nonnull IMaterialVisitor material) {
		MATERIALS_VISITORS.add(material);
		
		return material;
	}

}
