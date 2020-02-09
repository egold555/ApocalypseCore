package org.golde.apocalypsecore.common.network.packets;

import org.golde.apocalypsecore.common.ApocalypseCore;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class ACPacket <REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{
	@Override
	public REQ onMessage(REQ message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> onReceived(message, ctx.side == Side.SERVER ? ctx.getServerHandler().player : ApocalypseCore.proxy.getPlayer()));
		return null;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {};
	
	@Override
	public void fromBytes(ByteBuf buf) {};
	
	public abstract void onReceived(REQ message, EntityPlayer player);

}
