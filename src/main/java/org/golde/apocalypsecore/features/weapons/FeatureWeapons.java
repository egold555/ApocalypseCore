package org.golde.apocalypsecore.features.weapons;

import org.golde.apocalypsecore.base.features.Feature;
import org.golde.apocalypsecore.features.weapons.blocks.BlockLandMine;
import org.golde.apocalypsecore.features.weapons.items.ItemFlamethrower;
import org.golde.apocalypsecore.features.weapons.items.ItemGrapplingHook;
import org.golde.apocalypsecore.features.weapons.items.ItemMolotovCocktail;
import org.golde.apocalypsecore.features.weapons.items.ItemNightVisionGoggles;
import org.golde.apocalypsecore.features.weapons.items.ItemSmokeBomb;
import org.golde.apocalypsecore.features.weapons.items.ItemTaser;
import org.golde.apocalypsecore.item._core._ACItemMeleeWeapon;
import org.golde.apocalypsecore.item.gun.GunAimable;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FeatureWeapons extends Feature {

	public static ItemFlamethrower flamethrower;
	public static ItemSmokeBomb smokeBomb;
	//public static GunAimable gunGlock;
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
		//registerItem(gunGlock = new GunAimable("glock", 12, 12, 50, 1, 3, 200, Items.APPLE, 1, "gun.glock.desc", "9mm Clip", 1));
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
		return new ItemStack(smokeBomb, 1, 5);
	}

	@Override
	public void bindTESR() {
		
	}

}
