package org.golde.apocalypsecore.client.events;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.items._ACItemBowAnimation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID, value = Side.CLIENT)
public class ItemBowAnimationEvent {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onLivingRender(RenderLivingEvent.Pre event) {


		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player1 = (EntityPlayer) event.getEntity();
			ModelPlayer model1 = (ModelPlayer) event.getRenderer().getMainModel();
			ItemStack stack1;

			if (player1.getHeldItemMainhand() != null) {
				stack1 = player1.getHeldItemMainhand();
				if (stack1.getItem() instanceof _ACItemBowAnimation && ((_ACItemBowAnimation) stack1.getItem()).raiseArms) {
					if (Minecraft.getMinecraft().gameSettings.mainHand == EnumHandSide.RIGHT) {
						if (player1.getHeldItemMainhand() == stack1) {
							model1.rightArmPose = ArmPose.BOW_AND_ARROW;
						}
					} 
					else if (player1.getHeldItemMainhand() == stack1) {
						model1.leftArmPose = ArmPose.BOW_AND_ARROW;
					}
				}
			}

			if (player1.getHeldItemOffhand() != null) {
				stack1 = player1.getHeldItemOffhand();
				if (stack1.getItem() instanceof _ACItemBowAnimation && ((_ACItemBowAnimation) stack1.getItem()).raiseArms) {
					if (Minecraft.getMinecraft().gameSettings.mainHand == EnumHandSide.RIGHT) {
						if (player1.getHeldItemOffhand() == stack1) {
							model1.leftArmPose = ArmPose.BOW_AND_ARROW;
						}
					} 
					else if (player1.getHeldItemOffhand() == stack1) {
						model1.rightArmPose = ArmPose.BOW_AND_ARROW;
					}
				}
			}
		}

	}

}
