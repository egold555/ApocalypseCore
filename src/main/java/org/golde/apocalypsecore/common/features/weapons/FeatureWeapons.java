package org.golde.apocalypsecore.common.features.weapons;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.blocks._ACBlockFluidClassic;
import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.weapons.blocks.BlockLandMine;
import org.golde.apocalypsecore.common.features.weapons.items.ItemFlamethrower;
import org.golde.apocalypsecore.common.features.weapons.items.ItemGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.items.ItemMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.items.ItemNightVisionGoggles;
import org.golde.apocalypsecore.common.features.weapons.items.ItemSmokeBomb;
import org.golde.apocalypsecore.common.features.weapons.items.ItemTaser;
import org.golde.apocalypsecore.common.items._ACItemMeleeWeapon;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FeatureWeapons extends Feature {

	public static ItemFlamethrower flamethrower;
	public static ItemSmokeBomb smokeBomb;
	public static _ACItemMeleeWeapon itemBaseBallBat;
	public static _ACItemMeleeWeapon itemBaseBallBatSpiked;
	public static ItemTaser taser;
	public static ItemMolotovCocktail molotovCocktail;
	
	public static ItemNightVisionGoggles nightVisionGoggles;
	public static ItemGrapplingHook grapplingHook;
	
	public static BlockLandMine landMine;
	
	
	
	
	@Override
	public void registerBlocks() {
		registerBlock(landMine = new BlockLandMine());
	}

	@Override
	public void registerItems() {
		registerItem(smokeBomb = new ItemSmokeBomb());
		registerItem(flamethrower = new ItemFlamethrower());
		registerItem(itemBaseBallBat = new _ACItemMeleeWeapon("bat", 3, 50, Blocks.PLANKS));
		registerItem(itemBaseBallBatSpiked = new _ACItemMeleeWeapon("bat_spiked", 6, 100, Blocks.PLANKS));
		registerItem(taser = new ItemTaser());
		registerItem(molotovCocktail = new ItemMolotovCocktail());
		
		registerItem(nightVisionGoggles = new ItemNightVisionGoggles());
		registerItem(grapplingHook = new ItemGrapplingHook());
	}

	@Override
	public ItemStack getTabIcon() {
		ItemStack is = new ItemStack(taser);
		is.setTagCompound(new NBTTagCompound()); //fix null
		is.getTagCompound().setBoolean("active", true);
		return is;
	}

}