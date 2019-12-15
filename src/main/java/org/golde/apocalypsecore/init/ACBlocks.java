package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.blocks.BlockColoredLamp;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.blocks._core._ACBlockWithTE;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
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
	
	public static BlockColoredLamp lamp_white;
	public static BlockColoredLamp lamp_orange;
	public static BlockColoredLamp lamp_magenta;
	public static BlockColoredLamp lamp_light_blue;
	public static BlockColoredLamp lamp_yellow;
	public static BlockColoredLamp lamp_lime;
	public static BlockColoredLamp lamp_pink;
	public static BlockColoredLamp lamp_gray;
	public static BlockColoredLamp lamp_silver;
	public static BlockColoredLamp lamp_cyan;
	public static BlockColoredLamp lamp_purple;
	public static BlockColoredLamp lamp_blue;
	public static BlockColoredLamp lamp_brown;
	public static BlockColoredLamp lamp_green;
	public static BlockColoredLamp lamp_red;
	public static BlockColoredLamp lamp_black;
	
	public static BlockColoredLamp lamp_white_caged;
	public static BlockColoredLamp lamp_orange_caged;
	public static BlockColoredLamp lamp_magenta_caged;
	public static BlockColoredLamp lamp_light_blue_caged;
	public static BlockColoredLamp lamp_yellow_caged;
	public static BlockColoredLamp lamp_lime_caged;
	public static BlockColoredLamp lamp_pink_caged;
	public static BlockColoredLamp lamp_gray_caged;
	public static BlockColoredLamp lamp_silver_caged;
	public static BlockColoredLamp lamp_cyan_caged;
	public static BlockColoredLamp lamp_purple_caged;
	public static BlockColoredLamp lamp_blue_caged;
	public static BlockColoredLamp lamp_brown_caged;
	public static BlockColoredLamp lamp_green_caged;
	public static BlockColoredLamp lamp_red_caged;
	public static BlockColoredLamp lamp_black_caged;
	
	private static List<_ACBlock> ALL_BLOCKS = new ArrayList<_ACBlock>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(exampleBlock = new _ACBlock("example_block"));
		event.getRegistry().register(barbedWire = new BlockBarbedWire());
		
		event.getRegistry().register(lamp_white = new BlockColoredLamp(EnumDyeColor.WHITE, false));
		event.getRegistry().register(lamp_orange = new BlockColoredLamp(EnumDyeColor.ORANGE, false));
		event.getRegistry().register(lamp_magenta = new BlockColoredLamp(EnumDyeColor.MAGENTA, false));
		event.getRegistry().register(lamp_light_blue = new BlockColoredLamp(EnumDyeColor.LIGHT_BLUE, false));
		event.getRegistry().register(lamp_yellow = new BlockColoredLamp(EnumDyeColor.YELLOW, false));
		event.getRegistry().register(lamp_lime = new BlockColoredLamp(EnumDyeColor.LIME, false));
		event.getRegistry().register(lamp_pink = new BlockColoredLamp(EnumDyeColor.PINK, false));
		event.getRegistry().register(lamp_gray = new BlockColoredLamp(EnumDyeColor.GRAY, false));
		event.getRegistry().register(lamp_silver = new BlockColoredLamp(EnumDyeColor.SILVER, false));
		event.getRegistry().register(lamp_cyan = new BlockColoredLamp(EnumDyeColor.CYAN, false));
		event.getRegistry().register(lamp_purple = new BlockColoredLamp(EnumDyeColor.PURPLE, false));
		event.getRegistry().register(lamp_blue = new BlockColoredLamp(EnumDyeColor.BLUE, false));
		event.getRegistry().register(lamp_brown = new BlockColoredLamp(EnumDyeColor.BROWN, false));
		event.getRegistry().register(lamp_green = new BlockColoredLamp(EnumDyeColor.GREEN, false));
		event.getRegistry().register(lamp_red = new BlockColoredLamp(EnumDyeColor.RED, false));
		event.getRegistry().register(lamp_black = new BlockColoredLamp(EnumDyeColor.BLACK, false));
		
		event.getRegistry().register(lamp_white_caged = new BlockColoredLamp(EnumDyeColor.WHITE, true));
		event.getRegistry().register(lamp_orange_caged = new BlockColoredLamp(EnumDyeColor.ORANGE, true));
		event.getRegistry().register(lamp_magenta_caged = new BlockColoredLamp(EnumDyeColor.MAGENTA, true));
		event.getRegistry().register(lamp_light_blue_caged = new BlockColoredLamp(EnumDyeColor.LIGHT_BLUE, true));
		event.getRegistry().register(lamp_yellow_caged = new BlockColoredLamp(EnumDyeColor.YELLOW, true));
		event.getRegistry().register(lamp_lime_caged = new BlockColoredLamp(EnumDyeColor.LIME, true));
		event.getRegistry().register(lamp_pink_caged = new BlockColoredLamp(EnumDyeColor.PINK, true));
		event.getRegistry().register(lamp_gray_caged = new BlockColoredLamp(EnumDyeColor.GRAY, true));
		event.getRegistry().register(lamp_silver_caged = new BlockColoredLamp(EnumDyeColor.SILVER, true));
		event.getRegistry().register(lamp_cyan_caged = new BlockColoredLamp(EnumDyeColor.CYAN, true));
		event.getRegistry().register(lamp_purple_caged = new BlockColoredLamp(EnumDyeColor.PURPLE, true));
		event.getRegistry().register(lamp_blue_caged = new BlockColoredLamp(EnumDyeColor.BLUE, true));
		event.getRegistry().register(lamp_brown_caged = new BlockColoredLamp(EnumDyeColor.BROWN, true));
		event.getRegistry().register(lamp_green_caged = new BlockColoredLamp(EnumDyeColor.GREEN, true));
		event.getRegistry().register(lamp_red_caged = new BlockColoredLamp(EnumDyeColor.RED, true));
		event.getRegistry().register(lamp_black_caged = new BlockColoredLamp(EnumDyeColor.BLACK, true));

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
