package org.golde.apocalypsecore.blocks.chest.loot;

import org.golde.apocalypsecore.blocks._core._ACTESingleChest;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;

public class TIleEntityLootChest extends _ACTESingleChest {

	@Override
	public SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_ENDERCHEST_OPEN;
	}

	@Override
	public SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_CHEST_CLOSE;
	}

	@Override
	public void openChest() {
		super.openChest();
		// TODO Auto-generated method stub
		EntityItem item = new EntityItem(world, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() +0.5, new ItemStack(Items.DIAMOND));
		item.motionX = 0;
		item.motionY = 0.3;
		item.motionZ = 0;
		item.noClip = true;
		item.setCustomNameTag(TextFormatting.RED + "Hello!");
		item.setAlwaysRenderNameTag(true);
		item.setPickupDelay(30);
		world.spawnEntity(item);

	}

	@Override
	public void closeChest() {
		super.closeChest();
	}

}
