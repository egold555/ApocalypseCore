package org.golde.apocalypsecore.common.network.packets;

import java.awt.Point;

import org.golde.apocalypsecore.common.items._ACItemColor;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ACPacketColorGuiClosed extends ACPacket<ACPacketColorGuiClosed> {

	protected int newColor;
	protected Point colorPoint;
	protected float lightness;
	
	public ACPacketColorGuiClosed() {
	}
	
	public ACPacketColorGuiClosed(int newColor, Point colorPoint, float lightness) {
		this.newColor = newColor;
		this.colorPoint = colorPoint;
		this.lightness = lightness;
	}
	
	@Override
	public void onReceived(ACPacketColorGuiClosed message, EntityPlayer player) {
		ItemStack stack = player.getHeldItemMainhand();
		stack = _ACItemColor.setColor(stack, message.newColor);
		NBTTagCompound tag = stack.getOrCreateSubCompound("colorPicker");
		tag.setInteger("pointX", message.colorPoint.x);
		tag.setInteger("pointY", message.colorPoint.y);
		tag.setFloat("lightnessValue", message.lightness);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		newColor = buf.readInt();
		this.colorPoint = new Point(buf.readInt(), buf.readInt());
		this.lightness = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(newColor);
		buf.writeInt(colorPoint.x);
		buf.writeInt(colorPoint.y);
		buf.writeFloat(lightness);
	}

	

}
