package org.golde.apocalypsecore.features.food;

import org.golde.apocalypsecore.base.features.Feature;
import org.golde.apocalypsecore.item._core._ACItem;
import org.golde.apocalypsecore.item._core._ACItemFood;
import org.golde.apocalypsecore.item._core._ACItemFood.EnumAnimation;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FeatureFood extends Feature {

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
	
	@Override
	public void registerBlocks() {
		
	}

	@Override
	public void registerItems() {
		registerItem(apple = new _ACItemFood("apple", EnumAnimation.EAT));
		registerItem(apple_rotten = new _ACItemFood("apple_rotten", EnumAnimation.EAT));
		registerItem(beans = new _ACItem("beans"));
		registerItem(beans_open = new _ACItemFood("beans_open", EnumAnimation.EAT));
		registerItem(blueberry = new _ACItemFood("blueberry", EnumAnimation.EAT));
		registerItem(blueberry_rotten = new _ACItemFood("blueberry_rotten", EnumAnimation.EAT));
		registerItem(bottlecure = new _ACItemFood("bottlecure", EnumAnimation.DRINK));
		registerItem(bottlerbi = new _ACItemFood("bottlerbi", EnumAnimation.DRINK));
		registerItem(candybar = new _ACItemFood("candybar", EnumAnimation.EAT));
		registerItem(cannedcorn = new _ACItem("cannedcorn"));
		registerItem(cannedcorn_open = new _ACItemFood("cannedcorn_open", EnumAnimation.EAT));
		registerItem(canneddogfood = new _ACItem("canneddogfood"));
		registerItem(canneddogfood_open = new _ACItemFood("canneddogfood_open", EnumAnimation.EAT));
		registerItem(cannedpickles = new _ACItem("cannedpickles"));
		registerItem(cannedpickles_open = new _ACItemFood("cannedpickles_open", EnumAnimation.EAT));
		registerItem(cereal = new _ACItemFood("cereal", EnumAnimation.EAT));
		registerItem(cerealemerald = new _ACItemFood("cerealemerald", EnumAnimation.EAT));
		registerItem(cerealflakes = new _ACItemFood("cerealflakes", EnumAnimation.EAT));
		registerItem(cerealnutty = new _ACItemFood("cerealnutty", EnumAnimation.EAT));
		registerItem(chips = new _ACItemFood("chips", EnumAnimation.EAT));
		registerItem(chipscheese = new _ACItemFood("chipscheese", EnumAnimation.EAT));
		registerItem(chipsranch = new _ACItemFood("chipsranch", EnumAnimation.EAT));
		registerItem(chipssalt = new _ACItemFood("chipssalt", EnumAnimation.EAT));
		registerItem(coffeebeans = new _ACItemFood("coffeebeans", EnumAnimation.EAT));
		registerItem(colapop_empty = new _ACItem("colapop_empty"));
		registerItem(colapop = new _ACItemFood("colapop", EnumAnimation.DRINK).setReturnItem(colapop_empty));
		registerItem(custard_open = new _ACItemFood("custard_open", EnumAnimation.EAT));
		registerItem(custard = new _ACItem("custard"));
		registerItem(deadbull_empty = new _ACItem("deadbull_empty"));
		registerItem(deadbull = new _ACItemFood("deadbull", EnumAnimation.DRINK).setReturnItem(deadbull_empty));
		registerItem(humansteak = new _ACItemFood("humansteak", EnumAnimation.EAT));
		registerItem(icetea_empty = new _ACItem("icetea_empty"));
		registerItem(icetea = new _ACItemFood("icetea", EnumAnimation.DRINK).setReturnItem(icetea_empty));
		registerItem(ironbrew_empty = new _ACItem("ironbrew_empty"));
		registerItem(ironbrew = new _ACItemFood("ironbrew", EnumAnimation.DRINK).setReturnItem(ironbrew_empty));
		registerItem(juice_empty = new _ACItem("juice_empty"));
		registerItem(juice = new _ACItemFood("juice", EnumAnimation.DRINK).setReturnItem(juice_empty));
		registerItem(lemonfizz_empty = new _ACItem("lemonfizz_empty"));
		registerItem(lemonfizz = new _ACItemFood("lemonfizz", EnumAnimation.DRINK));
		registerItem(milk_empty = new _ACItem("milk_empty"));
		registerItem(milk = new _ACItemFood("milk", EnumAnimation.DRINK).setReturnItem(milk_empty));
		registerItem(milk_rotten = new _ACItemFood("milk_rotten", EnumAnimation.DRINK).setReturnItem(milk_empty));
		registerItem(noodles = new _ACItemFood("noodles", EnumAnimation.EAT));
		registerItem(pasta = new _ACItem("pasta"));
		registerItem(pasta_open = new _ACItemFood("pasta_open", EnumAnimation.EAT));
		registerItem(peach = new _ACItem("peach"));
		registerItem(peach_open = new _ACItemFood("peach_open", EnumAnimation.EAT));
		registerItem(pear = new _ACItemFood("pear", EnumAnimation.EAT));
		registerItem(pear_rotten = new _ACItemFood("pear_rotten", EnumAnimation.EAT));
		registerItem(popcorn = new _ACItemFood("popcorn", EnumAnimation.EAT));
		registerItem(raspberry = new _ACItemFood("raspberry", EnumAnimation.EAT));
		registerItem(raspberry_rotten = new _ACItemFood("raspberry_rotten", EnumAnimation.EAT));
		registerItem(rice = new _ACItemFood("rice", EnumAnimation.EAT));
		registerItem(sodacola_empty = new _ACItem("sodacola_empty"));
		registerItem(sodacola = new _ACItemFood("sodacola", EnumAnimation.DRINK).setReturnItem(sodacola_empty));
		registerItem(sodadew_empty = new _ACItem("sodadew_empty"));
		registerItem(sodadew = new _ACItemFood("sodadew", EnumAnimation.DRINK).setReturnItem(sodadew_empty));
		registerItem(sodaorange_empty = new _ACItem("sodaorange_empty"));
		registerItem(sodaorange = new _ACItemFood("sodaorange", EnumAnimation.DRINK).setReturnItem(sodaorange_empty));
		registerItem(sodapepe_empty = new _ACItem("sodapepe_empty"));
		registerItem(sodapepe = new _ACItemFood("sodapepe", EnumAnimation.DRINK).setReturnItem(sodapepe_empty));
		registerItem(sprite_empty = new _ACItem("sprite_empty"));
		registerItem(sprite = new _ACItemFood("sprite", EnumAnimation.DRINK).setReturnItem(sprite_empty));
		registerItem(tacticalbacon = new _ACItem("tacticalbacon"));
		registerItem(tacticalbacon_open = new _ACItemFood("tacticalbacon_open", EnumAnimation.EAT));
		registerItem(tomatosoup = new _ACItem("tomatosoup"));
		registerItem(tomatosoup_open = new _ACItemFood("tomatosoup_open", EnumAnimation.DRINK));
		registerItem(tuna = new _ACItem("tuna"));
		registerItem(tuna_open = new _ACItemFood("tuna_open", EnumAnimation.EAT));
		registerItem(waterbottle_empty = new _ACItem("waterbottle_empty"));
		registerItem(waterbottle = new _ACItemFood("waterbottle", EnumAnimation.DRINK).setReturnItem(waterbottle_empty));
		registerItem(watercanteen_empty = new _ACItem("watercanteen_empty"));
		registerItem(watercanteen = new _ACItemFood("watercanteen", EnumAnimation.DRINK).setReturnItem(watercanteen_empty));
		registerItem(watermelon = new _ACItemFood("watermelon", EnumAnimation.EAT));
		registerItem(watermelon_rotten = new _ACItemFood("watermelon_rotten", EnumAnimation.EAT));
		registerItem(zombieenergy_empty = new _ACItem("zombieenergy_empty"));
		registerItem(zombieenergy = new _ACItemFood("zombieenergy", EnumAnimation.DRINK).setReturnItem(zombieenergy_empty));

	}

	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(sodaorange);
	}

	@Override
	public void bindTESR() {
		
	}

}