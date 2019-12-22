package org.golde.apocalypsecore.network;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.network.packets.client.ACPacketParticle;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ACPacketHandler {

	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(ApocalypseCore.MODID.toLowerCase());

	public static void preInit() {
		int i = 0;

		ACPacketHandler.NETWORK.registerMessage(ACPacketParticle.class, ACPacketParticle.class, i++, Side.CLIENT);
		
	}
	
}
