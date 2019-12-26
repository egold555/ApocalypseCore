package org.golde.apocalypsecore.client.render.particle;

import org.golde.apocalypsecore.client.events.TextureStitch;
import org.golde.apocalypsecore.common.ApocalypseCore;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleGasSmoke extends ACParticle {

	public float particleMaxAlpha;

	public ParticleGasSmoke(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float red, float green, float blue, float scale) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn);
		//this.setParticleTexture(TextureStitch.SMOKE_SPRITE);
		particleTexture = TextureStitch.SMOKE_SPRITE;
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
		this.particleAlpha = 0;
		this.particleMaxAlpha = 1;
		this.giveRandomMotion(0, 0, 0);
		this.motionY *= 0.5;
		this.particleScale = scale;
		this.setMaxAge(100);
	}

	public void giveRandomMotion(float xSpeedIn, float ySpeedIn, float zSpeedIn) {
		this.motionX = xSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
		this.motionY = ySpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
		this.motionZ = zSpeedIn + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
		float f = (float) (Math.random() + Math.random() + 1.0D) * 0.15F;
		float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.motionX = this.motionX / (double) f1 * (double) f * 0.4000000059604645D;
		this.motionY = this.motionY / (double) f1 * (double) f * 0.4000000059604645D + 0.10000000149011612D;
		this.motionZ = this.motionZ / (double) f1 * (double) f * 0.4000000059604645D;
	}

	@Override
	public boolean shouldDisableDepth() {
		return true;
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = (float)this.particleTextureIndexX / 16.0F;
		float f1 = f + 0.0624375F;
		float f2 = (float)this.particleTextureIndexY / 16.0F;
		float f3 = f2 + 0.0624375F;
		float f4 = 0.1F * this.particleScale;

		if (this.particleTexture != null)
		{
			f = this.particleTexture.getMinU();
			f1 = this.particleTexture.getMaxU();
			f2 = this.particleTexture.getMinV();
			f3 = this.particleTexture.getMaxV();
		}

		float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
		float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
		float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
		final int brightness = 15728640; //THIS line changed from the normal particle renderer
		int j = brightness >> 16 & 65535;
		int k = brightness & 65535;
		Vec3d[] avec3d = new Vec3d[] {new Vec3d((double)(-rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(-rotationYZ * f4 - rotationXZ * f4)), new Vec3d((double)(-rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(-rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(rotationYZ * f4 - rotationXZ * f4))};

		if (this.particleAngle != 0.0F)
		{
			float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
			float f9 = MathHelper.cos(f8 * 0.5F);
			float f10 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.x;
			float f11 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.y;
			float f12 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.z;
			Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);

			for (int l = 0; l < 4; ++l)
			{
				avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double)(2.0F * f9)));
			}
		}

		buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.particleAlpha *= .99;
			if (this.particleAlpha <= 0.01)
				this.setExpired();
		} else
			particleAlpha = Math.min((float) particleAge / (particleMaxAge / 2) * particleMaxAlpha, 1);

		this.motionY += 0.0005D;
		this.move(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}
