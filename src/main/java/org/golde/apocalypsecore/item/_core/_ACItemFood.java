package org.golde.apocalypsecore.item._core;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class _ACItemFood extends ItemFood implements _IACItem {

	public static enum EnumAnimation {
		EAT, DRINK
	}

	ItemStack returnItem = null;
	EnumAnimation animation;

	public _ACItemFood(String name, EnumAnimation anim) {
		this(name, anim, 0, 0f);
	}

	public _ACItemFood(String name, EnumAnimation anim, int amount) {
		this(name, anim, amount, 0.6f);
	}

	public _ACItemFood(String name, EnumAnimation anim, int amount, float saturation) {
		super(amount, saturation, false);
		setRegistryName(name);
		setUnlocalizedName(ApocalypseCore.MODID + "." + name);
		this.animation = anim;

		if(anim == EnumAnimation.DRINK) {
			setAlwaysEdible();
		}
	}

	public _ACItemFood setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

		if (entityplayer != null)
		{
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
			}
			
			if(!entityplayer.capabilities.isCreativeMode) {
				stack.shrink(1);
			}
		}


		if (returnItem != null && (entityplayer == null || !entityplayer.capabilities.isCreativeMode))
		{
			if (stack.isEmpty())
			{
				return returnItem.copy();
			}

			if (entityplayer != null)
			{
				boolean succ = entityplayer.inventory.addItemStackToInventory(returnItem.copy());
				System.out.println(succ);
			}
		}

		return stack;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (playerIn.canEat(false) || animation == EnumAnimation.DRINK)
		{
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		else
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}

	public _ACItemFood setReturnItem(ItemStack returnItem) {
		this.returnItem = returnItem;
		return this;
	}

	public _ACItemFood setReturnItem(Item it) {
		this.returnItem = new ItemStack(it);
		return this;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.valueOf(animation.name());
	}



}
