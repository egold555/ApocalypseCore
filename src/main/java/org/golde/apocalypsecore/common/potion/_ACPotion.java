package org.golde.apocalypsecore.common.potion;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.items._IACItem;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class _ACPotion extends Potion {

	private static final ResourceLocation TEXTURE = new ResourceLocation(ApocalypseCore.MODID, "textures/gui/potions.png");
	
	public _ACPotion(String name, boolean isBadEffect, int color, int iconIndexX, int iconIndexY) {
		super(isBadEffect, color);
		setRegistryName(name);
        setPotionName("potion." + name);
        if (!isBadEffect) {
        	this.setBeneficial();
        }
        setIconIndex(iconIndexX, iconIndexY);
	}
	
	@Override
	public boolean hasStatusIcon() {
		//System.out.println("" + getStatusIconIndex());
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		return true;
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
	
}
