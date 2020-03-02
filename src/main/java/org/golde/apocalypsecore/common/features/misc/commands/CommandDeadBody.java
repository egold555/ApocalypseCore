package org.golde.apocalypsecore.common.features.misc.commands;

import java.util.UUID;

import org.golde.apocalypsecore.common.features.misc.entity.EntityDeadBody;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandDeadBody extends CommandBase {

	@Override
	public String getName() {
		return "deadbody";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "deadbody";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {
			
			EntityPlayer p = (EntityPlayer)sender;
			
			sender.getEntityWorld().spawnEntity(new EntityDeadBody(sender.getEntityWorld(), p.posX, p.posY, p.posZ, "ericgolde555", UUID.fromString("e071b42d-e482-4bf7-a6e4-61ce59b281df")));
			
		}
		
		else {
			throw new CommandException("need to be a player");
		}
		
	}

}
