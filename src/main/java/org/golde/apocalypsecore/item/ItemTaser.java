package org.golde.apocalypsecore.item;

import java.util.List;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.init.ACSounds;
import org.golde.apocalypsecore.init.ACTabs;
import org.golde.apocalypsecore.item._core._ACItem;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTaser extends _ACItem {

	public ItemTaser() {
		super("taser");
		this.maxStackSize = 1;
		setCreativeTab(ACTabs.WEAPONS);
		this.addPropertyOverride(new ResourceLocation("active"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}

				NBTTagCompound nbt = checkValidTags(stack);


				return nbt.getBoolean("active") ? 1.0F : 0.0F;
			}
		});
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GOLD + "Have you ever wanted to shock somebody with 50,000 volts?");
		tooltip.add("");
		tooltip.add("When activated:");
		tooltip.add(" 4 Attack Damage");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		NBTTagCompound nbt = checkValidTags(playerIn.getHeldItem(handIn));
		nbt.setBoolean("active", true);
		nbt.setInteger("tick", 0);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		NBTTagCompound nbt = checkValidTags(stack);
		if(nbt.getInteger("tick") > 3) {
			nbt.setBoolean("active", false);
		}
		
		if(nbt.getBoolean("active")) {
			nbt.setInteger("tick", (nbt.getInteger("tick") + 1));
			worldIn.playSound(null, entityIn.getPosition(), ACSounds.TASER, SoundCategory.PLAYERS, 1, 1);
			worldIn.playSound(null, entityIn.getPosition(), ACSounds.TASER, SoundCategory.PLAYERS, 0.4f, itemRand.nextFloat() + 0.5f);
		}
		
		
		
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override 
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
    	if(slotChanged){
    		return true;
    	}
    	return false;
    }

	private NBTTagCompound checkValidTags(ItemStack is) {
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

}
