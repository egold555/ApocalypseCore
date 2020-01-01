package org.golde.apocalypsecore.client.render;

import java.util.Random;

import org.golde.apocalypsecore.client.ClientStats;
import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.thirst.ThirstConstants;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ForgeHooks;

public class GuiRenderBar {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static Random rand = new Random();
	public static int updateCounter;
	
	public static int left_height = 39;
    public static int right_height = 39;
    
    private static final ResourceLocation TEXTURE = new ResourceLocation(ApocalypseCore.MODID, "textures/gui/thirst_bar.png");
    
    public static void renderArmor(int width, int height) {
		mc.mcProfiler.startSection("armor");

		GuiIngame ingameGUI = mc.ingameGUI;
		int left = (width / 2) - 91;
		int top = height - left_height;

		if (!mc.player.isRidingHorse()) {
			if (ThirstConstants.METER_ON_LEFT) {
				left = ((width / 2) - 91) + 100;
			}
		}

		int level = ForgeHooks.getTotalArmorValue(mc.player);
		for (int i = 1; (level > 0) && (i < 20); i += 2) {
			if (i < level) {
				ingameGUI.drawTexturedModalRect(left, top, 34, 9, 9, 9);
			} else if (i == level) {
				ingameGUI.drawTexturedModalRect(left, top, 25, 9, 9, 9);
			} else if (i > level) {
				ingameGUI.drawTexturedModalRect(left, top, 16, 9, 9, 9);
			}
			left += 8;
		}
		left_height += 10;

		mc.mcProfiler.endSection();
	}
    
    public static void renderAir(int width, int height) {
		mc.mcProfiler.startSection("air");
		int left = (width / 2) + 91;
		int top = height - right_height;
		GuiIngame ingameGUI = mc.ingameGUI;

		int y = 0;
		int x = 0;

		int level = ForgeHooks.getTotalArmorValue(mc.player);

		if (!mc.player.isRidingHorse()) {
			if (ThirstConstants.METER_ON_LEFT) {
				if (level > 0) {
					y = -10;
				}
			} else if (!ThirstConstants.METER_ON_LEFT) {
				if (level > 0) {
					y = -10;
				} else {
					x = -101;
				}
			}
		}

		if (mc.player.isInsideOfMaterial(Material.WATER)) {
			int air = mc.player.getAir();
			int full = MathHelper.ceil(((air - 2) * 10.0D) / 300.0D);
			int partial = MathHelper.ceil((air * 10.0D) / 300.0D) - full;

			for (int i = 0; i < (full + partial); ++i) {
				ingameGUI.drawTexturedModalRect((left - (i * 8) - 9) + x, top + y, (i < full ? 16 : 25), 18, 9, 9);
			}
			right_height += 10;
		}
	}

    public static void renderThirst(int rwidth, int rheight) {
		bind(TEXTURE);

		ClientStats stats = ClientStats.getInstance();
		GuiIngame ingameGUI = mc.ingameGUI;
		updateCounter = ingameGUI.getUpdateCounter();
	
		for (int i = 0; i < 10; i++) {
			int width = ThirstConstants.METER_ON_LEFT ? ((rwidth / 2) - 91) + (i * 8) : ((rwidth / 2) + 91) - (i * 8) - 9;
			int height = rheight - 49;
			int xStart = 1;
			int yStart = 1;
			int yEnd = 9;
			int xEnd = 7;

			if (stats.saturation <= 0.0F && updateCounter % (stats.level * 3 + 1) == 0) {
				height += rand.nextInt(3) - 1;
			}
			
			ingameGUI.drawTexturedModalRect(width, height, xStart, yStart, xEnd, yEnd);
			if(!stats.isPoisoned) {
				if(i * 2 + 1 < stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 8, yStart, xEnd, yEnd);
				} else if(i * 2 + 1 == stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 16, yStart, xEnd, yEnd);
				}
			} else {
				if (i * 2 + 1 < stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 24, yStart, xEnd, yEnd);
				} else if (i * 2 + 1 == stats.level) {
					ingameGUI.drawTexturedModalRect(width, height, xStart + 32, yStart, xEnd, yEnd);
				}
			}
		}
		bind(Gui.ICONS);
	}

	private static void bind(ResourceLocation res) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
	}
}
