package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.blocks._core._ACBlockWithTE;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ACBlocks {

	public static _ACBlock exampleBlock;
	public static BlockBarbedWire barbedWire;
	

	private static List<_ACBlock> ALL_BLOCKS = new ArrayList<_ACBlock>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(exampleBlock = new _ACBlock("example_block"));
		event.getRegistry().register(barbedWire = new BlockBarbedWire());
		
		
		populateAllBlocksArray();

		registerTileEntities();
	}

	private static void registerTileEntities() {
		for(_ACBlock block : ALL_BLOCKS) {

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

	private static void registerTE(_ACBlockWithTE<?> block) {
		GameRegistry.registerTileEntity(block.getTileEntityClass(), block.getRegistryName());
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {

		for(_ACBlock block : ALL_BLOCKS) {
			if(block.shouldRegisterItem()) {
				block.initModel();
			}
		}

		//		exampleBlock.initModel();
		//		tickChanger.initModel();
		//		tickChanger2.initModel();
		//		tickChanger3.initModel();
		//		enhancedObsidian.initModel();
		//		weatherDetector.initModel();
	}

	@SideOnly(Side.CLIENT)
	public static void bindTESR() {
		
	}

	private static void populateAllBlocksArray(){

		for (Field field : ACBlocks.class.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {

				try {
					ALL_BLOCKS.add((_ACBlock) field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}

	}

	public static List<_ACBlock> getAllBlocksReflection() {
		return ALL_BLOCKS;
	}

}