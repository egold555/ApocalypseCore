package org.golde.apocalypsecore.common.features.weapons.items;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
import org.golde.apocalypsecore.common.items._ACItemArmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemNightVisionGoggles extends _ACItemArmor {

	private static final ArmorMaterial NIGHT_VIION_MATERIAL = EnumHelper.addArmorMaterial("nightVisionGoggles", ApocalypseCore.MODID + ":night_vision_goggles", 10, new int[]{1, 0, 0, 0}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0);

	public ItemNightVisionGoggles() {
		super("night_vision_goggles", NIGHT_VIION_MATERIAL, 1, EntityEquipmentSlot.HEAD);
	}


	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack stack) {
		return stack.getItem() == Items.IRON_INGOT;
	} 

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (itemStack.getItem() == FeatureWeapons.nightVisionGoggles) {
			if (player.getActivePotionEffect(MobEffects.NIGHT_VISION) == null || player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= 200)
				player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5, 0, true, true));
		}
	} 

}
