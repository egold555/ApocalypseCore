package org.golde.apocalypsecore.common.entity;

import org.golde.apocalypsecore.common.features.misc.FeatureMisc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFallingLootCrate extends Entity
{
	public int fallTime = 1;
	protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.<BlockPos>createKey(EntityFallingLootCrate.class, DataSerializers.BLOCK_POS);

	private IBlockState fallTile = FeatureMisc.lootChest.getDefaultState();

	public EntityFallingLootCrate(World worldIn)
	{
		super(worldIn);
	}

	public EntityFallingLootCrate(World worldIn, double x, double y, double z)
	{
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.setPosition(x, y + (double)((1.0F - this.height) / 2.0F), z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.setOrigin(new BlockPos(this));
	}

	/**
	 * Returns true if it's possible to attack this entity with an item.
	 */
	@Override
	public boolean canBeAttackedWithItem()
	{
		return false;
	}

	public void setOrigin(BlockPos p_184530_1_)
	{
		this.dataManager.set(ORIGIN, p_184530_1_);
	}

	@SideOnly(Side.CLIENT)
	public BlockPos getOrigin()
	{
		return (BlockPos)this.dataManager.get(ORIGIN);
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		//idk its sometimes null??
//		if(fallTile == null) {
//			this.fallTile = Blocks.RED_GLAZED_TERRACOTTA.getDefaultState();
//		}
		Block block = this.fallTile.getBlock();


		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.fallTime++ == 0)
		{
			BlockPos blockpos = new BlockPos(this);

			if (this.world.getBlockState(blockpos).getBlock() == block)
			{
				this.world.setBlockToAir(blockpos);
			}
			else if (!this.world.isRemote)
			{
				this.setDead();
				return;
			}
		}

		if (!this.hasNoGravity())
		{
			this.motionY -= 0.03999999910593033D;
		}

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

		if (!this.world.isRemote)
		{
			BlockPos blockpos1 = new BlockPos(this);

			double d0 = this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ;


			if (!this.onGround)
			{
				if (this.fallTime > 100 && !this.world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.fallTime > 600)
				{
					this.setDead();
				}
			}
			else
			{
				IBlockState iblockstate = this.world.getBlockState(blockpos1);

				if (this.world.isAirBlock(new BlockPos(this.posX, this.posY - 0.009999999776482582D, this.posZ))) { //Forge: Don't indent below.
					if (BlockFalling.canFallThrough(this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.009999999776482582D, this.posZ))))
					{
						this.onGround = false;
						return;
					}
				}

				this.motionX *= 0.699999988079071D;
				this.motionZ *= 0.699999988079071D;
				this.motionY *= -0.5D;

				if (iblockstate.getBlock() != Blocks.PISTON_EXTENSION)
				{
					this.setDead();


					if (this.world.mayPlace(block, blockpos1, true, EnumFacing.UP, (Entity)null) && (!BlockFalling.canFallThrough(this.world.getBlockState(blockpos1.down()))) && this.world.setBlockState(blockpos1, this.fallTile, 3))
					{



					}



				}
			}
		}

		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("Time", this.fallTime);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		this.fallTime = compound.getInteger("Time");
	}


	/**
	 * Return whether this entity should be rendered as on fire.
	 */
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	public boolean ignoreItemEntityData()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public World getWorldObj()
	{
		return this.world;
	}
}