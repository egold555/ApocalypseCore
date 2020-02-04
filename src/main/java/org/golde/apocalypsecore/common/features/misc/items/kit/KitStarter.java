package org.golde.apocalypsecore.common.features.misc.items.kit;

import org.golde.apocalypsecore.common.features.building.FeatureBuilding;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;

import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class KitStarter extends AbstractKit {

	@Override
	public String getName() {
		return "Starter";
	}
	
	@Override
	public ItemStack getHelmet() {
		return genericArmor(Items.IRON_HELMET, "Helmet");
	}
	
	@Override
	public ItemStack getChestplate() {
		return genericArmor(Items.IRON_CHESTPLATE, "Chestplate");
	}
	
	@Override
	public ItemStack getLeggings() {
		return genericArmor(Items.IRON_LEGGINGS, "Leggings");
	}
	
	@Override
	public ItemStack getBoots() {
		return genericArmor(Items.IRON_BOOTS, "Boots");
	}

	@Override
	public ItemStack[] getItems() {
		
		ItemStack sword = generic(Items.IRON_SWORD, "Sword");
		sword.addEnchantment(Enchantments.SHARPNESS, 1);
		
		return new ItemStack[] {
				genericTool(Items.IRON_PICKAXE, "Pickaxe"),
				genericTool(Items.IRON_AXE, "Axe"),
				genericTool(Items.IRON_SHOVEL, "Shovel"),
				sword,
				new ItemStack(Items.COOKED_CHICKEN, 32),
				new ItemStack(Blocks.TORCH, 64),
				new ItemStack(FeatureWeapons.grapplingHook),
				new ItemStack(FeatureBuilding.barbedWire, 32),
		};
	}

	private ItemStack generic(Item item, String name) {
		ItemStack helmet = new ItemStack(item);
		helmet.addEnchantment(Enchantments.UNBREAKING, 1);
		helmet.setStackDisplayName(TextFormatting.LIGHT_PURPLE + "Starter " + name);
		return helmet;
	}
	
	private ItemStack genericArmor(Item item, String name) {
		ItemStack toReturn = generic(item, name);
		toReturn.addEnchantment(Enchantments.PROTECTION, 1);
		return toReturn;
	}
	
	private ItemStack genericTool(Item item, String name) {
		ItemStack toReturn = generic(item, name);
		toReturn.addEnchantment(Enchantments.EFFICIENCY, 1);
		return toReturn;
	}
}
