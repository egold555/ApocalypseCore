package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks.BlockLandMine;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.blocks._core._ACBlockWithTE;
import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.blocks.chest.loot.BlockLootChest;
import org.golde.apocalypsecore.blocks.chest.loot.TIleEntityLootChest;
import org.golde.apocalypsecore.client.render.block.TESRLootChest;
import org.golde.apocalypsecore.client.render.block.TESRLootChest2;
import org.golde.apocalypsecore.features.building.blocks.BlockBarbedWire;
import org.golde.apocalypsecore.features.building.blocks.BlockCagedLampOn;

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
	
	public static BlockLandMine landMine;

	public static BlockLootChest lootChest;

	public static class Decoration {
		
	}

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(exampleBlock = new _ACBlock("example_block"));
		event.getRegistry().register(landMine = new BlockLandMine());
		event.getRegistry().register(lootChest = new BlockLootChest());

		
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
		ClientRegistry.bindTileEntitySpecialRenderer(TIleEntityLootChest.class, new TESRLootChest2());
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
