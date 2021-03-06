package org.golde.apocalypsecore.client;

import org.golde.apocalypsecore.client.render.particle.ParticleFireballHuge;
import org.golde.apocalypsecore.client.render.particle.ParticleFireballLarge;
import org.golde.apocalypsecore.client.render.particle.ParticleGasSmoke;
import org.golde.apocalypsecore.client.render.particle.ParticleItemIcon;
import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.proxy.ClientProxy;
import org.golde.apocalypsecore.common.utils.ACParticleTypesServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public enum ACParticleTypesClient {
	ERROR {
		@Override
		protected void onRender(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args) {
			for (int i = 0; i < count; i++) {
				ParticleItemIcon p = new ParticleItemIcon(world, xCoord, yCoord, zCoord, Item.getItemFromBlock(Blocks.BARRIER));
				this.addToRenderer(p);
			}
			
		}
	}, 

	// /acparticle SMOKE ~ ~ ~ 0 0 0 10 16711935 8 2
	SMOKE(3) {
		@Override
		protected void onRender(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args) {
			int color = args[0];
			int radX = args[1];
			int radY = args[2];
			
			float b = (color & 0xFF) / 255F;
	        float g = (color >> 8 & 0xFF) / 255F;
	        float r = (color >> 16 & 0xFF) / 255F;
	        
	        for (int i = 0; i < count; i++) {
	            ClientProxy.particleCount += world.rand.nextInt(3);
	            if (ClientProxy.particleCount % (Minecraft.getMinecraft().gameSettings.particleSetting == 0 ? 1 : 2 * Minecraft.getMinecraft().gameSettings.particleSetting) == 0) {
	                double posX = xCoord + world.rand.nextGaussian() * radX % radX;
	                double posY = yCoord + world.rand.nextGaussian() * radY % radY;
	                double posZ = zCoord + world.rand.nextGaussian() * radX % radX;
	                ParticleGasSmoke particle = new ParticleGasSmoke(world, posX, posY, posZ, r, g, b, (float) (55 + 20 * world.rand.nextGaussian()));

	                this.addToRenderer(particle);
	            }
	        }
		}
	}, 

	FIREBALL {
		@Override
		protected void onRender(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args) {
			for (int i = 0; i < count; i++) {
	            ParticleFireballLarge p = new ParticleFireballLarge(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
	            this.addToRenderer(p);
	        }
		}
	},
	
	FIREBALL_HUGE {
		@Override
		protected void onRender(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args) {
			for (int i = 0; i < count; i++) {
	            ParticleFireballHuge p = new ParticleFireballHuge(world, xCoord, yCoord, zCoord);
	            this.addToRenderer(p);
	        }
		}
	};

	private final int argCount;
	ACParticleTypesClient() {
		this(0);
	}

	ACParticleTypesClient(int argCount) {
		this.argCount = argCount;
	}

	public final int getArgumentCount() {
		return argCount;
	}

	public final int getParticleID() {
		return this.ordinal(); //seems to work fine
	}
	
	protected final void addToRenderer(Particle p) {
		Minecraft.getMinecraft().effectRenderer.addEffect(p); 
	}
	
	public final void renderParticle(double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args) {
		this.onRender(Minecraft.getMinecraft().world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, count, args);
	}

	protected abstract void onRender(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int count, int... args);

	public static ACParticleTypesClient getParticleFromServer(ACParticleTypesServer p) {
		if(p == null) {
			ApocalypseCore.logger.error("Recieved particle NULL from the server, and it does not exist on the client! Returning ERROR particle to prevent crash!");
			return ACParticleTypesClient.ERROR;
		}
		try {
			return ACParticleTypesClient.valueOf(p.name());
		}
		catch(Throwable e) {
			ApocalypseCore.logger.error("Recieved particle name '" + p.name() + "' from the server, and it does not exist on the client! Returning ERROR particle to prevent crash!");
			return ACParticleTypesClient.ERROR;
		}
		
	}

}
