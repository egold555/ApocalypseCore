package org.golde.apocalypsecore.item._core;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
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
		if(shouldBeInCreatveTab()) {setCreativeTab(ApocalypseCore.tab);}
		this.animation = anim;
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;


		if(returnItem == null || entityplayer == null) {
			return super.onItemUseFinish(stack, worldIn, entityLiving);
		}

		if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
		{
			if (stack.isEmpty())
			{
				return returnItem;
			}

			if (entityplayer != null)
			{
				entityplayer.inventory.addItemStackToInventory(returnItem);
			}
		}

		return stack;
	}
	
	public _ACItemFood setReturnItem(ItemStack returnItem) {
		this.returnItem = returnItem;
		return this;
	}
	
	public _ACItemFood setReturnItem(Item it) {
		this.returnItem = new ItemStack(it);
		return this;
	}

	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.valueOf(animation.name());
    }
	
}
