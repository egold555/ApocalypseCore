package org.golde.apocalypsecore.common.features.weapons.client.events;

import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.client.render.util.EntityGlowlyThing;
import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID, value = Side.CLIENT)
public class NightVisionGoggleEvent {

	static boolean isShaderActive = false;
	static List<Integer> glowingMobs = new ArrayList<Integer>();
	static List<Integer> glowingMobsLast = new ArrayList<Integer>();

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {

			if(isWearingNightVisionGoggles()) {

				if(!isShaderActive) {
					isShaderActive = true;

					Minecraft.getMinecraft().player.playSound(FeatureWeapons.SOUND_NIGHT_VISION_ON, 1, 1);
					
					Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation(ApocalypseCore.MODID, "shaders/post/night_vision.json"));
				}

				
				
				Minecraft mc = Minecraft.getMinecraft();
				int expand = 20;
				List<EntityMob> mobs = Minecraft.getMinecraft().world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(
						mc.player.posX - 1, 
						mc.player.posY - 1, 
						mc.player.posZ - 1, 
						mc.player.posX + 1, 
						mc.player.posY + 1, 
						mc.player.posZ + 1
						).grow(expand));

				glowingMobs.clear();
				for(EntityMob mob : mobs) {
					//System.out.println("glowi");
					EntityGlowlyThing.setColor(mob, TextFormatting.GREEN);
					if(!glowingMobs.contains(mob.getEntityId())) {
						glowingMobs.add(mob.getEntityId());
					}

				}

				for(Integer id : glowingMobsLast) {
					if(!glowingMobs.contains(id)) {
						Entity en = mc.world.getEntityByID(id);
						if(en != null && en instanceof EntityMob) {
							EntityGlowlyThing.removeGlow((EntityMob)en);
						}
					}
				}

				glowingMobsLast = new ArrayList<Integer>(glowingMobs);



			}
			else {
				if(isShaderActive) {
					isShaderActive = false;
					//remove shader
					Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
					Minecraft.getMinecraft().player.playSound(FeatureWeapons.SOUND_NIGHT_VISION_OFF, 1, 1);
					for(Integer id : glowingMobsLast) {
						Entity en = Minecraft.getMinecraft().world.getEntityByID(id);
						if(en != null) {
							EntityGlowlyThing.removeGlow((EntityMob)en);
						}
						
					}
				}
			}



		}


	}

	@SubscribeEvent
	public static void onPotionOverlayEvent(final RenderGameOverlayEvent.Pre event) {

		//remove potion HUD when wearing night vision
		if(isWearingNightVisionGoggles()) {
			if (event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
				event.setCanceled(true);
			}
		}
	}

	private static boolean isWearingNightVisionGoggles() {
		EntityPlayer p = Minecraft.getMinecraft().player;

		if(p == null || Minecraft.getMinecraft().world == null) {
			return false;
		}

		ItemStack is = p.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		if(is == null) {
			return false;
		}

		if(ItemStack.areItemsEqual(is, new ItemStack(FeatureWeapons.nightVisionGoggles))) {
			return true;
		}

		return false;
	}

}
