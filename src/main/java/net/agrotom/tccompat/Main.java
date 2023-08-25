package net.agrotom.tccompat;

import net.agrotom.tccompat.data.providers.BucketModelProvider;
import net.agrotom.tccompat.data.providers.CompatBlockTagProvider;
import net.agrotom.tccompat.data.providers.CompatFluidTagProvider;
import net.agrotom.tccompat.data.providers.CompatItemTagProvider;
import net.agrotom.tccompat.data.providers.CompatLangProvider;
import net.agrotom.tccompat.data.providers.MaterialDataProvider;
import net.agrotom.tccompat.data.providers.MaterialRecipes;
import net.agrotom.tccompat.data.providers.MaterialRenderInfo;
import net.agrotom.tccompat.data.providers.MaterialSpriteProvider;
import net.agrotom.tccompat.data.providers.MaterialStatsDataProvider;
import net.agrotom.tccompat.data.providers.MaterialTraitsDataProvider;
import net.agrotom.tccompat.init.CompatBlocks;
import net.agrotom.tccompat.init.CompatFluids;
import net.agrotom.tccompat.init.CompatItems;
import net.agrotom.tccompat.init.Materials;
import net.agrotom.tccompat.util.FastRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
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

	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		MinecraftForge.EVENT_BUS.register(this);
		
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		FastRegistry.registerMaterials();
		
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
			
			final ExistingFileHelper helper = event.getExistingFileHelper();
			
			dataGenerator.addProvider(new CompatItemTagProvider(dataGenerator,
					new CompatBlockTagProvider(dataGenerator, MOD_ID, helper), helper));
			
			dataGenerator.addProvider(new CompatFluidTagProvider(dataGenerator, MOD_ID, helper));
		}
		
		if (event.includeClient()) {
			final MaterialSpriteProvider sprites = new MaterialSpriteProvider();
			dataGenerator.addProvider(new MaterialRenderInfo(dataGenerator, sprites));
			dataGenerator.addProvider(new BucketModelProvider(dataGenerator, MOD_ID, event.getExistingFileHelper()));
			dataGenerator.addProvider(new CompatLangProvider(dataGenerator, MOD_ID, "en_us"));
		}
	}

	private void setup(final FMLCommonSetupEvent event) {
		Materials.MATERIALS.clear();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {

	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void processIMC(final InterModProcessEvent event) {

	}
}
