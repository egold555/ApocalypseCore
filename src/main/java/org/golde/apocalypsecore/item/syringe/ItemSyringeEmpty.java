package org.golde.apocalypsecore.item.syringe;

import org.golde.apocalypsecore.ACDamage;
import org.golde.apocalypsecore.ACTabs;
import org.golde.apocalypsecore.item._core._ACItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSyringeEmpty extends _ACItem {

	public ItemSyringeEmpty() {
		this("syringe_empty");
		this.setMaxDamage(0);
		setMaxStackSize(16);
		setCreativeTab(ACTabs.MISC); //its in building for some reason?!
	}
	
	public ItemSyringeEmpty(String name) {
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		player.attackEntityFrom(ACDamage.INJECTION, 1.0F);
		return super.onItemRightClick(worldIn, player, handIn);
	}

}
