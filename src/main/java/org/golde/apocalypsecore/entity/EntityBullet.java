package org.golde.apocalypsecore.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import scala.collection.concurrent.Debug;

public class EntityBullet extends EntityArrow
{
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private int inData;
	protected boolean inGround;
	protected int timeInGround;
	float damage;
	int range;
	private int ticksInGround;
	private int ticksInAir;

	private int strength;

	private Potion effect;

	public EntityBullet(World a) {
		super(a);
	}

	public EntityBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	/**
	 * 
	 * @param worldIn the world
	 * @param shooter the shooter
	 * @param strength the strength: 1 = Glass can be broken, 2 = Stone can be Broken
	 */
	public EntityBullet(World worldIn, EntityLivingBase shooter, int strength) {
		super(worldIn, shooter);
		this.strength = strength;
		this.shootingEntity = shooter;

	}


	@Override
	public void onUpdate() {
		super.onUpdate();
		int x = MathHelper.floor(this.getEntityBoundingBox().minX);
		int y = MathHelper.floor(this.getEntityBoundingBox().minY);
		int z = MathHelper.floor(this.getEntityBoundingBox().minZ);
		World world = this.world;
		Entity entity = (Entity) shootingEntity;

		if (this.inGround) {
			this.world.removeEntity(this);
		}
	}

	public void setPotionEffect(Potion potion)
	{
		this.effect = potion;
	}

	public void setGunDamage(double damage)
	{
		double d = damage;
		if(this.getEntityWorld().getGameRules().getBoolean("lethalguns") == true)
		{
			d = d * 2;
		}
		this.setDamage(d);
	}

	public void setRange(int range) 
	{
		this.range = range;
	}

	public int getRange()
	{
		return this.range;
	}

	@Override
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn)
	{
		Entity entity = raytraceResultIn.entityHit;

		if(raytraceResultIn.typeOfHit == Type.BLOCK)
		{
			BlockPos pos = raytraceResultIn.getBlockPos();
			if(pos !=null) {
				if(world.getBlockState(pos) != null) {
					tryBreakBlock(pos);
				}
			}

		}

		if (entity != null)
		{

			int i = MathHelper.ceil((double)this.getDamage());

			if (this.getIsCritical())
			{
				i += this.rand.nextInt(i / 2 + 2);
			}

			DamageSource damagesource;

			if (this.shootingEntity == null)
			{
				damagesource = DamageSource.causeArrowDamage(this, this);
			}
			else
			{
				damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
			}

			if (entity.attackEntityFrom(damagesource, (float)i))
			{
				if (entity instanceof EntityLivingBase)
				{
					EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

					if (!this.world.isRemote)
					{

						if(this.effect != null)
						{
							entitylivingbase.addPotionEffect(new PotionEffect(this.effect, 80, 1));
						}
					}

					this.arrowHit(entitylivingbase);

					if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
					{
						((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

				if (!(entity instanceof EntityEnderman))
				{
					this.setDead();
				}
			}
			else
			{
				this.motionX *= -0.10000000149011612D;
				this.motionY *= -0.10000000149011612D;
				this.motionZ *= -0.10000000149011612D;
				this.rotationYaw += 180.0F;
				this.prevRotationYaw += 180.0F;
				this.ticksInAir = 0;

				if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D)
				{
					this.setDead();
				}
			}
		}
		else
		{
			BlockPos blockpos = raytraceResultIn.getBlockPos();
			this.xTile = blockpos.getX();
			this.yTile = blockpos.getY();
			this.zTile = blockpos.getZ();
			IBlockState iblockstate = this.world.getBlockState(blockpos);
			this.inTile = iblockstate.getBlock();
			this.inData = this.inTile.getMetaFromState(iblockstate);
			this.motionX = (double)((float)(raytraceResultIn.hitVec.x - this.posX));
			this.motionY = (double)((float)(raytraceResultIn.hitVec.y - this.posY));
			this.motionZ = (double)((float)(raytraceResultIn.hitVec.z - this.posZ));
			float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
			this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
			this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
			this.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			this.inGround = true;
			this.arrowShake = 7;
			this.setIsCritical(false);

			if (iblockstate.getMaterial() != Material.AIR)
			{
				this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
			}
		}
		this.setDead();
	}

	@Override
	public void onEntityUpdate() {
		double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
		if (!this.world.isRemote && (ticksExisted > this.getRange() || speed < 0.01))
		{
			this.setDead();
		}
		super.onEntityUpdate();
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}

	private void tryBreakBlock(BlockPos pos) {
		if(shootingEntity instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)shootingEntity;
			
			IBlockState state = world.getBlockState(pos);
			
			if(p.canHarvestBlock(state)) {
				
				if(state.getBlock() instanceof BlockBreakable || state.getBlock() instanceof BlockPane)
				{
					world.destroyBlock(pos, false);
				}
			}
			else {
				if(rand.nextBoolean()) {
					int id = -rand.nextInt();
					world.sendBlockBreakProgress(id, pos, -1 );
					world.sendBlockBreakProgress(id, pos, rand.nextInt(10));
				}
			}
		}
		
	}

}
