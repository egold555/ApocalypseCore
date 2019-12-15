package org.golde.apocalypsecore.item._core;

import org.golde.apocalypsecore.ACTabs;
import org.golde.apocalypsecore.ApocalypseCore;

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
        if(shouldBeInCreatveTab()) {setCreativeTab(ACTabs.MISC);}
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
