package org.golde.apocalypsecore.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InventoryUtils {

	public static void dropItems(World world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;

            for (int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.getCount() > 0) {
                    float rx = world.rand.nextFloat() * 0.8F + 0.1F;
                    float ry = world.rand.nextFloat() * 0.8F + 0.1F;
                    float rz = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, (double) ((float) pos.getX() + rx), (double) ((float) pos.getY() + ry), (double) ((float) pos.getZ() + rz), item.copy());
                    float factor = 0.05F;

                    entityItem.motionX = world.rand.nextGaussian() * (double) factor;
                    entityItem.motionY = world.rand.nextGaussian() * (double) factor + 0.20000000298023224D;
                    entityItem.motionZ = world.rand.nextGaussian() * (double) factor;
                    world.spawnEntity(entityItem);
                    inventory.setInventorySlotContents(i, (ItemStack) null);
                }
            }

        }
    }
	
}
