package org.golde.apocalypsecore.common.items;

import java.awt.Color;
import java.util.List;

import org.golde.apocalypsecore.common.features.decor.items.ItemSprayPaint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class _ACItemColor extends _ACItem {

	private static final boolean DEBUG_COLORS = true;
	private static final String KEY = "color";
	
	public _ACItemColor(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(DEBUG_COLORS && hasColor(stack)) {
			Color c = new Color(getColor(stack));
			tooltip.add("R: " + c.getRed() + " G: " + c.getGreen() + " B: " + c.getBlue());
		}
	}
	
	public static ItemStack setColor(ItemStack stack, Color color)
	{
		return setColor(stack, color.getRGB());
	}
	
	public static ItemStack setColor(ItemStack stack, int color)
	{
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setInteger(KEY, color);
		stack.setTagCompound(tag);
		return stack;
	}
	
	public static boolean hasColor(ItemStack stack) {
		
		if(stack == null){
			return false;
		}
		
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();

		if(!stack.hasTagCompound() || stack.getTagCompound() == null) {
			return false;
		}
		
		return tag.hasKey(KEY);
	}
	
	public static int getColor(ItemStack is) {
		if(!hasColor(is)) {
			return -1;
		}

		return is.getTagCompound().getInteger(KEY);
	}


}
