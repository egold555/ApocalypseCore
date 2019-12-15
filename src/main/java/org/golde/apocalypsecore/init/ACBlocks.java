package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.blocks.BlockCagedLight;
import org.golde.apocalypsecore.blocks.BlockCagedLight2;
import org.golde.apocalypsecore.blocks.BlockCagedOn;
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
	
	public static BlockCagedOn lamp_white;
	public static BlockCagedOn lamp_orange;
	public static BlockCagedOn lamp_magenta;
	public static BlockCagedOn lamp_light_blue;
	public static BlockCagedOn lamp_yellow;
	public static BlockCagedOn lamp_lime;
	public static BlockCagedOn lamp_pink;
	public static BlockCagedOn lamp_gray;
	public static BlockCagedOn lamp_silver;
	public static BlockCagedOn lamp_cyan;
	public static BlockCagedOn lamp_purple;
	public static BlockCagedOn lamp_blue;
	public static BlockCagedOn lamp_brown;
	public static BlockCagedOn lamp_green;
	public static BlockCagedOn lamp_red;
	public static BlockCagedOn lamp_black;
	
	private static List<_ACBlock> ALL_BLOCKS = new ArrayList<_ACBlock>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(exampleBlock = new _ACBlock("example_block"));
		event.getRegistry().register(barbedWire = new BlockBarbedWire());
		
		event.getRegistry().register(lamp_white = new BlockCagedOn(EnumDyeColor.WHITE));
		event.getRegistry().register(lamp_orange = new BlockCagedOn(EnumDyeColor.ORANGE));
		event.getRegistry().register(lamp_magenta = new BlockCagedOn(EnumDyeColor.MAGENTA));
		event.getRegistry().register(lamp_light_blue = new BlockCagedOn(EnumDyeColor.LIGHT_BLUE));
		event.getRegistry().register(lamp_yellow = new BlockCagedOn(EnumDyeColor.YELLOW));
		event.getRegistry().register(lamp_lime = new BlockCagedOn(EnumDyeColor.LIME));
		event.getRegistry().register(lamp_pink = new BlockCagedOn(EnumDyeColor.PINK));
		event.getRegistry().register(lamp_gray = new BlockCagedOn(EnumDyeColor.GRAY));
		event.getRegistry().register(lamp_silver = new BlockCagedOn(EnumDyeColor.SILVER));
		event.getRegistry().register(lamp_cyan = new BlockCagedOn(EnumDyeColor.CYAN));
		event.getRegistry().register(lamp_purple = new BlockCagedOn(EnumDyeColor.PURPLE));
		event.getRegistry().register(lamp_blue = new BlockCagedOn(EnumDyeColor.BLUE));
		event.getRegistry().register(lamp_brown = new BlockCagedOn(EnumDyeColor.BROWN));
		event.getRegistry().register(lamp_green = new BlockCagedOn(EnumDyeColor.GREEN));
		event.getRegistry().register(lamp_red = new BlockCagedOn(EnumDyeColor.RED));
		event.getRegistry().register(lamp_black = new BlockCagedOn(EnumDyeColor.BLACK));

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
