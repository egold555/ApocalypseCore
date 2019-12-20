package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.blocks._core._IACBlock;
import org.golde.apocalypsecore.item.ItemFlamethrower;
import org.golde.apocalypsecore.item.ItemGrapplingHook;
import org.golde.apocalypsecore.item.ItemNightVisionGoggles;
import org.golde.apocalypsecore.item.ItemSmokeBomb;
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

	public static _ACItem exampleItem;
	public static ItemWrench wrench;
	public static ItemNightVisionGoggles nightVisionGoggles;
	public static ItemGrapplingHook grapplingHook;
	public static ItemSyringeEmpty syringeEmpty;
	public static ItemSyringeEmpty syringeFull;
	public static class _Weapons {
		public static ItemFlamethrower flamethrower;
		public static ItemSmokeBomb smokeBomb;
		public static GunAimable gunGlock;
		public static _ACItemMeleeWeapon itemBaseBallBat;
		public static _ACItemMeleeWeapon itemBaseBallBatSpiked;
	}

	public static class _Food {

		public static _ACItemFood apple;
		public static _ACItemFood apple_rotten;
		public static _ACItem beans;
		public static _ACItemFood beans_open;
		public static _ACItemFood blueberry;
		public static _ACItemFood blueberry_rotten;
		public static _ACItemFood bottlecure;
		public static _ACItemFood bottlerbi;
		public static _ACItemFood candybar;
		public static _ACItem cannedcorn;
		public static _ACItemFood cannedcorn_open;
		public static _ACItem canneddogfood;
		public static _ACItemFood canneddogfood_open;
		public static _ACItem cannedpickles;
		public static _ACItemFood cannedpickles_open;
		public static _ACItemFood cereal;
		public static _ACItemFood cerealemerald;
		public static _ACItemFood cerealflakes;
		public static _ACItemFood cerealnutty;
		public static _ACItemFood chips;
		public static _ACItemFood chipscheese;
		public static _ACItemFood chipsranch;
		public static _ACItemFood chipssalt;
		public static _ACItemFood coffeebeans;
		public static _ACItemFood colapop;
		public static _ACItem colapop_empty;
		public static _ACItem custard;
		public static _ACItemFood custard_open;
		public static _ACItemFood deadbull;
		public static _ACItem deadbull_empty;
		public static _ACItemFood humansteak;
		public static _ACItemFood icetea;
		public static _ACItem icetea_empty;
		public static _ACItemFood ironbrew;
		public static _ACItem ironbrew_empty;
		public static _ACItemFood juice;
		public static _ACItem juice_empty;
		public static _ACItemFood lemonfizz;
		public static _ACItem lemonfizz_empty;
		public static _ACItemFood milk;
		public static _ACItem milk_empty;
		public static _ACItemFood milk_rotten;
		public static _ACItemFood noodles;
		public static _ACItem pasta;
		public static _ACItemFood pasta_open;
		public static _ACItem peach;
		public static _ACItemFood peach_open;
		public static _ACItemFood pear;
		public static _ACItemFood pear_rotten;
		public static _ACItemFood popcorn;
		public static _ACItemFood raspberry;
		public static _ACItemFood raspberry_rotten;
		public static _ACItemFood rice;
		public static _ACItemFood sodacola;
		public static _ACItem sodacola_empty;
		public static _ACItemFood sodadew;
		public static _ACItem sodadew_empty;
		public static _ACItemFood sodaorange;
		public static _ACItem sodaorange_empty;
		public static _ACItemFood sodapepe;
		public static _ACItem sodapepe_empty;
		public static _ACItemFood sprite;
		public static _ACItem sprite_empty;
		public static _ACItem tacticalbacon;
		public static _ACItemFood tacticalbacon_open;
		public static _ACItem tomatosoup;
		public static _ACItemFood tomatosoup_open;
		public static _ACItem tuna;
		public static _ACItemFood tuna_open;
		public static _ACItemFood waterbottle;
		public static _ACItem waterbottle_empty;
		public static _ACItemFood watercanteen;
		public static _ACItem watercanteen_empty;
		public static _ACItemFood watermelon;
		public static _ACItemFood watermelon_rotten;
		public static _ACItemFood zombieenergy;
		public static _ACItem zombieenergy_empty;
	}

	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().register(exampleItem = new _ACItem("example_item"));
		event.getRegistry().register(wrench = new ItemWrench());
		event.getRegistry().register(_Weapons.smokeBomb = new ItemSmokeBomb());
		event.getRegistry().register(_Weapons.gunGlock = new GunAimable("glock", 12, 12, 50, 1, 3, 200, Items.APPLE, 1, "gun.glock.desc", "9mm Clip", 1));
		event.getRegistry().register(_Weapons.flamethrower = new ItemFlamethrower());
		event.getRegistry().register(nightVisionGoggles = new ItemNightVisionGoggles());
		event.getRegistry().register(grapplingHook = new ItemGrapplingHook());
		event.getRegistry().register(_Weapons.itemBaseBallBat = new _ACItemMeleeWeapon("bat", 3, 50, Blocks.PLANKS));
		event.getRegistry().register(_Weapons.itemBaseBallBatSpiked = new _ACItemMeleeWeapon("bat_spiked", 6, 100, Blocks.PLANKS));
		event.getRegistry().register(syringeEmpty = new ItemSyringeEmpty());
		event.getRegistry().register(syringeFull = new ItemSyringeFull());
		
		
		event.getRegistry().register(_Food.apple = new _ACItemFood("apple", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.apple_rotten = new _ACItemFood("apple_rotten", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.beans = new _ACItem("beans").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.beans_open = new _ACItemFood("beans_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.blueberry = new _ACItemFood("blueberry", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.blueberry_rotten = new _ACItemFood("blueberry_rotten", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.bottlecure = new _ACItemFood("bottlecure", EnumAnimation.DRINK).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.bottlerbi = new _ACItemFood("bottlerbi", EnumAnimation.DRINK).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.candybar = new _ACItemFood("candybar", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cannedcorn = new _ACItem("cannedcorn").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cannedcorn_open = new _ACItemFood("cannedcorn_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.canneddogfood = new _ACItem("canneddogfood").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.canneddogfood_open = new _ACItemFood("canneddogfood_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cannedpickles = new _ACItem("cannedpickles").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cannedpickles_open = new _ACItemFood("cannedpickles_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cereal = new _ACItemFood("cereal", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cerealemerald = new _ACItemFood("cerealemerald", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cerealflakes = new _ACItemFood("cerealflakes", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.cerealnutty = new _ACItemFood("cerealnutty", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.chips = new _ACItemFood("chips", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.chipscheese = new _ACItemFood("chipscheese", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.chipsranch = new _ACItemFood("chipsranch", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.chipssalt = new _ACItemFood("chipssalt", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.coffeebeans = new _ACItemFood("coffeebeans", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.colapop_empty = new _ACItem("colapop_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.colapop = new _ACItemFood("colapop", EnumAnimation.DRINK).setReturnItem(_Food.colapop_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.custard_open = new _ACItemFood("custard_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.custard = new _ACItem("custard").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.deadbull_empty = new _ACItem("deadbull_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.deadbull = new _ACItemFood("deadbull", EnumAnimation.DRINK).setReturnItem(_Food.deadbull_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.humansteak = new _ACItemFood("humansteak", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.icetea_empty = new _ACItem("icetea_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.icetea = new _ACItemFood("icetea", EnumAnimation.DRINK).setReturnItem(_Food.icetea_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.ironbrew_empty = new _ACItem("ironbrew_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.ironbrew = new _ACItemFood("ironbrew", EnumAnimation.DRINK).setReturnItem(_Food.ironbrew_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.juice_empty = new _ACItem("juice_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.juice = new _ACItemFood("juice", EnumAnimation.DRINK).setReturnItem(_Food.juice_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.lemonfizz_empty = new _ACItem("lemonfizz_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.lemonfizz = new _ACItemFood("lemonfizz", EnumAnimation.DRINK).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.milk_empty = new _ACItem("milk_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.milk = new _ACItemFood("milk", EnumAnimation.DRINK).setReturnItem(_Food.milk_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.milk_rotten = new _ACItemFood("milk_rotten", EnumAnimation.DRINK).setReturnItem(_Food.milk_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.noodles = new _ACItemFood("noodles", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.pasta = new _ACItem("pasta").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.pasta_open = new _ACItemFood("pasta_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.peach = new _ACItem("peach").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.peach_open = new _ACItemFood("peach_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.pear = new _ACItemFood("pear", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.pear_rotten = new _ACItemFood("pear_rotten", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.popcorn = new _ACItemFood("popcorn", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.raspberry = new _ACItemFood("raspberry", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.raspberry_rotten = new _ACItemFood("raspberry_rotten", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.rice = new _ACItemFood("rice", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodacola_empty = new _ACItem("sodacola_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodacola = new _ACItemFood("sodacola", EnumAnimation.DRINK).setReturnItem(_Food.sodacola_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodadew_empty = new _ACItem("sodadew_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodadew = new _ACItemFood("sodadew", EnumAnimation.DRINK).setReturnItem(_Food.sodadew_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodaorange_empty = new _ACItem("sodaorange_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodaorange = new _ACItemFood("sodaorange", EnumAnimation.DRINK).setReturnItem(_Food.sodaorange_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodapepe_empty = new _ACItem("sodapepe_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sodapepe = new _ACItemFood("sodapepe", EnumAnimation.DRINK).setReturnItem(_Food.sodapepe_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sprite_empty = new _ACItem("sprite_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.sprite = new _ACItemFood("sprite", EnumAnimation.DRINK).setReturnItem(_Food.sprite_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tacticalbacon = new _ACItem("tacticalbacon").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tacticalbacon_open = new _ACItemFood("tacticalbacon_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tomatosoup = new _ACItem("tomatosoup").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tomatosoup_open = new _ACItemFood("tomatosoup_open", EnumAnimation.DRINK).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tuna = new _ACItem("tuna").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.tuna_open = new _ACItemFood("tuna_open", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.waterbottle_empty = new _ACItem("waterbottle_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.waterbottle = new _ACItemFood("waterbottle", EnumAnimation.DRINK).setReturnItem(_Food.waterbottle_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.watercanteen_empty = new _ACItem("watercanteen_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.watercanteen = new _ACItemFood("watercanteen", EnumAnimation.DRINK).setReturnItem(_Food.watercanteen_empty).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.watermelon = new _ACItemFood("watermelon", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.watermelon_rotten = new _ACItemFood("watermelon_rotten", EnumAnimation.EAT).setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.zombieenergy_empty = new _ACItem("zombieenergy_empty").setCreativeTab(ACTabs.FOOD));
		event.getRegistry().register(_Food.zombieenergy = new _ACItemFood("zombieenergy", EnumAnimation.DRINK).setReturnItem(_Food.zombieenergy_empty).setCreativeTab(ACTabs.FOOD));

		
		for(_IACBlock block : ACBlocks.getAllBlocksReflection()) {
			if(block != null && block.shouldRegisterItem()) {
				event.getRegistry().register(getItem(block));
			}
		}

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
