package org.golde.apocalypsecore.common.network.packets.server;

import org.golde.apocalypsecore.client.ClientStats;
import org.golde.apocalypsecore.common.features.thirst.PlayerContainer;
import org.golde.apocalypsecore.common.network.packets.client.ACPacketParticle;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketMovement implements IMessage, IMessageHandler<PacketMovement, IMessage> {

	private String username;
	private int movementSpeed;
	
	public PacketMovement() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		username = ByteBufUtils.readUTF8String(buf);
		movementSpeed = buf.readInt();	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, FMLClientHandler.instance().getClientPlayerEntity().getDisplayNameString());
		buf.writeInt(ClientStats.getInstance().movementSpeed);	
	}
	
	public void handleServerSide() {
		PlayerContainer handler = PlayerContainer.getPlayer(username);
		if (handler != null) {
			handler.getStats().movementSpeed = movementSpeed;
		}
	}

	@Override
	public IMessage onMessage(PacketMovement message, MessageContext ctx) {
		message.handleServerSide();
		return null;
	}
}
