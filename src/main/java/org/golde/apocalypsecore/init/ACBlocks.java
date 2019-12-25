package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.blocks.BlockCagedOn;
import org.golde.apocalypsecore.blocks.BlockLandMine;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.blocks._core._ACBlockWithTE;
import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.blocks.chest.loot.BlockLootChest;
import org.golde.apocalypsecore.blocks.chest.loot.TIleEntityLootChest;
import org.golde.apocalypsecore.client.render.block.TESRLootChest;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ACBlocks {

	public static _ACBlock exampleBlock;
	public static BlockBarbedWire barbedWire;
	public static BlockLandMine landMine;

	public static BlockLootChest lootChest;

	public static class Decoration {
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
	}

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(exampleBlock = new _ACBlock("example_block"));
		event.getRegistry().register(barbedWire = new BlockBarbedWire());
		event.getRegistry().register(landMine = new BlockLandMine());
		event.getRegistry().register(lootChest = new BlockLootChest());

		event.getRegistry().register(Decoration.lamp_white = new BlockCagedOn(EnumDyeColor.WHITE));
		event.getRegistry().register(Decoration.lamp_orange = new BlockCagedOn(EnumDyeColor.ORANGE));
		event.getRegistry().register(Decoration.lamp_magenta = new BlockCagedOn(EnumDyeColor.MAGENTA));
		event.getRegistry().register(Decoration.lamp_light_blue = new BlockCagedOn(EnumDyeColor.LIGHT_BLUE));
		event.getRegistry().register(Decoration.lamp_yellow = new BlockCagedOn(EnumDyeColor.YELLOW));
		event.getRegistry().register(Decoration.lamp_lime = new BlockCagedOn(EnumDyeColor.LIME));
		event.getRegistry().register(Decoration.lamp_pink = new BlockCagedOn(EnumDyeColor.PINK));
		event.getRegistry().register(Decoration.lamp_gray = new BlockCagedOn(EnumDyeColor.GRAY));
		event.getRegistry().register(Decoration.lamp_silver = new BlockCagedOn(EnumDyeColor.SILVER));
		event.getRegistry().register(Decoration.lamp_cyan = new BlockCagedOn(EnumDyeColor.CYAN));
		event.getRegistry().register(Decoration.lamp_purple = new BlockCagedOn(EnumDyeColor.PURPLE));
		event.getRegistry().register(Decoration.lamp_blue = new BlockCagedOn(EnumDyeColor.BLUE));
		event.getRegistry().register(Decoration.lamp_brown = new BlockCagedOn(EnumDyeColor.BROWN));
		event.getRegistry().register(Decoration.lamp_green = new BlockCagedOn(EnumDyeColor.GREEN));
		event.getRegistry().register(Decoration.lamp_red = new BlockCagedOn(EnumDyeColor.RED));
		event.getRegistry().register(Decoration.lamp_black = new BlockCagedOn(EnumDyeColor.BLACK));

		populateAllBlocksArray();

		registerTileEntities();
	}

	private static void registerTileEntities() {
		for(_IACBlock block : ALL_BLOCKS) {

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

	@SideOnly(Side.CLIENT)
	public static void initModels() {

		for(_IACBlock block : ALL_BLOCKS) {
			if(block.shouldRegisterItem()) {
				block.initModel();
			}
		}

	}

	@SideOnly(Side.CLIENT)
	public static void bindTESR() {
		ClientRegistry.bindTileEntitySpecialRenderer(TIleEntityLootChest.class, new TESRLootChest());
	}

	private static void populateAllBlocksArray(){

		List<Class<?>> classes = new ArrayList<Class<?>>();

		classes.add(ACBlocks.class);

		for(Class c : ACBlocks.class.getClasses()) {
			classes.add(c);
		}

		for(Class c : classes) {
			for (Field field : c.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {

					try {
						ALL_BLOCKS.add((_IACBlock) field.get(null));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}

				}
			}
		}



	}

	public static List<_IACBlock> getAllBlocksReflection() {
		return ALL_BLOCKS;
	}

}
