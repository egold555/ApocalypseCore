package org.golde.apocalypsecore.common.features.thirst.client.events;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.thirst.DrinkRegistry;
import org.golde.apocalypsecore.common.features.thirst.IDrinkable;
import org.golde.apocalypsecore.common.features.thirst.client.ClientStats;
import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.server.PacketMovement;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID, value = Side.CLIENT)
public class ClientThirstEvents {

	@SubscribeEvent
	public static void onClientTick(PlayerTickEvent event) {
		if( FMLClientHandler.instance().getClientPlayerEntity() != null && event.player != null) {
			ClientStats.getInstance().movementSpeed = getMovementStat(event.player);
			ACPacketHandler.NETWORK.sendToServer(new PacketMovement());
		}
		
	}
	
	public static int getMovementStat(EntityPlayer player) {
		double d = player.posX - player.prevPosX;
		double d1 = player.posY - player.prevPosY;
		double d2 = player.posZ - player.prevPosZ;
		return Math.round(MathHelper.sqrt((d * d) + (d1 * d1) + (d2 * d2)) * 100F);
	}
	
	@SubscribeEvent
	public static void onToolTIpRender(ItemTooltipEvent e) {
		
		if(e.getItemStack() == null || e.getItemStack().getItem() == null) {
			return;
		}
		
		for(IDrinkable d : DrinkRegistry.getDrinks()) {
			if(ItemStack.areItemStacksEqual(d.getItem(), e.getItemStack())) {
				e.getToolTip().add(1, "Restores " + d.getReplenish() + " thirst");
				if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) {
					if(d.isPoison()) {
						e.getToolTip().add(2, d.getPoisonChance() + "% chance of getting poisoned");
					}
				}
			}
		}
		
	}
	
}
