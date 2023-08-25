package net.agrotom.tccompat.init;

import javax.annotation.Nonnull;

import net.agrotom.tccompat.Main;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompatItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

	public static final RegistryObject<Item> REALMITE_BUCKET = ITEMS.register("realmite_bucket",
			() -> new BucketItem(CompatFluids.REALMITE_STILL, getDefaultProperties("divinerpg")));
			
	public static Item.Properties getDefaultProperties(@Nonnull String modid) {
		return new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModList.get().isLoaded(modid) ? ItemGroup.TAB_MISC : null);
	}
}
