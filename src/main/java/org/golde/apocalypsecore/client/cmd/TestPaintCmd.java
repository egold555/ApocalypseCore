package org.golde.apocalypsecore.client.cmd;

import java.awt.Color;

import org.golde.apocalypsecore.common.utils.PaintUtil;

import ichttt.mods.mcpaint.common.MCPaintUtil;
import ichttt.mods.mcpaint.common.block.TileEntityCanvas;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;

public class TestPaintCmd extends CommandBase implements IClientCommand {

	@Override
	public String getName() {
		return "painttest";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/painttest";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		
		BlockPos posLookingBlock = new BlockPos(0, 100, 0);
		TileEntity teLookingAt = sender.getEntityWorld().getTileEntity(posLookingBlock);
		if(teLookingAt instanceof TileEntityCanvas) {
			
			byte scaleFactor = (byte)8;
			
			int[][] data = new int[128 / scaleFactor][128 / scaleFactor];
			
			for(int x = 0; x < data.length; x++) {
				for(int z = 0; z < data[x].length; z++) {
					data[x][z] = Color.HSBtoRGB((float)Math.random(), (float)Math.random(), (float)(Math.random() / 2 + 0.5));
				}
				
			}
			
			
			PaintUtil.client(sender.getEntityWorld(), new BlockPos(0, 100, 0), EnumFacing.UP, scaleFactor, data);
		}
		else {
			sender.sendMessage(new TextComponentString("Tile not correct"));
		}


	}

	@Override
	public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
		return false;
	}

}
