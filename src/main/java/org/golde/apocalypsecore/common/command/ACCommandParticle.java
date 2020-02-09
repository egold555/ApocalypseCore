package org.golde.apocalypsecore.common.command;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.client.ACParticleTypesClient;
import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.ACPacketParticle;
import org.golde.apocalypsecore.common.utils.ACParticleTypesServer;
import org.golde.apocalypsecore.common.utils.EnumUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ACCommandParticle extends CommandBase {

	@Override
	public String getName() {
		return "acparticle";
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
		
		
		 if (args.length < 7)
	        {
	            throw new WrongUsageException("/acparticle <particle> <x> <y> <z> <speed x> <speed y> <speed z> [count] [params] ");
	        }
		
		ACParticleTypesServer particle;
		
		try {
			particle = ACParticleTypesServer.valueOf(args[0]);
		}
		catch(Exception notused) {
			throw new WrongUsageException("Particle not found");
		}
		
		Vec3d vec3d = sender.getPositionVector();
        double x = (double)((float)parseDouble(vec3d.x, args[1], true));
        double y = (double)((float)parseDouble(vec3d.y, args[2], true));
        double z = (double)((float)parseDouble(vec3d.z, args[3], true));
        double xs = (double)((float)parseDouble(args[4]));
        double ys = (double)((float)parseDouble(args[5]));
        double zs = (double)((float)parseDouble(args[6]));
		
        int count = 1;
        if(args.length > 7) {
        	count = parseInt(args[7], 1);
        }
        
        int[] aint = new int[particle.getArgumentCount()];

        for (int j = 0; j < aint.length; ++j)
        {
            if (args.length > 8 + j)
            {
                try
                {
                    aint[j] = Integer.parseInt(args[8 + j]);
                }
                catch (NumberFormatException var28)
                {
                    throw new CommandException("invanid params", new Object[] {args[8 + j]});
                }
            }
        }
        
        System.out.println(new ACPacketParticle(particle, x, y, z, xs, ys, zs, count, aint).toString());
        ACPacketHandler.sendToAll(new ACPacketParticle(particle, x, y, z, xs, ys, zs, count, aint));
        
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, EnumUtils.getNames(ACParticleTypesClient.class));
        }
        else if (args.length > 1 && args.length <= 4)
        {
            return getTabCompletionCoordinate(args, 1, targetPos);
        }
        else {
        	return Collections.<String>emptyList();
        }
    }

}
