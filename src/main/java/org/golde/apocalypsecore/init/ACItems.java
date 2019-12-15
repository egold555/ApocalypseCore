package org.golde.apocalypsecore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.blocks._core._ACBlock;
import org.golde.apocalypsecore.item.ItemCD;
import org.golde.apocalypsecore.item.ItemFlamethrower;
import org.golde.apocalypsecore.item.ItemGrapplingHook;
import org.golde.apocalypsecore.item.ItemNightVisionGoggles;
import org.golde.apocalypsecore.item.ItemSmokeBomb;
import org.golde.apocalypsecore.item.ItemWrench;
import org.golde.apocalypsecore.item._core._ACItem;
import org.golde.apocalypsecore.item._core._ACItemFood;
import org.golde.apocalypsecore.item._core._ACItemFood.EnumAnimation;
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
	public static ItemGrapplingHook grapplingHook;
	
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
	
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().register(exampleItem = new _ACItem("example_item"));
		event.getRegistry().register(wrench = new ItemWrench());
		event.getRegistry().register(smokeBomb = new ItemSmokeBomb());
		event.getRegistry().register(gunGlock = new GunAimable("glock", 12, 12, 50, 1, 3, 200, Items.APPLE, 1, "gun.glock.desc", "9mm Clip", 1));
		event.getRegistry().register(flamethrower = new ItemFlamethrower());
		event.getRegistry().register(nightVisionGoggles = new ItemNightVisionGoggles());
		event.getRegistry().register(grapplingHook = new ItemGrapplingHook());
		
		event.getRegistry().register(apple = new _ACItemFood("apple", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(apple_rotten = new _ACItemFood("apple_rotten", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(beans = new _ACItem("beans").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(beans_open = new _ACItemFood("beans_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(blueberry = new _ACItemFood("blueberry", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(blueberry_rotten = new _ACItemFood("blueberry_rotten", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(bottlecure = new _ACItemFood("bottlecure", EnumAnimation.DRINK).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(bottlerbi = new _ACItemFood("bottlerbi", EnumAnimation.DRINK).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(candybar = new _ACItemFood("candybar", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cannedcorn = new _ACItem("cannedcorn").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cannedcorn_open = new _ACItemFood("cannedcorn_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(canneddogfood = new _ACItem("canneddogfood").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(canneddogfood_open = new _ACItemFood("canneddogfood_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cannedpickles = new _ACItem("cannedpickles").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cannedpickles_open = new _ACItemFood("cannedpickles_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cereal = new _ACItemFood("cereal", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cerealemerald = new _ACItemFood("cerealemerald", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cerealflakes = new _ACItemFood("cerealflakes", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(cerealnutty = new _ACItemFood("cerealnutty", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(chips = new _ACItemFood("chips", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(chipscheese = new _ACItemFood("chipscheese", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(chipsranch = new _ACItemFood("chipsranch", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(chipssalt = new _ACItemFood("chipssalt", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(coffeebeans = new _ACItemFood("coffeebeans", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(colapop_empty = new _ACItem("colapop_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(colapop = new _ACItemFood("colapop", EnumAnimation.DRINK).setReturnItem(colapop_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(custard_open = new _ACItemFood("custard_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(custard = new _ACItem("custard").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(deadbull_empty = new _ACItem("deadbull_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(deadbull = new _ACItemFood("deadbull", EnumAnimation.DRINK).setReturnItem(deadbull_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(humansteak = new _ACItemFood("humansteak", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(icetea_empty = new _ACItem("icetea_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(icetea = new _ACItemFood("icetea", EnumAnimation.DRINK).setReturnItem(icetea_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(ironbrew_empty = new _ACItem("ironbrew_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(ironbrew = new _ACItemFood("ironbrew", EnumAnimation.DRINK).setReturnItem(ironbrew_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(juice_empty = new _ACItem("juice_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(juice = new _ACItemFood("juice", EnumAnimation.DRINK).setReturnItem(juice_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(lemonfizz_empty = new _ACItem("lemonfizz_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(lemonfizz = new _ACItemFood("lemonfizz", EnumAnimation.DRINK).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(milk_empty = new _ACItem("milk_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(milk = new _ACItemFood("milk", EnumAnimation.DRINK).setReturnItem(milk_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(milk_rotten = new _ACItemFood("milk_rotten", EnumAnimation.DRINK).setReturnItem(milk_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(noodles = new _ACItemFood("noodles", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(pasta = new _ACItem("pasta").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(pasta_open = new _ACItemFood("pasta_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(peach = new _ACItem("peach").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(peach_open = new _ACItemFood("peach_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(pear = new _ACItemFood("pear", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(pear_rotten = new _ACItemFood("pear_rotten", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(popcorn = new _ACItemFood("popcorn", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(raspberry = new _ACItemFood("raspberry", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(raspberry_rotten = new _ACItemFood("raspberry_rotten", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(rice = new _ACItemFood("rice", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodacola_empty = new _ACItem("sodacola_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodacola = new _ACItemFood("sodacola", EnumAnimation.DRINK).setReturnItem(sodacola_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodadew_empty = new _ACItem("sodadew_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodadew = new _ACItemFood("sodadew", EnumAnimation.DRINK).setReturnItem(sodadew_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodaorange_empty = new _ACItem("sodaorange_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodaorange = new _ACItemFood("sodaorange", EnumAnimation.DRINK).setReturnItem(sodaorange_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodapepe_empty = new _ACItem("sodapepe_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sodapepe = new _ACItemFood("sodapepe", EnumAnimation.DRINK).setReturnItem(sodapepe_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sprite_empty = new _ACItem("sprite_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(sprite = new _ACItemFood("sprite", EnumAnimation.DRINK).setReturnItem(sprite_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tacticalbacon = new _ACItem("tacticalbacon").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tacticalbacon_open = new _ACItemFood("tacticalbacon_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tomatosoup = new _ACItem("tomatosoup").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tomatosoup_open = new _ACItemFood("tomatosoup_open", EnumAnimation.DRINK).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tuna = new _ACItem("tuna").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(tuna_open = new _ACItemFood("tuna_open", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(waterbottle_empty = new _ACItem("waterbottle_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(waterbottle = new _ACItemFood("waterbottle", EnumAnimation.DRINK).setReturnItem(waterbottle_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(watercanteen_empty = new _ACItem("watercanteen_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(watercanteen = new _ACItemFood("watercanteen", EnumAnimation.DRINK).setReturnItem(watercanteen_empty).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(watermelon = new _ACItemFood("watermelon", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(watermelon_rotten = new _ACItemFood("watermelon_rotten", EnumAnimation.EAT).setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(zombieenergy_empty = new _ACItem("zombieenergy_empty").setCreativeTab(ApocalypseCore.tabFood));
		event.getRegistry().register(zombieenergy = new _ACItemFood("zombieenergy", EnumAnimation.DRINK).setReturnItem(zombieenergy_empty).setCreativeTab(ApocalypseCore.tabFood));
		
		
		ACItemsCD.registerItems(event);

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
		
		//ac items cd
		for (Field field : ACItemsCD.class.getDeclaredFields()) {
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
