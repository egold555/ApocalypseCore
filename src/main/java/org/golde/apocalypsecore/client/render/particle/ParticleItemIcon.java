package org.golde.apocalypsecore.client.render.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Barrier;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleItemIcon extends ACParticle {

	public ParticleItemIcon(World worldIn, double x, double y, double z, Item item)
    {
        super(worldIn, x, y, z, 0.0D, 0.0D, 0.0D);
        this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item));
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.particleGravity = 0.0F;
        this.particleMaxAge = 80;
    }

    /**
     * Renders the particle
     */
	@Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f = this.particleTexture.getMinU();
        float f1 = this.particleTexture.getMaxU();
        float f2 = this.particleTexture.getMinV();
        float f3 = this.particleTexture.getMaxV();
        float f4 = 0.5F;
        float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        int i = this.getBrightnessForRender(partialTicks);
        int j = i >> 16 & 65535;
        int k = i & 65535;
        buffer.pos((double)(f5 - rotationX * 0.5F - rotationXY * 0.5F), (double)(f6 - rotationZ * 0.5F), (double)(f7 - rotationYZ * 0.5F - rotationXZ * 0.5F)).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos((double)(f5 - rotationX * 0.5F + rotationXY * 0.5F), (double)(f6 + rotationZ * 0.5F), (double)(f7 - rotationYZ * 0.5F + rotationXZ * 0.5F)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos((double)(f5 + rotationX * 0.5F + rotationXY * 0.5F), (double)(f6 + rotationZ * 0.5F), (double)(f7 + rotationYZ * 0.5F + rotationXZ * 0.5F)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos((double)(f5 + rotationX * 0.5F - rotationXY * 0.5F), (double)(f6 - rotationZ * 0.5F), (double)(f7 + rotationYZ * 0.5F - rotationXZ * 0.5F)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
    }
	
}
