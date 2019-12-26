package org.golde.apocalypsecore.blocks.chest.loot;

import org.golde.apocalypsecore.blocks._core._ACTESingleChest;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.WorldServer;

public class TIleEntityLootChest extends _ACTESingleChest {

	@Override
	public SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_ENDERCHEST_OPEN;
	}

	@Override
	public SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_CHEST_CLOSE;
	}
	
	int ticks = 0;
	int breakanim = 0;

	@Override
	public void openChest() {
		super.openChest();

		world.spawnEntity(getEntityItem(new ItemStack(Items.DIAMOND), "" + System.currentTimeMillis()));

	}
	
	@Override
	public void closeChest() {
		super.closeChest();
		System.out.println("close chest");
		breakanim = 1;
	}
	
	@Override
	public void update() {
		super.update();
		
		if(world.isRemote) {
			return;
		}
		
		if(isOpen()) {
			ticks++;
			
			if(ticks > 20*5) {
				closeChest();
			}
		}
		else {
			if(breakanim > 0) {
				if(lidAngle == 0) {
					breakanim++;
				}
				
				if(breakanim > 10) {
				
					((WorldServer)world).spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, false, pos.getX(), pos.getY(), pos.getZ(), 1, 0, 0, 0, 1, new int[0]);
					((WorldServer)world).playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1, 1);
					
					world.setBlockToAir(getPos());
					ticks = 0;
					breakanim = -1;
					world.removeTileEntity(getPos());
				}
			}
		}
	}

	private EntityItem getEntityItem(ItemStack in, String name) {
		EntityItem item = new EntityItem(world, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() +0.5, in);
		item.motionX = 0;
		item.motionY = 0.3;
		item.motionZ = 0;
		item.noClip = true;
		item.setCustomNameTag(name);
		item.setAlwaysRenderNameTag(true);
		item.setPickupDelay(30);
		return item;
	}

	

}
