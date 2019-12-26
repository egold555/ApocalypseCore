package org.golde.apocalypsecore.base.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.blocks._core._ACBlockWithTE;
import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.features.building.FeatureBuilding;
import org.golde.apocalypsecore.item._core._IACItem;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class FeatureRegistration {

	private static List<Feature> features = new ArrayList<Feature>();
	
	
	static {
		features.add(new FeatureBuilding());
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		
		for(Feature f : features) {
			f.registerBlocks();
			
		}
		
		for(_IACBlock b : Feature.getALL_BLOCKS()) {
			event.getRegistry().register((Block)b);
		}
		registerTileEntities();
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		
		for(Feature f : features) {
			f.registerItems();
		}
		
		for(_IACBlock block : Feature.getALL_BLOCKS()) {
			if(block != null && block.shouldRegisterItem()) {
				
				event.getRegistry().register(getItem(block));
			}
		}

		for(_IACItem item : Feature.getALL_ITEMS()) {
			event.getRegistry().register((Item)item);
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public static final void initModels() {

		for(_IACBlock block : Feature.getALL_BLOCKS()) {
			if(block.shouldRegisterItem()) {
				block.initModel();
			}
		}
		
		for(_IACItem item : Feature.getALL_ITEMS()) {
			item.initModel();
		}

	}
	
	public static void bindTESR() {
		for(Feature f : features) {
			f.bindTESR();
		}
	}
	
	private static final void registerTileEntities() {
		for(_IACBlock block : Feature.getALL_BLOCKS()) {

			if(block == null) {
				System.err.println("Null block detected. Check assignmentss");
				continue;
			}

			if(block instanceof _ACBlockWithTE) {
				_ACBlockWithTE<?> blockTE = (_ACBlockWithTE<?>)block;
				GameRegistry.registerTileEntity(blockTE.getTileEntityClass(), blockTE.getRegistryName());
			}
		}
	}
	
	private static ItemBlock getItem(_IACBlock iblock) {
		return getItem((Block)iblock); //BAD but it works
	}
	
	private static ItemBlock getItem(Block block) {
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		return ib;
	}
	
}
