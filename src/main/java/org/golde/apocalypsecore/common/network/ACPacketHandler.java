package org.golde.apocalypsecore.common.network;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.network.packets.client.ACPacketParticle;

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
