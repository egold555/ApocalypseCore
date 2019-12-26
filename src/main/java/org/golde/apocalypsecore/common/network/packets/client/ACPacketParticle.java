package org.golde.apocalypsecore.common.network.packets.client;

import java.util.Arrays;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.utils.ACParticleTypes;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ACPacketParticle implements IMessage, IMessageHandler<ACPacketParticle, IMessage> {

	private ACParticleTypes particleType;
	private double xCoord;
	private double yCoord;
	private double zCoord;
	private double xSpeed; 
	private double ySpeed; 
	private double zSpeed; 
	private int count;
    private int[] particleArguments;
    
    public ACPacketParticle()
    {
    }
    
    public ACPacketParticle(ACParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int countIn) {
    	this(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, countIn, new int[0]);
    }

    public ACPacketParticle(ACParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... argumentsIn)
    {
        this.particleType = particleType;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.zSpeed = zSpeed;
        this.count = count;
        this.particleArguments = argumentsIn;
    }
    
	@Override
	public String toString() {
		return "ACPacketParticle [particleType=" + particleType + ", xCoord=" + xCoord + ", yCoord=" + yCoord
				+ ", zCoord=" + zCoord + ", xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + ", zSpeed=" + zSpeed + ", count="
				+ count + ", particleArguments=" + Arrays.toString(particleArguments) + "]";
	}

	@Override
	public IMessage onMessage(ACPacketParticle msg, MessageContext ctx) {
		
		msg.particleType.renderParticle(msg.xCoord, msg.yCoord, msg.zCoord, msg.xSpeed, msg.ySpeed, msg.zSpeed, msg.count, msg.particleArguments);
		
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.particleType = ACParticleTypes.getParticleFromId(buf.readInt());
		
        this.xCoord = buf.readDouble();
        this.yCoord = buf.readDouble();
        this.zCoord = buf.readDouble();
        this.xSpeed = buf.readDouble();
        this.ySpeed = buf.readDouble();
        this.zSpeed = buf.readDouble();
        this.count = buf.readInt();
        int i = this.particleType.getArgumentCount();
        this.particleArguments = new int[i];

        for (int j = 0; j < i; ++j)
        {
            this.particleArguments[j] = buf.readInt();
        }
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.particleType.getParticleID());
        buf.writeDouble(this.xCoord);
        buf.writeDouble(this.yCoord);
        buf.writeDouble(this.zCoord);
        buf.writeDouble(this.xSpeed);
        buf.writeDouble(this.ySpeed);
        buf.writeDouble(this.zSpeed);
        buf.writeInt(this.count);
        int i = this.particleType.getArgumentCount();

        for (int j = 0; j < i; ++j)
        {
            buf.writeInt(this.particleArguments[j]);
        }
	}
	
	

}
