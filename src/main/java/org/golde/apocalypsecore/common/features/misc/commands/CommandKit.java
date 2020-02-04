package org.golde.apocalypsecore.common.features.misc.commands;

import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.common.features.misc.items.kit.AbstractKit;
import org.golde.apocalypsecore.common.features.misc.items.kit.ItemKit;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.ItemHandlerHelper;
import scala.actors.threadpool.Arrays;

public class CommandKit extends CommandBase {

	@Override
	public String getName() {
		return "kit";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/kit <name>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if(args.length > 0) {

			String kitName = args[0];
			
			EntityPlayer player = null;

			if(ItemKit.getFromName(kitName) == null) {
				throw new CommandException("Invalid kit!");
			}

			if(args.length == 2 || !(sender instanceof EntityPlayer)) {
				player = getPlayer(server, sender, args[1]);
			}
			else {
				player = (EntityPlayer)sender;
			}
			
			if(player == null) {
				throw new CommandException("Player must be specified to give the kit to!");
			}

			sender.sendMessage(new TextComponentString(ChatFormatting.GREEN + "Successfully given " + ChatFormatting.GRAY + player.getName() + ChatFormatting.GREEN + " kit " + ChatFormatting.GRAY + kitName + ChatFormatting.GREEN + "."));
			ItemHandlerHelper.giveItemToPlayer(player, ItemKit.get(kitName));

		}
		else {
			throw new CommandException("/kit <kit name>");
		}


	}




	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		
		if(args.length == 1) {
			List<String> toReturn = new ArrayList<String>();
			for(AbstractKit kit : ItemKit.kits) {
				toReturn.add(kit.getName());
			}
			return getListOfStringsMatchingLastWord(args, toReturn);
		}
		
		else if(args.length == 2) {
			return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}
		
		return super.getTabCompletions(server, sender, args, targetPos);

	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 1;
    }

}
