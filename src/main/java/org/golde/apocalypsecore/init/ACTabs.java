package org.golde.apocalypsecore.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ACTabs {

	public static final ACTabDrugs DRUGS = new ACTabDrugs();
	
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
