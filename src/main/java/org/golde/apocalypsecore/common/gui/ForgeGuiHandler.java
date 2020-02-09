package org.golde.apocalypsecore.common.gui;

import org.golde.apocalypsecore.client.gui.GuiColorWheel;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ForgeGuiHandler implements IGuiHandler {

	public static final int GUI_INDEX_COLOR_PICKER = 0;

	//Returns Containers
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}

	//return new Gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		
		if (world instanceof WorldClient) {

			if(ID == GUI_INDEX_COLOR_PICKER) {
				return new GuiColorWheel();
			}

		}

		return null;
	}

}
