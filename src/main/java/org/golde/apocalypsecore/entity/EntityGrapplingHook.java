package org.golde.apocalypsecore.entity;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrapplingHook extends Entity implements IProjectile {

    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData;
    private boolean inGround;
    public int canBePickedUp;
    public int hookShake;
    public Entity shootingEntity;
    private int ticksInAir;
    public double destdistX;
    public double destdistY;
    public double destdistZ;
    
    public static final DataParameter<String> SHOOT_UUID = EntityDataManager.<String>createKey(EntityGrapplingHook.class, DataSerializers.STRING);

    public EntityGrapplingHook(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    public EntityGrapplingHook(World worldIn, double x, double y, double z) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.setPosition(x, y, z);
    }

    public EntityGrapplingHook(World worldIn, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
        super(worldIn);
        this.shootingEntity = shooter;
        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.posY = shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_i1755_3_.posX - shooter.posX;
        double d1 = p_i1755_3_.getEntityBoundingBox().minY + (double) (p_i1755_3_.height / 3.0F) - this.posY;
        double d2 = p_i1755_3_.posZ - shooter.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));
            double d4 = d0 / d3;
            double d5 = d2 / d3;

            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
            float f4 = (float) (d3 * 0.20000000298023224D);

            this.shoot(d0, d1 + (double) f4, d2, p_i1755_4_, p_i1755_5_);
        }

    }

    public EntityGrapplingHook(World worldIn, EntityLivingBase shooter, float p_i1756_3_) {
        super(worldIn);
        this.shootingEntity = shooter;
        this.setShootingEntityUUID(shooter.getCachedUniqueIdString());
        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + (double) shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
        this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
        this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F));
        this.shoot(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
    }
 

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt(x * x + y * y + z * z);

        x /= (double) f2;
        y /= (double) f2;
        z /= (double) f2;
        x += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) inaccuracy;
        y += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) inaccuracy;
        z += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) inaccuracy;
        x *= (double) velocity;
        y *= (double) velocity;
        z *= (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt(x * x + z * z);

        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / 3.141592653589793D);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) f3) * 180.0D / 3.141592653589793D);
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);

            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / 3.141592653589793D);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) f) * 180.0D / 3.141592653589793D);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }

    }

    public void setShootingEntityUUID(String uuid) {
        this.dataManager.set(SHOOT_UUID, uuid);
    }

    public String getShootingEntityUUID() {
        return this.dataManager.get(SHOOT_UUID);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float blockpos = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) blockpos) * 180.0D / 3.141592653589793D);
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB vec31 = iblockstate.getSelectedBoundingBox(this.world, blockpos);

            if (vec31 != Block.NULL_AABB && vec31.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.hookShake > 0) {
            --this.hookShake;
        }

        if (this.inGround) {
            int i = block.getMetaFromState(iblockstate);

            if (block != this.inTile && i != this.inData) {
                this.inGround = false;
                this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
                this.ticksInAir = 0;
            }

            if (this.shootingEntity != null) {
                this.destdistX = this.posX - this.shootingEntity.posX;
                this.destdistY = this.posY - 1.625D - this.shootingEntity.posY;
                this.destdistZ = this.posZ - this.shootingEntity.posZ;
                if (this.destdistX <= 32.0D && this.destdistX >= -32.0D && this.destdistY <= 32.0D && this.destdistY >= -32.0D && this.destdistZ <= 32.0D && this.destdistZ >= -32.0D && this.shootingEntity != null) {
                    EntityPlayer vec3 = (EntityPlayer) this.shootingEntity;

                    vec3.motionX = this.destdistX / 5.0D;
                    vec3.motionY = this.destdistY / 5.0D;
                    vec3.motionZ = this.destdistZ / 5.0D;
                    vec3.velocityChanged = true;
                    vec3.fallDistance = 0.0F;
                } else {
                    this.setDead();
                }
            }
        } else {
            ++this.ticksInAir;
            Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);

            vec3d = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (raytraceresult != null) {
                vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            Entity entity = null;
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
            double d0 = 0.0D;

            float f1;

            for (int i = 0; i < list.size(); ++i) {
                Entity f2 = (Entity) list.get(i);

                if (f2.canBeCollidedWith() && (f2 != this.shootingEntity || this.ticksInAir >= 5)) {
                    f1 = 0.3F;
                    AxisAlignedBB f3 = f2.getEntityBoundingBox().expand((double) f1, (double) f1, (double) f1);
                    RayTraceResult f4 = f3.calculateIntercept(vec3d, vec3d1);

                    if (f4 != null) {
                        double l = vec3d.distanceTo(f4.hitVec);

                        if (l < d0 || d0 == 0.0D) {
                            entity = f2;
                            d0 = l;
                        }
                    }
                }
            }

            if (entity != null) {
                raytraceresult = new RayTraceResult(entity);
            }

            float f;

            if (raytraceresult != null && raytraceresult.entityHit == null) {
                BlockPos blockpos1 = raytraceresult.getBlockPos();

                this.xTile = blockpos1.getX();
                this.yTile = blockpos1.getY();
                this.zTile = blockpos1.getZ();
                iblockstate = this.world.getBlockState(blockpos1);
                this.inTile = iblockstate.getBlock();
                this.inData = this.inTile.getMetaFromState(iblockstate);
                this.motionX = (double) ((float) (raytraceresult.hitVec.x - this.posX));
                this.motionY = (double) ((float) (raytraceresult.hitVec.y - this.posY));
                this.motionZ = (double) ((float) (raytraceresult.hitVec.z - this.posZ));
                f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / (double) f * 0.05000000074505806D;
                this.posY -= this.motionY / (double) f * 0.05000000074505806D;
                this.posZ -= this.motionZ / (double) f * 0.05000000074505806D;
                this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inGround = true;
                this.hookShake = 7;
                if (iblockstate.getMaterial() != Material.AIR) {
                    this.inTile.onEntityCollidedWithBlock(this.world, blockpos1, iblockstate, this);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f5 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D);

            for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f5) * 180.0D / 3.141592653589793D); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            f = 0.99F;
            f1 = 0.05F;
            if (this.isInWater()) {
                for (int j = 0; j < 4; ++j) {
                    float f2 = 0.25F;

                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f2, this.posY - this.motionY * (double) f2, this.posZ - this.motionZ * (double) f2, this.motionX, this.motionY, this.motionZ, new int[0]);
                }

                f = 0.6F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= (double) f;
            this.motionY *= (double) f;
            this.motionZ *= (double) f;
            this.motionY -= (double) f1;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }

        if (this.shootingEntity != null) {
            this.setShootingEntityUUID(this.shootingEntity.getUniqueID().toString());
        }

        if (this.shootingEntity == null && this.getShootingEntityUUID() != null) {
            this.shootingEntity = this.world.getPlayerEntityByUUID(UUID.fromString(this.getShootingEntityUUID()));
        }

    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setShort("xTile", (short) this.xTile);
        tagCompound.setShort("yTile", (short) this.yTile);
        tagCompound.setShort("zTile", (short) this.zTile);
        ResourceLocation resourcelocation = (ResourceLocation) Block.REGISTRY.getNameForObject(this.inTile);

        tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        tagCompound.setByte("inData", (byte) this.inData);
        tagCompound.setByte("shake", (byte) this.hookShake);
        tagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        tagCompound.setByte("pickup", (byte) this.canBePickedUp);
        if (this.getShootingEntityUUID() != null) {
            tagCompound.setString("ShootingEntityUUID", this.getShootingEntityUUID());
        }

    }

    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        this.xTile = tagCompound.getShort("xTile");
        this.yTile = tagCompound.getShort("yTile");
        this.zTile = tagCompound.getShort("zTile");
        if (tagCompound.hasKey("inTile", 8)) {
            this.inTile = Block.getBlockFromName(tagCompound.getString("inTile"));
        } else {
            this.inTile = Block.getBlockById(tagCompound.getByte("inTile") & 255);
        }

        this.inData = tagCompound.getByte("inData") & 255;
        this.hookShake = tagCompound.getByte("shake") & 255;
        this.inGround = tagCompound.getByte("inGround") == 1;
        if (tagCompound.hasKey("pickup", 99)) {
            this.canBePickedUp = tagCompound.getByte("pickup");
        } else if (tagCompound.hasKey("player", 99)) {
            this.canBePickedUp = tagCompound.getBoolean("player") ? 1 : 0;
        }

        if (tagCompound.hasKey("ShootingEntityUUID")) {
            this.setShootingEntityUUID(tagCompound.getString("ShootingEntityUUID"));
        }

    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean canAttackWithItem() {
        return false;
    }

    public boolean getInGround() {
        return this.inGround;
    }

    @Override
    protected void entityInit() {
    	this.dataManager.register(SHOOT_UUID, "");
    }
}
