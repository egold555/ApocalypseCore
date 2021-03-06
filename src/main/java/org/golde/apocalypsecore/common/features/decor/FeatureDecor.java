package org.golde.apocalypsecore.common.features.decor;

import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.decor.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.common.features.decor.blocks.BlockCagedLampOn;
import org.golde.apocalypsecore.common.features.decor.items.ItemSprayPaint;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class FeatureDecor extends Feature {

	public static BlockCagedLampOn lamp_white;
	public static BlockCagedLampOn lamp_orange;
	public static BlockCagedLampOn lamp_magenta;
	public static BlockCagedLampOn lamp_light_blue;
	public static BlockCagedLampOn lamp_yellow;
	public static BlockCagedLampOn lamp_lime;
	public static BlockCagedLampOn lamp_pink;
	public static BlockCagedLampOn lamp_gray;
	public static BlockCagedLampOn lamp_silver;
	public static BlockCagedLampOn lamp_cyan;
	public static BlockCagedLampOn lamp_purple;
	public static BlockCagedLampOn lamp_blue;
	public static BlockCagedLampOn lamp_brown;
	public static BlockCagedLampOn lamp_green;
	public static BlockCagedLampOn lamp_red;
	public static BlockCagedLampOn lamp_black;
	
	public static BlockBarbedWire barbedWire;
	
	public static final DamageSource DAMAGE_SOURCE_BARBED_WIRE = new DamageSource("barbedWire").setDamageBypassesArmor().setDifficultyScaled();

	public static ItemSprayPaint sprayPaint;
	
	@Override
	public void registerBlocks() {
		registerBlock(lamp_white = new BlockCagedLampOn(EnumDyeColor.WHITE));
		registerBlock(lamp_orange = new BlockCagedLampOn(EnumDyeColor.ORANGE));
		registerBlock(lamp_magenta = new BlockCagedLampOn(EnumDyeColor.MAGENTA));
		registerBlock(lamp_light_blue = new BlockCagedLampOn(EnumDyeColor.LIGHT_BLUE));
		registerBlock(lamp_yellow = new BlockCagedLampOn(EnumDyeColor.YELLOW));
		registerBlock(lamp_lime = new BlockCagedLampOn(EnumDyeColor.LIME));
		registerBlock(lamp_pink = new BlockCagedLampOn(EnumDyeColor.PINK));
		registerBlock(lamp_gray = new BlockCagedLampOn(EnumDyeColor.GRAY));
		registerBlock(lamp_silver = new BlockCagedLampOn(EnumDyeColor.SILVER));
		registerBlock(lamp_cyan = new BlockCagedLampOn(EnumDyeColor.CYAN));
		registerBlock(lamp_purple = new BlockCagedLampOn(EnumDyeColor.PURPLE));
		registerBlock(lamp_blue = new BlockCagedLampOn(EnumDyeColor.BLUE));
		registerBlock(lamp_brown = new BlockCagedLampOn(EnumDyeColor.BROWN));
		registerBlock(lamp_green = new BlockCagedLampOn(EnumDyeColor.GREEN));
		registerBlock(lamp_red = new BlockCagedLampOn(EnumDyeColor.RED));
		registerBlock(lamp_black = new BlockCagedLampOn(EnumDyeColor.BLACK));
		
		registerBlock(barbedWire = new BlockBarbedWire());
	}
	
	@Override
	public void registerItems() {
		registerItem(sprayPaint = new ItemSprayPaint());
	}

	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(barbedWire);
	}
	
}
