package org.golde.apocalypsecore.base.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.item._core._IACItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Feature {

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();
	private static HashMap<Feature, CreativeTabs> tabs = new HashMap<Feature, CreativeTabs>();

	public abstract void registerBlocks();
	public abstract void registerItems();
	public abstract ItemStack getTabIcon();

	public Feature() {
		if(getTabIcon() != null) {
			tabs.put(this, new CreativeTabs("acTab." + this.getClass().getSimpleName()) {

				@Override
				public ItemStack getTabIconItem() {
					return getTabIcon();
				}
			});
		}
	}

	protected void registerBlock(_IACBlock block) {
		if(getTabIcon() != null && block.shouldBeInCreatveTab()) {
			block.setCreativeTab(tabs.get(this));
		}

		ALL_BLOCKS.add(block);
	}

	protected void registerItem(_IACItem item) {
		if(getTabIcon() != null && item.shouldBeInCreatveTab()) {
			item.setCreativeTab(tabs.get(this));
		}
		ALL_ITEMS.add(item);
	}



	@SideOnly(Side.CLIENT)
	public abstract void bindTESR();

	public static List<_IACBlock> getALL_BLOCKS() {
		return ALL_BLOCKS;
	}

	public static List<_IACItem> getALL_ITEMS() {
		return ALL_ITEMS;
	}


}
