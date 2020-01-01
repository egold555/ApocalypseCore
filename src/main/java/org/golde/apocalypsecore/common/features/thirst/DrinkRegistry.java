package org.golde.apocalypsecore.common.features.thirst;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class DrinkRegistry {

	private static List<IDrinkable> DRINKS = new ArrayList<IDrinkable>();

	public static void addDrink(IDrinkable drink) {
		DRINKS.add(drink);
	}
	
	public static void addDrink(ItemStack item, int replenish, float saturation) {
		addDrink(item, replenish, saturation, false, 0.0F);
	}

	public static void addDrink(ItemStack item, int replenish, float saturation, boolean poison, float poisonChance) {
		Drink d = new Drink(item, replenish, saturation, poison, poisonChance);
		DRINKS.add(d);
	}
	
	public static List<IDrinkable> getDrinks() {
		return DRINKS;
	}
	
}
