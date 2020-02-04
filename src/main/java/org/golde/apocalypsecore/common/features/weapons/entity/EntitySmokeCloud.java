package org.golde.apocalypsecore.common.features.weapons.entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.client.ACPacketParticle;
import org.golde.apocalypsecore.common.utils.ACParticleTypesServer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntitySmokeCloud extends Entity {

	public static final int MAX_PROPAGATION_DISTANCE = 10;
	public static final int MAX_PROPAGATION_DISTANCE_SQ = MAX_PROPAGATION_DISTANCE * MAX_PROPAGATION_DISTANCE;
	private static final DataParameter<Integer> CLOUD_AGE = EntityDataManager.createKey(EntitySmokeCloud.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MAX_LIFESPAN = EntityDataManager.createKey(EntitySmokeCloud.class, DataSerializers.VARINT);

	@Nullable
	protected UUID emitterID;

	private int color = 0x000000; //0xRRGGBB

	public EntitySmokeCloud(World worldIn) {
		super(worldIn);
	}

	public EntitySmokeCloud(World worldIn, int color) {
		this(worldIn);
		this.color = color;
	}

	public void setEmitter(@Nullable Entity emitter) {
		this.emitterID = emitter == null ? null : emitter.getUniqueID();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		float ageRatio = 1 - getCloudAge() / (float) getMaxLifeSpan();
		int particleAmount = 10;
		if (this.getCloudAge() % 10 == 0) {
			if (emitterID != null) {
				Entity emitter = ((WorldServer)world).getEntityFromUuid(emitterID);
				if (emitter != null) {
					this.setPosition(emitter.posX, emitter.posY, emitter.posZ);
					//ApocalypseCore.proxy.makeSmoke(world, posX, posY, posZ, color, particleAmount, MAX_PROPAGATION_DISTANCE-2, 2);
					ACPacketHandler.NETWORK.sendToAllAround(new ACPacketParticle(ACParticleTypesServer.SMOKE, posX, posY, posZ, 0, 0, 0, particleAmount, color, MAX_PROPAGATION_DISTANCE-2, 2), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 200));
				}
			}

		}

		if (!world.isRemote) {
			// sync the cloud's position with the emitter's
			if (emitterID != null) {
				Entity emitter = ((WorldServer)world).getEntityFromUuid(emitterID);
				if (emitter != null) {
					this.setPosition(emitter.posX, emitter.posY, emitter.posZ);
				}
			}
			int age = getCloudAge();
			this.setCloudAge(age + 1);
			if (age > getMaxLifeSpan()) {
				setDead();
				return;
			}
		}
		List<EntityLivingBase> entities;
		AxisAlignedBB box = new AxisAlignedBB(new BlockPos(this)).grow(10);
		if (world.isRemote) {
			entities = world.getPlayers(EntityPlayer.class, p -> p != null && getDistanceSq(p) < MAX_PROPAGATION_DISTANCE_SQ);
		}
		else {
			entities = world.getEntitiesWithinAABB(EntityLivingBase.class, box);
		}
	}

	public int getCloudAge() {
		return getDataManager().get(CLOUD_AGE);
	}

	public void setCloudAge(int cloudAge) {
		getDataManager().set(CLOUD_AGE, cloudAge);
	}

	public int getMaxLifeSpan() {
		return getDataManager().get(MAX_LIFESPAN);
	}

	public void setMaxLifespan(int lifespan) {
		getDataManager().set(MAX_LIFESPAN, lifespan);
	}

	@Override
	protected void entityInit() {
		getDataManager().register(CLOUD_AGE, 0);
		getDataManager().register(MAX_LIFESPAN, 100);
	}

	@Override
	protected void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
		this.color = compound.getInteger("color");
		this.setMaxLifespan(compound.getInteger("max_lifespan"));
		this.setCloudAge(compound.getInteger("cloud_age"));
		emitterID = compound.getUniqueId("emitter_id");
	}

	@Override
	protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {

		compound.setInteger("color", color);
		compound.setInteger("max_lifespan", getMaxLifeSpan());
		compound.setInteger("cloud_age", getCloudAge());
		if (emitterID != null) {
			compound.setUniqueId("emitter_id", emitterID);
		}
	}
}
