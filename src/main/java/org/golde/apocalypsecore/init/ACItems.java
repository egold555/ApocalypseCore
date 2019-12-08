package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.item.ItemFlamethrower;
import org.golde.apocalypsecore.item.ItemNightVisionGoggles;
import org.golde.apocalypsecore.item.ItemSmokeBomb;
import org.golde.apocalypsecore.item.ItemWrench;
import org.golde.apocalypsecore.item._core._ACItem;
import org.golde.apocalypsecore.item._core._IACItem;
import org.golde.apocalypsecore.item.gun.GunAimable;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ACItems {

	public static _ACItem exampleItem;
	public static ItemWrench wrench;
	public static ItemSmokeBomb smokeBomb;
	
	public static GunAimable gunGlock;
	
	public static ItemFlamethrower flamethrower;
	
	public static ItemNightVisionGoggles nightVisionGoggles;
	
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().register(exampleItem = new _ACItem("example_item"));
		event.getRegistry().register(wrench = new ItemWrench());
		event.getRegistry().register(smokeBomb = new ItemSmokeBomb());
		event.getRegistry().register(gunGlock = new GunAimable("glock", 12, 12, 50, 1, 3, 200, Items.APPLE, 1, "gun.glock.desc", "9mm Clip", 1));
		event.getRegistry().register(flamethrower = new ItemFlamethrower());
		event.getRegistry().register(nightVisionGoggles = new ItemNightVisionGoggles());

		for(_ACBlock block : ACBlocks.getAllBlocksReflection()) {
			if(block != null && block.shouldRegisterItem()) {
				event.getRegistry().register(getItem(block));
			}
		}

		populateAllItemsArray();

	}

	private static ItemBlock getItem(Block block) {
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		return ib;
	}

	private static void populateAllItemsArray(){

		for (Field field : ACItems.class.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {

				try {
					ALL_ITEMS.add((_IACItem) field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for(_IACItem item : ALL_ITEMS) {
			item.initModel();
		}
	}
}
