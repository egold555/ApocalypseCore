package org.golde.apocalypsecore.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ACTabs {

	public static final ACTab MISC = new ACTab();
	public static final ACTabFood FOOD = new ACTabFood();
	public static final ACTabWeapons WEAPONS = new ACTabWeapons();
	public static final ACTabDrugs DRUGS = new ACTabDrugs();

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
	
	private static class ACTabWeapons extends CreativeTabs {

		public ACTabWeapons() {
			super("acTabWeapons");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ACItems._Weapons.smokeBomb);
		}

	}
	
	private static class ACTabDrugs extends CreativeTabs {

		public ACTabDrugs() {
			super("acTabDrugs");
		}

		@Override
		public ItemStack getTabIconItem() {
			ItemStack it = new ItemStack(ACItems.syringeEmpty);

			return it;
		}

	}

}
