package org.golde.apocalypsecore.common.items;

import org.golde.apocalypsecore.common.ApocalypseCore;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class _ACItem extends Item implements _IACItem {

	public _ACItem(String name) {
		setRegistryName(name);
        setUnlocalizedName(ApocalypseCore.MODID + "." + name);
	}
	
	public _ACItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
