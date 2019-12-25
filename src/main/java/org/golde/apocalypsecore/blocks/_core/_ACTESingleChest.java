package org.golde.apocalypsecore.blocks._core;

import org.golde.apocalypsecore.init.ACBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class _ACTESingleChest extends _ACTE implements ITickable {

	public float lidAngle;
	/** The angle of the ender chest lid last tick */
	public float prevLidAngle;
	private int ticksSinceSync;
	private boolean isOpen;

	private static final String TAG_OPEN = "open";

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	@Override
	public void update()
	{

		this.prevLidAngle = this.lidAngle;
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		float f = 0.1F;

		if (isOpen && this.lidAngle == 0.0F)
		{
			double d0 = (double)i + 0.5D;
			double d1 = (double)k + 0.5D;
			if(getOpenSound() != null) {
				this.world.playSound((EntityPlayer)null, d0, (double)j + 0.5D, d1, getOpenSound(), SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
			}
		}

		if (!isOpen && this.lidAngle > 0.0F || isOpen && this.lidAngle < 1.0F)
		{
			float f2 = this.lidAngle;

			if (isOpen)
			{
				this.lidAngle += 0.1F;
			}
			else
			{
				this.lidAngle -= 0.1F;
			}

			if (this.lidAngle > 1.0F)
			{
				this.lidAngle = 1.0F;
			}

			float f1 = 0.5F;

			if (this.lidAngle < 0.5F && f2 >= 0.5F)
			{
				double d3 = (double)i + 0.5D;
				double d2 = (double)k + 0.5D;
				if(getCloseSound() != null) {
					this.world.playSound((EntityPlayer)null, d3, (double)j + 0.5D, d2, getCloseSound(), SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
				}
			}

			if (this.lidAngle < 0.0F)
			{
				this.lidAngle = 0.0F;
			}
		}
	}

	public SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_ENDERCHEST_OPEN;
	}

	public SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_ENDERCHEST_CLOSE;
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void invalidate()
	{
		this.updateContainingBlockInfo();
		super.invalidate();
	}


	public void openChest()
	{
		isOpen = true;
		sendUpdates();
	}

	public void closeChest()
	{
		isOpen = false;
		sendUpdates();
	}

	public void toggleChest() {
		if(isOpen) {
			closeChest();
		}
		else {
			openChest();
		}
	}

	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		if(nbt.hasKey(TAG_OPEN)) {
			isOpen = nbt.getBoolean(TAG_OPEN);
		}
	}

	@Override
	public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		nbt.setBoolean(TAG_OPEN, isOpen);
		return nbt;
	}

}
