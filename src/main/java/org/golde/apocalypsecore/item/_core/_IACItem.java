package org.golde.apocalypsecore.item._core;

import org.golde.apocalypsecore.init.shared._IBObject;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface _IACItem extends _IBObject {

	//Item.java has this, needed for a hacky feature list
	public Item setCreativeTab(CreativeTabs tab);
}
