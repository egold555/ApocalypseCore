package org.golde.apocalypsecore.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class _ACItemBowAnimation extends _ACItem {

	public boolean raiseArms = false;
	
	public _ACItemBowAnimation(String name) {
		super(name);
		
	}

	/*
	 * CLIENT SIDE CODE CALLED ON SERVER
	 * FIX ASAP
	 
	 */
	
	//TODO: FIX CLIENT SIDE CODE ON SERVER
	@Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);
        EntityPlayer player = (EntityPlayer) entity;

        if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && (isSelected || player.getHeldItem(EnumHand.OFF_HAND) == stack)) {
            this.raiseArms = true;
        } 
        else if (!Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown()) {
            this.raiseArms = false;
        }

    }
	
}
