package org.golde.apocalypsecore.common.blocks;

import org.golde.apocalypsecore.common.init.shared._IBObject;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public interface _IACBlock extends _IBObject {

	public default boolean shouldRegisterItem() {
		return true;
	}
	
	public Block setCreativeTab(CreativeTabs tab); //block has this method, used for features
	
}
