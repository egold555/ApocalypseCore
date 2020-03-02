package org.golde.apocalypsecore.common.network.packets;

import java.awt.Point;

import org.golde.apocalypsecore.common.items._ACItemColor;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ACPacketGraffitiText extends ACPacket<ACPacketGraffitiText> {

	private String text = "";
	
	public ACPacketGraffitiText() {
		// TODO Auto-generated constructor stub
	}
	
	public ACPacketGraffitiText(String text) {
		this.text = text;
	}

	@Override
	public void onReceived(ACPacketGraffitiText message, EntityPlayer player) {
		ItemStack stack = player.getHeldItemMainhand();
		NBTTagCompound tag = stack.getTagCompound();
		tag.setString("tempText", text);
		
		System.out.println("IS CLIENT: " + player.world.isRemote);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.text = this.readString(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		this.writeString(buf, this.text);
	}
	
}
