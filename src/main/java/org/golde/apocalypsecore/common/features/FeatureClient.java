package org.golde.apocalypsecore.common.features;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureClient {

	private static HashMap<IItemColor, Item[]> colorMapItem = new HashMap<IItemColor, Item[]>();
	private static HashMap<IItemColor, Block[]> colorMapBlock = new HashMap<IItemColor, Block[]>();
	
	@SideOnly(Side.CLIENT)
	public void regsterEntityRenderers() {};
	
	@SideOnly(Side.CLIENT)
	public void bindTESR() {};
	
	@SideOnly(Side.CLIENT)
	public void registerItemColorHandlers() {};
	
	protected static final void registerItemColorHandler(IItemColor key, Item... item) {
		colorMapItem.put(key, item);
	}

	protected static final void registerItemColorHandler(IItemColor key, Block... block) {
		colorMapBlock.put(key, block);
	}
	
	public static final HashMap<IItemColor, Block[]> getColorMapBlock() {
		return colorMapBlock;
	}

	public static final HashMap<IItemColor, Item[]> getColorMapItem() {
		return colorMapItem;
	}
	
}
