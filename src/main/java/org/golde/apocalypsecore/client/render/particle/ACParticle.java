package org.golde.apocalypsecore.client.render.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ACParticle extends Particle {

	protected ACParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		// TODO Auto-generated constructor stub
	}

	public ACParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		// TODO Auto-generated constructor stub
	}

	public void setTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureMapBlocks().registerSprite(texture);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		this.setParticleTexture(sprite);
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public String toString() {
		return "ACParticle [prevPosX=" + prevPosX + ", prevPosY=" + prevPosY + ", prevPosZ="
				+ prevPosZ + ", posX=" + posX + ", posY=" + posY + ", posZ=" + posZ + ", motionX=" + motionX
				+ ", motionY=" + motionY + ", motionZ=" + motionZ + ", onGround=" + onGround + ", canCollide="
				+ canCollide + ", isExpired=" + isExpired + ", width=" + width + ", height=" + height + ", rand=" + rand
				+ ", particleTextureIndexX=" + particleTextureIndexX + ", particleTextureIndexY="
				+ particleTextureIndexY + ", particleTextureJitterX=" + particleTextureJitterX
				+ ", particleTextureJitterY=" + particleTextureJitterY + ", particleAge=" + particleAge
				+ ", particleMaxAge=" + particleMaxAge + ", particleScale=" + particleScale + ", particleGravity="
				+ particleGravity + ", particleRed=" + particleRed + ", particleGreen=" + particleGreen
				+ ", particleBlue=" + particleBlue + ", particleAlpha=" + particleAlpha + ", particleTexture="
				+ particleTexture + ", particleAngle=" + particleAngle + ", prevParticleAngle=" + prevParticleAngle
				+ ", getFXLayer()=" + getFXLayer() + ", getClass()=" + getClass() + "]";
	}
	
	

}
