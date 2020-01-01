package org.golde.apocalypsecore.common.features.thirst;

import net.minecraft.item.ItemStack;

public class Drink implements IDrinkable {

	public ItemStack item;
	public int replenish;
	public float saturation;
	public boolean poison;
	public float poisonChance;
	
	public Drink(ItemStack item, int rep, float sat, boolean poisonable, float chance) {
		this.item = item;
		this.replenish = rep;
		this.saturation = sat;
		this.poison = poisonable;
		this.poisonChance = chance;
	}

	@Override
	public ItemStack getItem() {
		return item;
	}

	@Override
	public int getReplenish() {
		return replenish;
	}

	@Override
	public float getSaturation() {
		return saturation;
	}
	
	@Override
	public boolean isPoison() {
		return poison;
	}
	
	@Override
	public float getPoisonChance() {
		return poisonChance;
	}
	
	
	
}
