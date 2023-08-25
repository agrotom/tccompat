package net.agrotom.tccompat.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.data.materials.IDataVisitors;
import net.agrotom.tccompat.data.materials.IMaterialData;
import net.agrotom.tccompat.data.materials.impl.DataVisitors;
import net.agrotom.tccompat.data.materials.impl.MaterialData;
import net.agrotom.tccompat.data.materials.impl.RegistryData;
import net.agrotom.tccompat.util.DataGenHelper;
import net.agrotom.tccompat.util.FastTuple;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class Materials {

	public static final List<Tuple<IMaterialData, IDataVisitors>> MATERIALS = new ArrayList<>();

	static {
		IMaterialData rupee = MaterialData.builder("rupee").modid("divinerpg").color(0x03ADFC)
				.registry(RegistryData.builder().tags(FastTuple.of(ItemTags.bind("forge:ingots/rupee"), "divinerpg:rupee_ingot")).build()).build();
		IDataVisitors rupeeVisitors = DataVisitors.builder(rupee).data((parent, provider) -> {
			provider.addMaterial(rupee.getID(), 2, AbstractMaterialDataProvider.ORDER_GENERAL, false, parent.getColor(), false,
					createModCondition(parent));
		}).sprite((parent, provider) -> {
			provider.builder(parent.getID()).meleeHarvest().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
					.addABGR(100, parent.getColor()).addABGR(102, parent.getColor()).build());
		}).stats((parent, provider) -> {
			provider.addMaterialStats(parent.getID(), createHead(1500, 8.5F, 5, 5.0F), createHandle(0.8F, 1.1F, 1.25F, 1.05F), createExtra());
		}).traits((parent, provider) -> {
			provider.addDefaultTraits(parent.getID(), TinkerModifiers.antiaquatic.get());
		}).render((parent, provider) -> {
			provider.builder(parent.getID()).color(parent.getColor()).build();
		}).recipes((parent, provider) -> {
			final DataGenHelper helper = new DataGenHelper(parent.getID(), provider);
			
			helper.createDefaultMaterialRecipes(null, "divinerpg:rupee_ingot", "divinerpg:rupee_block");
		}).build();
		
		register(rupee, rupeeVisitors);
	
	}

	public static IMaterialData register(@Nonnull IMaterialData data, @Nonnull IDataVisitors visitors) {
		
		MATERIALS.add(FastTuple.of(data, visitors));
		
		return data;
	}

	public static IMaterialStats createHead(int durability, float miningSpeed, int harvestLevel, float attackDamage) {
		return new HeadMaterialStats(durability, miningSpeed, harvestLevel, attackDamage);
	}

	public static IMaterialStats createHandle(float durability, float miningSpeed, float attackSpeed,
			float attackDamage) {
		return new HandleMaterialStats(durability, miningSpeed, attackSpeed, attackDamage);
	}

	public static IMaterialStats createExtra() {
		return new ExtraMaterialStats();
	}

	public static ICondition createModCondition(@Nonnull IMaterialData material) {
		return new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition(material.getModID()));
	}

}
