package org.golde.apocalypsecore.blocks.chest.loot;

import org.golde.apocalypsecore.blocks._core._ACTESingleChest;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class TIleEntityLootChest extends _ACTESingleChest {

	@Override
	public SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_ANVIL_BREAK;
	}
	
	@Override
	public SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_END_PORTAL_FRAME_FILL;
	}
	
	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		super.openChest();
	}
	
	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		super.closeChest();
	}
	
}
