package net.agrotom.tccompat.events;

import net.agrotom.tccompat.Main;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

	@SubscribeEvent
	public static void playerInteract(PlayerInteractEvent.RightClickBlock event) {
		
	}

}
