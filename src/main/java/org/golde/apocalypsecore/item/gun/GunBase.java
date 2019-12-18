package org.golde.apocalypsecore.item.gun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.ACTabs;
import org.golde.apocalypsecore.entity.EntityBullet;
import org.golde.apocalypsecore.item._core._ACItem;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunBase extends _ACItem {

	public int Recoil;

	int Firerate;
	public int clipsize;
	int ReloadTime;
	int AutoFiremode;
	int SingleFiremode;
	float damage;
	int range;
	public Item ammo;
	int type;
	int strength;

	public ResourceLocation texture;

	float IncreaseDamageAmount = damage + 3f;
	int LowRecoil = Recoil / 2;

	private List < String > attachmentNames = new ArrayList < String > ();

	String description;
	String ammoName;

	/**
	 * 
	 * @param name
	 * @param fireRate
	 * @param ammocap
	 * @param reloadtm
	 * @param recoil
	 * @param bulletDamage
	 * @param bulletDuration
	 * @param ammunition
	 * @param guntype
	 * @param desc
	 * @param ammoName
	 * @param strength
	 */
	public GunBase(String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype, String desc, String ammoName, int strength) {
		super(name);
		setMaxStackSize(1);

		this.Firerate = fireRate;
		this.clipsize = ammocap;
		this.ReloadTime = reloadtm;
		this.damage = bulletDamage;
		this.range = bulletDuration;
		this.ammo = ammunition;
		this.Recoil = recoil;

		this.description = desc;
		this.ammoName = ammoName;
		this.strength = strength;

		setCreativeTab(ACTabs.WEAPONS);
		
		this.addPropertyOverride(new ResourceLocation("aiming"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}
				NBTTagCompound nbt = checkNBTTags(stack);
				float j = nbt.getBoolean("ADS") ? 1.0F : 0.0F;
				return j;
			}
		});

		this.addPropertyOverride(new ResourceLocation("reloading"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}
				NBTTagCompound nbt = checkNBTTags(stack);
				float j = nbt.getBoolean("reload") ? 1.0F : 0.0F;
				return j;
			}
		});

		this.addPropertyOverride(new ResourceLocation("running"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}
				NBTTagCompound nbt = checkNBTTags(stack);
				float j = nbt.getBoolean("running") ? 1.0F : 0.0F;
				return j;
			}
		});

	}

	public float getGunDamage() {
		return this.damage;
	}

	//	public void addModification(GunAttachments attachment, ItemStack stack)
	//	{
	//		NBTTagCompound nbt = checkNBTTags(stack);
	//		
	//		nbt.setBoolean(attachment.toString(), true);
	//	}
	//	
	//	public void removeModification(GunAttachments attachment, ItemStack stack)
	//	{
	//		NBTTagCompound nbt = checkNBTTags(stack);
	//		
	//		nbt.setBoolean(attachment.toString(), false);
	//	}

	public List < String > getModifications(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);
		List < String > Attachments = new ArrayList < String > ();

		//		if(nbt.getBoolean(GunAttachments.INCREASEDAMAGE.toString()) == true)
		//		{
		//			Attachments.add("Upgraded Caliber");
		//		}
		//		if(nbt.getBoolean(GunAttachments.LOWRECOIL.toString()) == true)
		//		{
		//			Attachments.add("Recoil Control");
		//		}
		//		if(nbt.getBoolean(GunAttachments.POTIONEFFECT.toString()) == true)
		//		{
		//			Attachments.add("Bullet Effect");
		//		}
		//		if(nbt.getBoolean(GunAttachments.STATTRACK.toString()) == true)
		//		{
		//			Attachments.add("StatTrack");
		//		}

		return Attachments;
	}

	public void addPotionEffect(Potion potion, ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		nbt.setInteger("PotionID", potion.getIdFromPotion(potion));
	}

	public Potion getBoundPotionEffect(ItemStack stack) {

		NBTTagCompound nbt = checkNBTTags(stack);

		int potionID = nbt.getInteger("PotionID");

		return Potion.getPotionById(potionID);
	}

	public Boolean hasBoundPotionEffect(ItemStack stack) {

		NBTTagCompound nbt = checkNBTTags(stack);

		if (nbt.hasKey("PotionID")) {
			return true;
		}

		return false;
	}

	public void addStatTrack(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		nbt.setInteger("StatTrack", 0);
	}

	public void removeStatTrack(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		nbt.removeTag("StatTrack");
	}

	public Boolean hasStatTrack(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);
		return nbt.hasKey("StatTrack");
	}

	public int getStatTrackCount(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		return nbt.getInteger("StatTrack");
	}

	public void addStatTrackKill(int killAmount, ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		nbt.setInteger("StatTrack", getStatTrackCount(stack) + killAmount);
	}

	public void resetStatTrackKills(ItemStack stack) {
		NBTTagCompound nbt = checkNBTTags(stack);

		nbt.setInteger("StatTrack", 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List < String > tooltip, ITooltipFlag flagIn) {

		NBTTagCompound nbt = checkNBTTags(stack);
		tooltip.add(TextFormatting.YELLOW + "Show Information (LSHIFT)");
		tooltip.add(TextFormatting.YELLOW + "Show Recoil Patterns (CTRL)");
		tooltip.add(TextFormatting.YELLOW + "Weapon Description (RSHIFT)");
		tooltip.add(TextFormatting.GREEN + "----------------------");

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			tooltip.add(TextFormatting.YELLOW + "Weapon Information <!>");
			//			if(nbt.getBoolean(GunAttachments.INCREASEDAMAGE.toString()) == true)
			//			{
			//				tooltip.add(TextFormatting.BLUE + "Impact: " + TextFormatting.GREEN + this.IncreaseDamageAmount);
			//			}
			//			else
			//			{
			//				tooltip.add(TextFormatting.BLUE + "Impact: " + TextFormatting.GREEN + damage);
			//			}
			tooltip.add(TextFormatting.BLUE + "Range:  " + TextFormatting.GREEN + range);
			tooltip.add(TextFormatting.BLUE + "Clipsize: " + TextFormatting.GREEN + clipsize);
			tooltip.add(TextFormatting.BLUE + "Ammunition: " + TextFormatting.GREEN + ammoName);
			tooltip.add("");
			tooltip.add(TextFormatting.YELLOW + "Weapon Modifications <!>");
			for (String s: getModifications(stack)) {
				tooltip.add(TextFormatting.GREEN + "- " + TextFormatting.AQUA + s);
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
			tooltip.add(TextFormatting.RED + "Recoil Patterns <!>");

			//			if(nbt.getBoolean(GunAttachments.LOWRECOIL.toString()) == true)
			//			{
			//				tooltip.add(TextFormatting.RED + "Verticle Recoil: " + this.getRecoil() / 2);
			//				int horiz = this.getRecoil() / 2 - 1;
			//				int vertical = this.getRecoil() / 2 + 1;
			//				tooltip.add(TextFormatting.RED + "Horizontal Recoil <Left> : " + horiz);
			//				tooltip.add(TextFormatting.RED + "Horizontal Recoil <Right>: "+ vertical);
			//			}
			//			else
			//			{
			//				tooltip.add(TextFormatting.RED + "Verticle Recoil: " + this.getRecoil());
			//				tooltip.add(TextFormatting.RED + "Horizontal Recoil <Left> : " + (this.getRecoil() - 1));
			//				tooltip.add(TextFormatting.RED + "Horizontal Recoil <Right>: " + (this.getRecoil() + 1));
			//			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			tooltip.add(TextFormatting.RED + "Weapon Description <!>");
			tooltip.add(TextFormatting.AQUA + I18n.format(this.description));
		}


		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	/*
	 * Sets the Recoil
	 */

	public void setRecoil(int amt) {
		this.Recoil = amt;
	}

	/*
	 * Returns the recoil
	 */

	public int getRecoil() {
		return this.Recoil;
	}

	/*
	 * Initiates the Verticle and Horizontal Recoil
	 */
	public void doRecoil(EntityPlayer p) {
		Random rand = new Random();
		int yawWay = rand.nextInt(2);

		int recoil = this.Recoil;

		//EntityPlayerSP sp = (EntityPlayerSP) p;

		p.rotationPitch += -this.Recoil;



		for (String s: getModifications(p.getHeldItemMainhand())) {
			if (s == "Recoil Control") {
				recoil = this.LowRecoil;
			}
		}

		if (yawWay == 1) {
			p.rotationYaw += this.Recoil + 1;
		}

		if (yawWay == 2) {
			p.rotationYaw += -this.Recoil + 1;
		}
	}
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		if (!nbt.hasKey("ADS")) {
			nbt.setBoolean("ADS", false);
		}

		if (!nbt.hasKey("reload")) {
			nbt.setBoolean("reload", false);
		}

		if (!nbt.hasKey("running")) {
			nbt.setBoolean("running", false);
		}

		return nbt;
	}

	public boolean isAiming(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		return nbt.getBoolean("ADS");
	}

	public void doAim(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		nbt.setBoolean("ADS", !nbt.getBoolean("ADS"));
	}



	/*
	 * Checks for the gun states (NBT)
	 */
	public void checkStates(ItemStack stack, World worldIn, Entity entityIn) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		EntityPlayer playerIn = (EntityPlayer) entityIn;

//		CooldownTracker cd = playerIn.getCooldownTracker();
//		if (!cd.hasCooldown(this) && nbt.getBoolean("reload") == true && stack.getItem() instanceof GunBase) {
//			nbt.setBoolean("reload", false);
//		}

		if (playerIn.isSprinting()) {
			if (!nbt.getBoolean("reload")) {
				nbt.setBoolean("running", true);
				nbt.setBoolean("ADS", false);
			}

		} else {
			nbt.setBoolean("running", false);
		}
	}

	public void playDryFireSound(EntityPlayer playerIn) {

	}

	public void playShootSound(EntityPlayer playerIn) {
		World worldIn = playerIn.getEntityWorld();

		//		if(this.ammo == ModItems.DMRCLIP || this.ammo == ModItems.SNIPERCLIP)
		//		{
		//			worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_SNIPER_SHOOT, SoundCategory.PLAYERS, 1, 1);
		//		}
		//		
		//		if(this.ammo == ModItems.RIFLE56 || this.ammo == ModItems.RIFLE762)
		//		{
		//			worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.PLAYERS, 1, 1);
		//		}
		//		
		//		if(this.ammo == ModItems.SMG45)
		//		{
		//			worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.PLAYERS, 1, 1);
		//		}
		//		
		//		if(this.ammo == ModItems.PISTOL9mm)
		//		{
		//			worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.G17_SHOOT, SoundCategory.PLAYERS, 1, 1);
		//		}

	}

	public boolean isRunning(ItemStack stack) {

		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		return nbt.getBoolean("running");
	}

	/*
	 * Shoots the gun
	 */

	public void shootShotgun(World worldIn, EntityPlayer playerIn, ItemStack itemstack, float spread) {
		boolean attach = false;

		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		if (!playerIn.capabilities.isCreativeMode) {
			if (itemstack.isItemDamaged()) {
				if (itemstack.getItemDamage() == clipsize) {
					Reload(playerIn, itemstack, nbt);
				} 
				else {
					//playerIn.getCooldownTracker().setCooldown(this, Firerate);
					if (!worldIn.isRemote) {
						if (!playerIn.isSprinting()) {
							for (int i = 0; i < 6; i++) {
								EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
								if (this.hasBoundPotionEffect(itemstack)) {
									entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
								}
								entity.setGunDamage((double) this.damage);
								for (String s: getModifications(itemstack)) {
									if (s == "Upgraded Caliber") {
										entity.setGunDamage((double) this.IncreaseDamageAmount);
										break;
									}
								}
								Random r = new Random();
								int addedSpread = r.nextInt((int) spread);
								entity.setPosition(entity.getPosition().getX() + addedSpread, entity.getPosition().getY() + addedSpread, entity.getPosition().getZ());
								entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
								entity.setRange(this.range);
								entity.shootingEntity = playerIn;
								worldIn.spawnEntity(entity);
							}

							itemstack.damageItem(1, playerIn);
							playShootSound(playerIn);

						}



					}


				}

			} 
			else {
				//First Bullet
				//playerIn.getCooldownTracker().setCooldown(this, Firerate);
				if (!worldIn.isRemote) {
					if (!playerIn.isSprinting()) {
						for (int i = 0; i < 3; i++) {
							EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
							if (this.hasBoundPotionEffect(itemstack)) {
								entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
							}

							entity.setGunDamage((double) this.damage);
							for (String s: getModifications(itemstack)) {
								if (s == "Upgraded Caliber") {
									entity.setGunDamage((double) this.IncreaseDamageAmount);
									break;
								}
							}
							Random r = new Random();
							int addedSpread = r.nextInt((int) spread);
							entity.setPosition(entity.getPosition().getX() + addedSpread, entity.getPosition().getY() + addedSpread, entity.getPosition().getZ());
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
							entity.setRange(this.range);
							entity.shootingEntity = playerIn;
							worldIn.spawnEntity(entity);
						}

						itemstack.damageItem(1, playerIn);
						playShootSound(playerIn);
					}

				}

			}
		} 
		else {

			//Creative Move
			playerIn.getCooldownTracker().setCooldown(this, Firerate);
			if (!worldIn.isRemote) {
				if (!playerIn.isSprinting()) {
					for (int i = 0; i < 3; i++) {
						EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
						if (this.hasBoundPotionEffect(itemstack)) {
							entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
						}

						entity.setGunDamage((double) this.damage);
						for (String s: getModifications(itemstack)) {
							if (s == "Upgraded Caliber") {
								entity.setGunDamage((double) this.IncreaseDamageAmount);
								break;
							}
						}
						Random r = new Random();
						int addedSpread = r.nextInt((int) spread);
						entity.setPosition(entity.getPosition().getX() + addedSpread, entity.getPosition().getY() + addedSpread, entity.getPosition().getZ());
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
						entity.setRange(this.range);
						entity.shootingEntity = playerIn;
						worldIn.spawnEntity(entity);
					}

					itemstack.damageItem(1, playerIn);
					playShootSound(playerIn);
				}
			}
		}
	}

	public void shootGun(World worldIn, EntityPlayer playerIn, ItemStack itemstack) {

		boolean attach = false;

		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		if (!playerIn.capabilities.isCreativeMode) {
			if (itemstack.isItemDamaged()) {
				if (itemstack.getItemDamage() == clipsize) {
					Reload(playerIn, itemstack, nbt);
				} 
				else {
					//playerIn.getCooldownTracker().setCooldown(this, Firerate);
					if (!worldIn.isRemote) {
						if (!playerIn.isSprinting()) {
							EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
							if (this.hasBoundPotionEffect(itemstack)) {
								entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
							}
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);

							entity.setGunDamage((double) this.damage);
							for (String s: getModifications(itemstack)) {
								if (s == "Upgraded Caliber") {
									entity.setGunDamage((double) this.IncreaseDamageAmount);
									break;
								}
							}

							entity.shootingEntity = playerIn;
							entity.setRange(this.range);
							worldIn.spawnEntity(entity);
							itemstack.damageItem(1, playerIn);
							playShootSound(playerIn);

						}



					}


				}

			} else {
				//First Bullet
				//playerIn.getCooldownTracker().setCooldown(this, Firerate);
				if (!worldIn.isRemote) {
					if (!playerIn.isSprinting()) {
						EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
						if (this.hasBoundPotionEffect(itemstack)) {
							entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
						}

						entity.setGunDamage((double) this.damage);
						for (String s: getModifications(itemstack)) {
							if (s == "Upgraded Caliber") {
								entity.setGunDamage((double) this.IncreaseDamageAmount);
								break;
							}
						}
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
						entity.shootingEntity = playerIn;
						entity.setRange(this.range);
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						playShootSound(playerIn);
					}

				}

			}
		} 
		else {

			//Creative Move
			//playerIn.getCooldownTracker().setCooldown(this, Firerate);
			if (!worldIn.isRemote) {
				if (!playerIn.isSprinting()) {
					EntityBullet entity = new EntityBullet(worldIn, playerIn, this.strength);
					if (this.hasBoundPotionEffect(itemstack)) {
						entity.setPotionEffect(this.getBoundPotionEffect(itemstack));
					}

					entity.setGunDamage((double) this.damage);
					for (String s: getModifications(itemstack)) {
						if (s == "Upgraded Caliber") {
							entity.setGunDamage((double) this.IncreaseDamageAmount);
							break;
						}
					}
					entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 7 * 3, 0.0F);
					entity.setRange(this.range);
					entity.shootingEntity = playerIn;
					worldIn.spawnEntity(entity);
					itemstack.damageItem(1, playerIn);
					playShootSound(playerIn);
				}
			}
		}
	}

	public void Reload(EntityPlayer playerIn, ItemStack itemstack, NBTTagCompound nbt) {
		if (playerIn.inventory.hasItemStack(new ItemStack(ammo))) {
			itemstack.setItemDamage(-clipsize);
			playerIn.inventory.clearMatchingItems(ammo, 0, 1, null);
			//playerIn.getCooldownTracker().setCooldown(this, ReloadTime);
			if (nbt.getBoolean("ADS") == true) {
				nbt.setBoolean("ADS", false);
			}
			nbt.setFloat("reload", 1);
		}
	}



}