package org.golde.apocalypsecore.common.network;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.network.packets.ACPacketColorGuiClosed;
import org.golde.apocalypsecore.common.network.packets.ACPacketParticle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ACPacketHandler {

	private static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(ApocalypseCore.MODID.toLowerCase());

	public static void preInit() {
		int i = 0;

		ACPacketHandler.NETWORK.registerMessage(ACPacketParticle.class, ACPacketParticle.class, i++, Side.CLIENT);
		ACPacketHandler.NETWORK.registerMessage(ACPacketColorGuiClosed.class, ACPacketColorGuiClosed.class, i++, Side.SERVER);
		
	}
	

	public static void sendToServer(IMessage message)
	{
		NETWORK.sendToServer(message);
	}
	
	public static void sendToPlayer(EntityPlayer player, IMessage message)
	{
		if(!player.world.isRemote)
			NETWORK.sendTo(message, (EntityPlayerMP) player);
	}
	
	public static void sendToPlayersInWorld(World world, IMessage message)
	{
		if(world == null)
			sendToAll(message);
		else if(!world.isRemote)
			NETWORK.sendToDimension(message, world.provider.getDimension());
	}
	
	public static void sendToAll(IMessage message)
	{
		NETWORK.sendToAll(message);
	}
	
	public static void sendToAllAround(IMessage message, TargetPoint point)
	{
		NETWORK.sendToAllAround(message, point);
	}
	
}
