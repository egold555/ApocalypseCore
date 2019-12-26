package org.golde.apocalypsecore.common.features.drugs;

import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.drugs.item.syringe.ItemSyringeEmpty;
import org.golde.apocalypsecore.common.features.drugs.item.syringe.ItemSyringeFull;
import org.golde.apocalypsecore.common.features.drugs.item.syringe.SyringeLiquidColor;

import net.minecraft.item.ItemStack;

public class FeatureDrugs extends Feature {

	public static ItemSyringeEmpty syringeEmpty;
	public static ItemSyringeEmpty syringeFull;

	@Override
	public void registerItems() {
		registerItem(syringeEmpty = new ItemSyringeEmpty());
		registerItem(syringeFull = new ItemSyringeFull());
	}
	
	@Override
	public void registerItemColorHandlers() {
		registerItemColorHandler(new SyringeLiquidColor(), syringeFull);
	}

	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(syringeEmpty);
	}

}
