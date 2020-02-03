package org.golde.apocalypsecore.common.network.packets.client;

import org.golde.apocalypsecore.common.features.thirst.ThirstLogic;
import org.golde.apocalypsecore.common.features.thirst.client.ClientStats;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateClient implements IMessage, IMessageHandler<PacketUpdateClient, IMessage> {

	private int level;
	private float saturation;
	private boolean poisoned;
	
	public PacketUpdateClient() {
	}

	public PacketUpdateClient(ThirstLogic stats) {
		this.level = stats.thirstLevel;
		this.saturation = stats.thirstSaturation;
		this.poisoned = stats.poisonLogic.isPlayerPoisoned();
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		level = buffer.readInt();
		saturation = buffer.readFloat();
		poisoned = buffer.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(level);
		buffer.writeFloat(saturation);
		buffer.writeBoolean(poisoned);		
	}

	public void handleClientSide() {
		ClientStats.getInstance().level = level;
		ClientStats.getInstance().saturation = saturation;
		ClientStats.getInstance().isPoisoned = poisoned;
	}

	@Override
	public IMessage onMessage(PacketUpdateClient message, MessageContext ctx) {
		message.handleClientSide();
		return null;
	}
}
