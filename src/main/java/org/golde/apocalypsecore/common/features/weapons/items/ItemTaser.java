package org.golde.apocalypsecore.common.features.weapons.items;

import java.util.List;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
import org.golde.apocalypsecore.common.items._ACItemRightClickToKeepActive;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTaser extends _ACItemRightClickToKeepActive {

	public ItemTaser() {
		super("taser");
		this.maxStackSize = 1;
		this.addPropertyOverride(new ResourceLocation("active"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}

				return isActive(stack) ? 1.0F : 0.0F;
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
	public void onActiveTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		worldIn.playSound(null, entityIn.getPosition(), FeatureWeapons.SOUND_TASER, SoundCategory.PLAYERS, 1, 1);
		worldIn.playSound(null, entityIn.getPosition(), FeatureWeapons.SOUND_TASER, SoundCategory.PLAYERS, 0.4f, itemRand.nextFloat() + 0.5f);
	}
	
	

	

}
