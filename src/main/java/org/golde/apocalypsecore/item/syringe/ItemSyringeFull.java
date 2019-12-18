package org.golde.apocalypsecore.item.syringe;

import java.awt.Color;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemSyringeFull extends ItemSyringeEmpty{

	public ItemSyringeFull() {
		super("syringe_full");
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab == getCreativeTab()) {
			for(int i = 0; i < 255; i++) {
				float hue = i / 255F;
				Color c = Color.getHSBColor(hue, 1, 1);
				ItemStack is = new ItemStack(this);
				setItemStackColor(is, c);
				items.add(is);
			}
		}
		
	}
	
	public static void setItemStackColor(ItemStack stack, Color color)
	{
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setInteger("color", color.getRGB());
		stack.setTagCompound(tag);
	}

}
