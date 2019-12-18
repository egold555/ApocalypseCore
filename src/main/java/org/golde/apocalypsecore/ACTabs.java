package org.golde.apocalypsecore;

import org.golde.apocalypsecore.init.ACBlocks;
import org.golde.apocalypsecore.init.ACItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ACTabs {

	public static final ACTab MISC = new ACTab();
	public static final ACTabBuilding BUILDING = new ACTabBuilding();
	public static final ACTabFood FOOD = new ACTabFood();
	public static final ACTabWeapons WEAPONS = new ACTabWeapons();

	private static class ACTab extends CreativeTabs {

		public ACTab() {
			super("acTab");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ACItems.wrench);
		}

	}

	private static class ACTabFood extends CreativeTabs {

		public ACTabFood() {
			super("acTabFood");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ACItems._Food.sodaorange);
		}

	}

	private static class ACTabBuilding extends CreativeTabs {

		public ACTabBuilding() {
			super("acTabBuilding");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ACBlocks.barbedWire);
		}

	}
	
	private static class ACTabWeapons extends CreativeTabs {

		public ACTabWeapons() {
			super("acTabWeapons");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ACItems._Weapons.smokeBomb);
		}

	}

}
