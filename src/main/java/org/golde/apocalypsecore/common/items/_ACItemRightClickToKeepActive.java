package org.golde.apocalypsecore.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class _ACItemRightClickToKeepActive extends _ACItem {

	public _ACItemRightClickToKeepActive(String name) {
		super(name);
	}

	@Override 
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
    	if(slotChanged){
    		return true;
    	}
    	return false;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		NBTTagCompound nbt = checkValidTags(playerIn.getHeldItem(handIn));
		nbt.setBoolean("active", true);
		nbt.setInteger("tick", 0);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	protected static final NBTTagCompound checkValidTags(ItemStack is) {
		if(is == null) {
			return new NBTTagCompound();
		}
		NBTTagCompound nbt = is.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			is.setTagCompound(nbt);
		}
		if (!nbt.hasKey("active")) {
			nbt.setBoolean("active", false);
		}
		if (!nbt.hasKey("tick")) {
			nbt.setInteger("tick", 0);
		}
		return nbt;
	}
	
	public final boolean isActive(ItemStack is) {
		NBTTagCompound nbt = checkValidTags(is);
		return nbt.getBoolean("active");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		NBTTagCompound nbt = checkValidTags(stack);
		if(nbt.getInteger("tick") > 3) {
			nbt.setBoolean("active", false);
		}
		
		if(nbt.getBoolean("active")) {
			nbt.setInteger("tick", (nbt.getInteger("tick") + 1));
			onActiveTick(stack, worldIn, entityIn, itemSlot, isSelected);
		}
		
		
		
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	public abstract void onActiveTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected);
	
}
