package org.golde.apocalypsecore.command;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.entity.EntityFallingLootCrate;
import org.golde.apocalypsecore.init.ACBlocks;
import org.golde.apocalypsecore.network.ACPacketHandler;
import org.golde.apocalypsecore.network.packets.client.ACPacketParticle;
import org.golde.apocalypsecore.utils.ACParticleTypes;
import org.golde.apocalypsecore.utils.EnumUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ACCommandTest extends CommandBase {

	@Override
	public String getName() {
		return "actest";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
			
		 EntityFallingLootCrate fb = new EntityFallingLootCrate(sender.getEntityWorld(), sender.getPosition().getX(), sender.getPosition().getY() + 10, sender.getPosition().getZ());
		 sender.getEntityWorld().spawnEntity(fb);
        
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
		return Collections.<String>emptyList();
    }

}
