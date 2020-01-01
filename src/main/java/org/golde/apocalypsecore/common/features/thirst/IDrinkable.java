package org.golde.apocalypsecore.common.features.thirst;

import net.minecraft.item.ItemStack;

public interface IDrinkable {

	public ItemStack getItem();
	
	public default float getPoisonChance() {
		return 0.0F;
	}
	
	public int getReplenish();
	
	public float getSaturation();
	
	public default boolean isPoison() {
		return false;
	}
	
}
