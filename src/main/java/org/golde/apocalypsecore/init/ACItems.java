package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.features.weapons.items.ItemFlamethrower;
import org.golde.apocalypsecore.features.weapons.items.ItemGrapplingHook;
import org.golde.apocalypsecore.features.weapons.items.ItemMolotovCocktail;
import org.golde.apocalypsecore.features.weapons.items.ItemNightVisionGoggles;
import org.golde.apocalypsecore.features.weapons.items.ItemSmokeBomb;
import org.golde.apocalypsecore.features.weapons.items.ItemTaser;
import org.golde.apocalypsecore.item.ItemWrench;
import org.golde.apocalypsecore.item._core._ACItem;
import org.golde.apocalypsecore.item._core._ACItemFood;
import org.golde.apocalypsecore.item._core._ACItemFood.EnumAnimation;
import org.golde.apocalypsecore.item._core._ACItemMeleeWeapon;
import org.golde.apocalypsecore.item._core._IACItem;
import org.golde.apocalypsecore.item.gun.GunAimable;
import org.golde.apocalypsecore.item.syringe.ItemSyringeEmpty;
import org.golde.apocalypsecore.item.syringe.ItemSyringeFull;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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

	
	
	public static ItemSyringeEmpty syringeEmpty;
	public static ItemSyringeEmpty syringeFull;


	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		
		
		event.getRegistry().register(syringeEmpty = new ItemSyringeEmpty());
		event.getRegistry().register(syringeFull = new ItemSyringeFull());
		
//		for(_IACBlock block : ACBlocks.getAllBlocksReflection()) {
//			if(block != null && block.shouldRegisterItem()) {
//				
//				event.getRegistry().register(getItem(block));
//			}
//		}

		populateAllItemsArray();

	}

	private static ItemBlock getItem(_IACBlock iblock) {
		return getItem((Block)iblock); //BAD but it works
	}
	
	private static ItemBlock getItem(Block block) {
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		return ib;
	}

	private static void populateAllItemsArray(){

		List<Class<?>> classes = new ArrayList<Class<?>>();
		
		classes.add(ACItems.class);
		
		for(Class c : ACItems.class.getClasses()) {
			classes.add(c);
		}
		
		for(Class c : classes) {
			for (Field field : c.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {

					try {
						ALL_ITEMS.add((_IACItem) field.get(null));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}

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
