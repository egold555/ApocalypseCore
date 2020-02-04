package org.golde.apocalypsecore.common.features.misc.items.kit;

import net.minecraft.item.ItemStack;

public abstract class AbstractKit {

	public static final int USE_ONCE = -1;
	
	public abstract String getName();
	public int getCooldown() {return 0;};
	
	public ItemStack getHelmet() {return null;}
	public ItemStack getChestplate() {return null;}
	public ItemStack getLeggings() {return null;}
	public ItemStack getBoots() {return null;}
	
	public abstract ItemStack[] getItems();
	
}
