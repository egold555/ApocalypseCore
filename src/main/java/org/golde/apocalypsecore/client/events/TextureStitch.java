package org.golde.apocalypsecore.client.events;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID, value = Side.CLIENT)
public class TextureStitch {

	public static TextureAtlasSprite SMOKE_SPRITE;
	
	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event)
	{
		ResourceLocation resource = new ResourceLocation(ApocalypseCore.MODID, "particles/smoke");

		SMOKE_SPRITE = event.getMap().registerSprite(resource);
	}
	
}
