package org.golde.apocalypsecore.common.events;

import java.util.LinkedHashMap;

import org.golde.apocalypsecore.common.features.weapons.items.ItemTaser;
import org.golde.apocalypsecore.common.init.ACPotions;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class TaserStun {

	private static LinkedHashMap<Integer, Integer> entitiesBeingTazed = new LinkedHashMap<Integer, Integer>();

	private static boolean freezeClient = false;
	private static boolean reset = false;

	private static int keyBindForward;
	private static int keyBindBack;
	private static int keyBindLeft;
	private static int keyBindRight;
	private static int keyBindJump;
	private static int keyBindSneak;

	@SubscribeEvent
	public static void onPlayerinteract(EntityInteract e) {
		if(e.getSide() == Side.CLIENT) {
			return;
		}
		if(e.getItemStack().getItem() instanceof ItemTaser && e.getTarget() instanceof EntityLivingBase) {

			EntityLivingBase l = (EntityLivingBase)e.getTarget();
			//l.addPotionEffect(new PotionEffect(ACPotions.STUNNED, 20));
			entitiesBeingTazed.put(l.getEntityId(), 0);
		}
	}

	@SubscribeEvent
	public static void onTick(WorldTickEvent e) {
				if(e.side == Side.CLIENT) {
					return;
				}
		/*

		 */

		World world = e.world;
		for(int id : entitiesBeingTazed.keySet()) {
			if(entitiesBeingTazed.get(id) > 20) {
				Entity en = world.getEntityByID(id);
				if(en.getEntityId() == Minecraft.getMinecraft().player.getEntityId()) {
					//unfreeze client keys and reset the keys
					freezeClient = false;
					reset = true;
				}
				entitiesBeingTazed.remove(id);
				return;
			}
			Entity en = world.getEntityByID(id);
			if(en != null) {
				if(en instanceof EntityLivingBase) {
					EntityLivingBase l = (EntityLivingBase)en;
					l.attackEntityFrom(DamageSource.GENERIC, 4);
					if(l instanceof EntityPlayerMP) {
						//shaking animation
						EntityPlayerMP p = (EntityPlayerMP)l;
						p.connection.sendPacket(new SPacketAnimation(l, 1));
					}
					else {
						//freeze mobs
						l.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 256, true, false));
						l.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 256, true, false));
					}
					l.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20, 1, true, false));
					l.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20, 100, true, false));
					l.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,100, 1, true, false));

					l.setVelocity(0, 0, 0);
					if(l.posX != l.prevPosX || l.posY != l.prevPosY || l.posZ != l.prevPosZ) {
						l.setPositionAndUpdate(l.prevPosX, l.prevPosY, l.prevPosZ);
					}

					if(l.getEntityId() == Minecraft.getMinecraft().player.getEntityId()) {
						//freeze client keys
						freezeClient = true;
					}

				}
			}
			entitiesBeingTazed.put(id, (entitiesBeingTazed.get(id) + 1));
		}
	}

	@SubscribeEvent
	public static void onClientTick(ClientTickEvent e) {

		/*
		 Basically this s a very hacked together way of freezing the client so they cant move
		 We disallow the use of WASD [SHIFT] [SPACE] by setting the keybinds to NONE
		 Then after we unfreze the client, we set them back to thir origional keybinds
		 */
		if(e.type == Type.CLIENT) {

			if(!reset && !freezeClient) {
				keyBindForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode();
				keyBindBack = Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode();
				keyBindLeft = Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode();
				keyBindRight = Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode();
				keyBindJump = Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();
				keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode();
			}


			if(freezeClient) {
				//set keybinds to 0 aka NONE
				Minecraft.getMinecraft().gameSettings.keyBindForward.setKeyCode(0);
				Minecraft.getMinecraft().gameSettings.keyBindBack.setKeyCode(0);
				Minecraft.getMinecraft().gameSettings.keyBindLeft.setKeyCode(0);
				Minecraft.getMinecraft().gameSettings.keyBindRight.setKeyCode(0);
				Minecraft.getMinecraft().gameSettings.keyBindJump.setKeyCode(0);
				Minecraft.getMinecraft().gameSettings.keyBindSneak.setKeyCode(0);
				KeyBinding.resetKeyBindingArrayAndHash();
			}
			else {
				if(reset) {
					//set the keybinds
					reset = false;
					Minecraft.getMinecraft().gameSettings.keyBindForward.setKeyCode(keyBindForward);
					Minecraft.getMinecraft().gameSettings.keyBindBack.setKeyCode(keyBindBack);
					Minecraft.getMinecraft().gameSettings.keyBindLeft.setKeyCode(keyBindLeft);
					Minecraft.getMinecraft().gameSettings.keyBindRight.setKeyCode(keyBindRight);
					Minecraft.getMinecraft().gameSettings.keyBindJump.setKeyCode(keyBindJump);
					Minecraft.getMinecraft().gameSettings.keyBindSneak.setKeyCode(keyBindSneak);
					KeyBinding.resetKeyBindingArrayAndHash();
				}
				

			}

		}


	}

}
