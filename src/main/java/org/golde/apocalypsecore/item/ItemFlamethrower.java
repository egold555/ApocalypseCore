package org.golde.apocalypsecore.item;

import java.util.Random;

import org.golde.apocalypsecore.item._core._ACItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemFlamethrower extends _ACItem {

	public ItemFlamethrower() {
		super("flamethrower");
		this.setMaxStackSize(1);
		this.setMaxDamage(300);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {



		//		Vec3d lockVector = entityIn.getForward();
		//		double x = lockVector.x * 0.8;
		//		double y = 0;
		//		double z = lockVector.z * 0.8;
		//		
		//		double xCoord = entityIn.posX + x;
		//		double yCoord = entityIn.posY + y;
		//		double zCoord = entityIn.posZ + z;
		//		
		//		if (worldIn.isRemote) {
		//			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xCoord, yCoord, zCoord, 0, 0, 0);	
		//		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		// TODO Auto-generated method stub
		super.onUsingTick(stack, player, count);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		Vec3d lockVector = playerIn.getLookVec();
		double x = lockVector.x / 10;
		double y = lockVector.y / 10;
		double z = lockVector.z / 10;
		Random random = new Random();
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		for (int i = 0; i < 100; i++) {
			double xCoord = playerIn.posX + x * i + random.nextDouble();
			double yCoord = playerIn.posY + y * i + random.nextDouble();
			double zCoord = playerIn.posZ + z * i + random.nextDouble();

			//playerIn.getCooldownTracker().setCooldown(this, 15);

			if (worldIn.isRemote) {
				worldIn.spawnParticle(EnumParticleTypes.FLAME, xCoord, yCoord, zCoord, x * 10, y * 10, z * 10);	
			} 
			else {
				worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.2F, 0.5F / (itemRand.nextFloat() * 0.4F + 0.8F));



				if (worldIn.isBlockFullCube(new BlockPos(xCoord, yCoord, zCoord)) && worldIn.getBlockState(new BlockPos(xCoord, yCoord + 1, zCoord)).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(new BlockPos(xCoord, yCoord + 1, zCoord), Blocks.FIRE.getDefaultState());
				}

				for (Object obj : worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1))) {
					if (obj instanceof EntityLivingBase) {
						EntityLivingBase entity = (EntityLivingBase) obj;
						entity.setFire(5);
					}
				}
			}
		}	
		for(int i = 0; i < 100; i++) {
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, playerIn.posX + x * i + random.nextDouble(), playerIn.posY + y * i + random.nextDouble(), playerIn.posZ + z * i + random.nextDouble(), x * 10, y * 10, z * 10);
		}

		itemstack.damageItem(1, playerIn);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}
