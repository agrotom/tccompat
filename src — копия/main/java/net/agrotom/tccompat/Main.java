package net.agrotom.tccompat;

import net.agrotom.tccompat.data.materials.MaterialDataProvider;
import net.agrotom.tccompat.data.materials.MaterialRecipes;
import net.agrotom.tccompat.data.materials.MaterialRenderInfo;
import net.agrotom.tccompat.data.materials.MaterialSpriteProvider;
import net.agrotom.tccompat.data.materials.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.materials.MaterialTraitsDataProvider;
import net.agrotom.tccompat.init.CompatBlocks;
import net.agrotom.tccompat.init.CompatFluids;
import net.agrotom.tccompat.init.CompatItems;
import net.agrotom.tccompat.util.FastRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

@Mod(Main.MOD_ID)
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class Main {
	
	public static final String MOD_ID = "tccompat";
	
	{
		
		
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
	}
	
	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		MinecraftForge.EVENT_BUS.register(this);
		
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		FastRegistry.registerMaterials();
		
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
		
		//final DataGenerator generator = new DataGenerator("D:/Desktop/MyMiscellaneus/tcompat/src/main/resources/", false);
		
		//generator.generate(MOD_ID);
		
		CompatFluids.FLUIDS.register(bus);
		CompatBlocks.BLOCKS.register(bus);
		CompatItems.ITEMS.register(bus);
	}
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final net.minecraft.data.DataGenerator dataGenerator = event.getGenerator();
		
		if (event.includeServer()) {
			
			final AbstractMaterialDataProvider materials = new MaterialDataProvider(dataGenerator);
			
			dataGenerator.addProvider(materials);
			dataGenerator.addProvider(new MaterialStatsDataProvider(dataGenerator, materials));
			dataGenerator.addProvider(new MaterialTraitsDataProvider(dataGenerator, materials));
			dataGenerator.addProvider(new MaterialRecipes(dataGenerator));
		}
		
		if (event.includeClient()) {
			final MaterialSpriteProvider sprites = new MaterialSpriteProvider();
			dataGenerator.addProvider(new MaterialRenderInfo(dataGenerator, sprites));
		}
	}

	private void setup(final FMLCommonSetupEvent event) {
		
	}

	private void doClientStuff(final FMLClientSetupEvent event) {

	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void processIMC(final InterModProcessEvent event) {

	}
}
