package org.golde.apocalypsecore.common.features.thirst;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.client.PacketUpdateClient;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;


public class ThirstLogic {
	public EntityPlayer player;
	public int thirstLevel;
	public float thirstSaturation;
	public float thirstExhaustion;
	public int movementSpeed; 
	public int timer; 
	public DamageSource thirstSource;
	
	public PoisonLogic poisonLogic = new PoisonLogic();
	
	public ThirstLogic(EntityPlayer player) {
		this.thirstLevel = ThirstConstants.MAX_LEVEL;
		this.thirstSaturation = ThirstConstants.MAX_SATURATION;
		this.player = player;
		this.thirstSource = new DamageThirst();
		
		readData();
	}
	
	public void onTick( ) {
		int difSet = player.world.getDifficulty().getDifficultyId();
		if (thirstExhaustion > 5f) {
			thirstExhaustion = 0f;
			if (thirstSaturation > 0f) {
				thirstSaturation = Math.max(thirstSaturation - 1f, 0);
			} else if (difSet > 0) {
				thirstLevel = Math.max(thirstLevel - 1, 0);
			}
		}
		
		if (thirstLevel <= 6) {
			player.setSprinting(false);
			if (thirstLevel <= 0) {
				timer++;
				if (timer > 200) {
					if ((player.getHealth() > 10) || (player.getHealth() > (ThirstConstants.DEATH_FROM_THIRST ? 0 : (difSet == 3 ? 0 : 1)) && difSet >= 2)) {
						player.attackEntityFrom(this.thirstSource, 1);
						player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 15 * 20, 1));
						player.world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
						timer = 0;
					}
				}
			} 
		} 
		
		this.computeExhaustion(player);
		this.poisonLogic.onTick(player);
		this.writeData();
		
		ACPacketHandler.NETWORK.sendTo(new PacketUpdateClient(this), (EntityPlayerMP) player);
	}
	
	public void readData() {
		if (player != null) {
			NBTTagCompound oldnbt = player.getEntityData();
			NBTTagCompound nbt = oldnbt.getCompoundTag(ApocalypseCore.MODID);
			if (nbt.hasKey("level")) {
				thirstLevel = nbt.getInteger("level");
				thirstExhaustion = nbt.getFloat("exhaustion");
				thirstSaturation = nbt.getFloat("saturation");
				timer = nbt.getInteger("timer");
				
				poisonLogic.changeValues(nbt.getBoolean("poisoned"), nbt.getInteger("poisonTime"));
			}
		}
	}

	public void writeData() {
		if (player != null) {
			NBTTagCompound oldNBT = player.getEntityData();
			NBTTagCompound nbt = oldNBT.getCompoundTag(ApocalypseCore.MODID);
			if (!oldNBT.hasKey(ApocalypseCore.MODID)) {
				oldNBT.setTag(ApocalypseCore.MODID, nbt);
			}
			nbt.setInteger("level", thirstLevel);
			nbt.setFloat("exhaustion", thirstExhaustion);
			nbt.setFloat("saturation", thirstSaturation);
			nbt.setInteger("timer", timer);

			//nbt.setBoolean("poisoned", poisonLogic.isPlayerPoisoned());
			//nbt.setInteger("poisonTime", poisonLogic.getPoisonTimeRemaining());
		} 
	}
	
	public void computeExhaustion(EntityPlayer player) {
		int movement = player.isRiding() ? 0 : movementSpeed;
		float exhaustAmplifier = isNight(player) ? ThirstConstants.NIGHT_RATE : 1;
		float multiplier = getCurrentBiome(player).equals("Desert") ? ThirstConstants.DESERT_RATE : 1;
		if (player.isInsideOfMaterial(Material.WATER)) {
			if (movement > 0) {
				addExhaustion(ThirstConstants.IN_WATER_RATE * movement * 0.003F * exhaustAmplifier);
			}
		} else if (player.isInWater()) {
			if (movement > 0) {
				addExhaustion(ThirstConstants.IN_WATER_RATE * movement * 0.003F * exhaustAmplifier);
			}
		} else if (player.onGround) {
			if (movement > 0) {
				if (player.isSprinting()) {
					addExhaustion(ThirstConstants.RUNNING_RATE * movement * 0.018F * multiplier * exhaustAmplifier);
				} else {
					addExhaustion(ThirstConstants.WALKING_RATE * movement * 0.018F * multiplier * exhaustAmplifier);
				}
			}
		} else if (!player.onGround && !player.isRiding()) {
			if (player.isSprinting()) {
				addExhaustion((ThirstConstants.JUMP_RATE * 2) * multiplier * exhaustAmplifier);
			} else {
				addExhaustion(ThirstConstants.JUMP_RATE * multiplier * exhaustAmplifier);
			}
		}
	}

	public boolean isNight(EntityPlayer player) {
		long worldTime = player.world.getWorldTime() % 24000;
		return worldTime >= 13000;
	}
	
	public static String getCurrentBiome(EntityPlayer player) {
		return player.world.getBiomeForCoordsBody(player.getPosition()).getBiomeName();
	}
	
	public void addStats(int thirst, float sat) {
		thirstLevel = Math.min(thirst + thirstLevel, 20);
		thirstSaturation = Math.min(thirstSaturation + (thirst * sat * 2.0F), thirstLevel);
	}
	
	public void addExhaustion(float exh) {
		thirstExhaustion = Math.min(thirstExhaustion + exh, 40F);
	}
	
	public void setStats(int level, float sat) {
		this.thirstLevel = level;
		this.thirstSaturation = sat;
	}
	
	public boolean isThirstAllowedByDifficulty() {
		return player.world.getDifficulty().getDifficultyId() > 0;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Level = %d, Saturation = %f, Exhaustion = %f", player.getDisplayName(), thirstLevel, thirstSaturation, thirstExhaustion);
	}
	
	public static class DamageThirst extends DamageSource {
		public DamageThirst() {
			super("thirst");
			setDamageBypassesArmor();
			setDamageIsAbsolute();
		}
		
		@Override
		public ITextComponent getDeathMessage(EntityLivingBase entity) {
			if(entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entity;
				return new TextComponentString(player.getDisplayName() + "'s body is now made up of 0% water!");
			}
			return super.getDeathMessage(entity);
		}
	}
}