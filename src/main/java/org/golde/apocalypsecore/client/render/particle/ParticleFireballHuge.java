package org.golde.apocalypsecore.client.render.particle;

import org.golde.apocalypsecore.utils.ACParticleTypes;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFireballHuge extends ACParticle
{
    private int timeSinceStart;
    /** the maximum time for the explosion */
    private final int maximumTime = 8;

    public ParticleFireballHuge(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Renders the particle
     */
    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
    }

    @Override
    public void onUpdate()
    {
        for (int i = 0; i < 6; ++i)
        {
            double d0 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
            double d1 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
            double d2 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
            //this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d0, d1, d2, (double)((float)this.timeSinceStart / (float)this.maximumTime), 0.0D, 0.0D);
            ACParticleTypes.FIREBALL.renderParticle(d0, d1, d2, (double)((float)this.timeSinceStart / (float)this.maximumTime), 0.0D, 0.0D, 1);
        }

        ++this.timeSinceStart;

        if (this.timeSinceStart == this.maximumTime)
        {
            this.setExpired();
        }
    }
}