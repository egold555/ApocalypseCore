package org.golde.apocalypsecore.init;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ACSounds {

	public static SoundEvent NIGHT_VISION_ON;
	public static SoundEvent NIGHT_VISION_OFF;
	public static SoundEvent SYRINGE;
	public static SoundEvent TASER;
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().register(NIGHT_VISION_ON = registerSoundEvent("nightvision.on"));
		event.getRegistry().register(NIGHT_VISION_OFF = registerSoundEvent("nightvision.off"));
		event.getRegistry().register(SYRINGE = registerSoundEvent("syringe"));
		event.getRegistry().register(TASER = registerSoundEvent("taser"));
	}
	
	private static SoundEvent registerSoundEvent(String name){
		ApocalypseCore.logger.info("Registering Sound: " + name);
		ResourceLocation res = new ResourceLocation(ApocalypseCore.MODID, name);
		SoundEvent evt = new SoundEvent(res).setRegistryName(res);
		if(evt == null || res == null) {
			ApocalypseCore.logger.error("SOUND WAS NULL");
		}
		return evt;
	}
	
}
