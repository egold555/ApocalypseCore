package org.golde.apocalypsecore.common.features.drugs.item.syringe;

import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.items._ACItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSyringeEmpty extends _ACItem {

	public ItemSyringeEmpty() {
		this("syringe_empty");
		this.setMaxDamage(0);
		setMaxStackSize(16);
	}
	
	public ItemSyringeEmpty(String name) {
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		player.attackEntityFrom(FeatureDrugs.DAMAGE_SOURCE_INJECTION, 1.0F);
		worldIn.playSound(null, player.getPosition(), FeatureDrugs.SOUND_SYRINGE, SoundCategory.PLAYERS, 1, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
		return super.onItemRightClick(worldIn, player, handIn);
	}

}
