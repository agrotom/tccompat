package net.agrotom.tccompat.init;

import net.agrotom.tccompat.Main;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_REALMITE = BLOCKS
            .register("molten_realmite_block", () -> new FlowingFluidBlock(CompatFluids.REALMITE_STILL,
                    Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 15; }).randomTicks().strength(100.0F).noDrops()));

}
