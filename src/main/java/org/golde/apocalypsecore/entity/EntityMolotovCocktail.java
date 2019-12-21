package org.golde.apocalypsecore.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMolotovCocktail extends EntityThrowable {

	public EntityMolotovCocktail(World worldIn)
	{
		super(worldIn);
	}

	public EntityMolotovCocktail(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	public EntityMolotovCocktail(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {

		if (!this.world.isRemote)
		{
			this.world.setEntityState(this, (byte)3);
			setDead();

			int range1 = rand.nextInt(5) + 3;
			int range2 = rand.nextInt(5) + 3;

			for(int x = -range1; x < range1; x++) {
				for(int z = -range2; z < range2; z++) {
					if(rand.nextBoolean()) {
						BlockPos p = new BlockPos(this.posX + x, this.posY, this.posZ + z);
						if(world.getBlockState(p) == Blocks.AIR.getDefaultState()) {
							world.setBlockState(p, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 32; ++i)
			{
				this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, rand.nextDouble() - 0.5, rand.nextDouble() - 0.5, rand.nextDouble() - 0.5);
				this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY, this.posZ, rand.nextDouble() - 0.5, rand.nextDouble() - 0.5, rand.nextDouble() - 0.5);
			}
		}
	}
}
