package org.golde.apocalypsecore.common.features.misc.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDeadBody extends EntityLiving {

	private static final DataParameter<String> DB_NAME = EntityDataManager.<String>createKey(EntityDeadBody.class, DataSerializers.STRING);
	private static final DataParameter<String> BB_UUID = EntityDataManager.<String>createKey(EntityDeadBody.class, DataSerializers.STRING);
	
	public EntityDeadBody(World worldIn) {
		super(worldIn);
		this.setEntityInvulnerable(true);
	}

	public EntityDeadBody(World worldIn, double posX, double posY, double posZ)
	{
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}
	
	public EntityDeadBody(World worldIn, double posX, double posY, double posZ, String name, UUID uuid)
	{
		this(worldIn);
		this.setPosition(posX, posY, posZ);
		
		this.getDataManager().set(DB_NAME, name);
		this.getDataManager().set(BB_UUID, uuid.toString());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(DB_NAME, "");
		this.getDataManager().register(BB_UUID, "");
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entityIn)
	{
	}

	@Override
	public boolean attackable() {
		return false;
	}

	@Override
	public void onKillCommand()
	{
		this.setDead();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();



	}

	@Override
	protected void collideWithNearbyEntities()
	{

	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance)
	{
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

		if (Double.isNaN(d0) || d0 == 0.0D)
		{
			d0 = 4.0D;
		}

		d0 = d0 * 64.0D;
		return distance < d0 * d0;
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (!this.world.isRemote && !this.isDead)
		{
			if (DamageSource.OUT_OF_WORLD.equals(source))
			{
				this.setDead();
				return false;
			}
			else if (!this.isEntityInvulnerable(source))
			{
				if (source.isExplosion())
				{
					this.dropContents();
					this.setDead();
					return false;
				}
				else
				{

					if (source.isCreativePlayer())
					{
						this.setDead();
						return false;
					}
					else
					{

						return false;
					}

				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	private void dropContents() {
		// TODO Auto-generated method stub

	}
	
	@Nullable
	public String getDbName() {
		String s = this.getDataManager().get(DB_NAME);
		if(s == null || s.isEmpty()) {
			return null;
		}
		
		return s;
	}
	
	@Nullable
	public UUID getDbUuid() {
		String s = this.getDataManager().get(BB_UUID);
		if(s == null || s.isEmpty()) {
			return null;
		}
		try {
			return UUID.fromString(s);
		}
		catch(IllegalArgumentException weDontCare) {
			return null;
		}
		
	}

}
