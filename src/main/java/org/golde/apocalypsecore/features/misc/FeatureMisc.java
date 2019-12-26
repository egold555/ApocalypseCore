package org.golde.apocalypsecore.features.misc;

import org.golde.apocalypsecore.base.features.Feature;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.client.render.block.TESRLootChest2;
import org.golde.apocalypsecore.features.misc.blocks.lootchest.BlockLootChest;
import org.golde.apocalypsecore.features.misc.blocks.lootchest.TIleEntityLootChest;
import org.golde.apocalypsecore.item.ItemWrench;
import org.golde.apocalypsecore.item._core._ACItem;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class FeatureMisc extends Feature {

	public static _ACBlock exampleBlock;
	public static _ACItem exampleItem;
	public static ItemWrench wrench;
	public static BlockLootChest lootChest;

	@Override
	public void registerBlocks() {
		registerBlock(exampleBlock = new _ACBlock("example_block"));
		registerBlock(lootChest = new BlockLootChest());
	}

	@Override
	public void registerItems() {
		registerItem(exampleItem = new _ACItem("example_item"));
		registerItem(wrench = new ItemWrench());
	}

	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(wrench);
	}

	@Override
	public void bindTESR() {
		ClientRegistry.bindTileEntitySpecialRenderer(TIleEntityLootChest.class, new TESRLootChest2());
	}

}
