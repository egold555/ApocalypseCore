package org.golde.apocalypsecore.item.gun;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GunAimable extends GunBase
{
	
	/**
	 * 
	 * @param name
	 * @param tab
	 * @param fireRate
	 * @param ammocap
	 * @param reloadtm
	 * @param recoil
	 * @param bulletDamage
	 * @param bulletDuration
	 * @param ammunition
	 * @param guntype
	 * @param desc
	 * @param ammoN
	 * @param strength
	 */
	public GunAimable(String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype, String desc, String ammoN, int strength) 
	{
		//String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype
		super(name, fireRate, ammocap, reloadtm, recoil, bulletDamage, bulletDuration, ammunition, guntype, desc, ammoN, strength);
		setMaxDamage(clipsize);
	}
	
	@Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		
		ItemStack itemstack = playerIn.getHeldItemMainhand();
		if(!this.isRunning(itemstack))
		{
			this.shootGun(worldIn, playerIn, itemstack);
			if(itemstack.getItemDamage() != this.clipsize)
			{
				this.doRecoil(playerIn);
				this.playShootSound(playerIn);
			}
			playerIn.addStat(StatList.getObjectUseStats(this));
			this.checkStates(itemstack, worldIn, playerIn);
		}
		
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		EntityPlayer p = (EntityPlayer) entityIn;
		NBTTagCompound nbt = stack.getTagCompound();
		this.checkStates(stack, worldIn, entityIn);
		
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		if(this.isAiming(itemstack))
		{
			return false;
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) 
	{
		EntityPlayer playerIn = (EntityPlayer) entityLiving;

		this.doAim(stack);
		
		return true;
	}

}