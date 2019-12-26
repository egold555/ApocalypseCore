package org.golde.apocalypsecore.entity;

import org.golde.apocalypsecore.features.weapons.FeatureWeapons;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySmokeBombThrowable extends EntityThrowable {
	
	private static final DataParameter<Integer> COLOR_DAMAGE = EntityDataManager.<Integer>createKey(EntitySmokeBombThrowable.class, DataSerializers.VARINT);
	
	public EntitySmokeBombThrowable(World worldIn)
    {
        super(worldIn);
    }

    public EntitySmokeBombThrowable(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntitySmokeBombThrowable(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    protected void entityInit() {
    	super.entityInit();
    	this.dataManager.register(COLOR_DAMAGE, 0);
    }
    
    public void setColorDamage(int color) {
    	this.dataManager.set(COLOR_DAMAGE, color);
    }
    
    public int getColorDamage() {
    	return this.dataManager.get(COLOR_DAMAGE);
    }

	/**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ThreadLocalRandom.current().nextDouble(-1, 1), ThreadLocalRandom.current().nextDouble(-1, 1), ThreadLocalRandom.current().nextDouble(-1, 1), Item.getIdFromItem(FeatureWeapons.smokeBomb), getColorDamage());
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result) {

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
  
            //UNTESTED
            EntitySmokeCloud cloud = new EntitySmokeCloud(world, EnumDyeColor.byDyeDamage(getColorDamage()).getColorValue());
            cloud.setEmitter(this);
            world.spawnEntity(cloud);
            cloud.onUpdate(); //IDK WHY I NEED TO CALL THIS, but update doesnt automatically start, Forge is fucking stupid
            
            hit = true;
        }
    }
    
    boolean hit = false;
    int aloha = 0;
    
    @Override
    public void onUpdate() {
    	if(hit) {
    		aloha++;
    		if(aloha >= 30) {
    			setDead();
    			hit = false;
    			aloha = 0;
    			return;
    		}
    	}
    	super.onUpdate();
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setInteger("damage", getColorDamage());
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	setColorDamage(compound.getInteger("damage"));
    }
	
}
