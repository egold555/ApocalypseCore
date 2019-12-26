package org.golde.apocalypsecore.base.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.item._core._IACItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Feature {

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();
	private static HashMap<Feature, CreativeTabs> tabs = new HashMap<Feature, CreativeTabs>();
	private static HashMap<IItemColor, Item[]> colorMapItem = new HashMap<IItemColor, Item[]>();
	private static HashMap<IItemColor, Block[]> colorMapBlock = new HashMap<IItemColor, Block[]>();

	public void registerBlocks() {};
	public void registerItems() {};
	public abstract ItemStack getTabIcon();
	@SideOnly(Side.CLIENT)
	public void bindTESR() {};
	public void registerItemColorHandlers() {};

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

	protected final void registerBlock(_IACBlock block) {
		if(getTabIcon() != null && block.shouldBeInCreatveTab()) {
			block.setCreativeTab(tabs.get(this));
		}

		ALL_BLOCKS.add(block);
	}

	protected final void registerItem(_IACItem item) {
		if(getTabIcon() != null && item.shouldBeInCreatveTab()) {
			item.setCreativeTab(tabs.get(this));
		}
		ALL_ITEMS.add(item);
	}

	protected final void registerItemColorHandler(IItemColor key, Item... item) {
		this.colorMapItem.put(key, item);
	}

	protected final void registerItemColorHandler(IItemColor key, Block... block) {
		this.colorMapBlock.put(key, block);
	}

	

	public static final List<_IACBlock> getALL_BLOCKS() {
		return ALL_BLOCKS;
	}

	public static final List<_IACItem> getALL_ITEMS() {
		return ALL_ITEMS;
	}

	public static final HashMap<IItemColor, Block[]> getColorMapBlock() {
		return colorMapBlock;
	}
	
	public static final HashMap<IItemColor, Item[]> getColorMapItem() {
		return colorMapItem;
	}

}
