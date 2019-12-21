package org.golde.apocalypsecore.init;

import org.golde.apocalypsecore.entity.EntityMolotovCocktail;
import org.golde.apocalypsecore.entity.EntitySmokeBombThrowable;
import org.golde.apocalypsecore.item.ItemSmokeBomb;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ACDispenser {

	public static void init() {
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ACItems._Weapons.molotovCocktail, new IBehaviorDispenseItem()
        {
            /**
             * Dispenses the specified ItemStack from a dispenser.
             */
            public ItemStack dispense(IBlockSource source, final ItemStack stack)
            {
                return (new BehaviorProjectileDispense()
                {
                    /**
                     * Return the projectile entity spawned by this dispense behavior.
                     */
                    protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn)
                    {
                        return new EntityMolotovCocktail(worldIn, position.getX(), position.getY(), position.getZ());
                    }
                    protected float getProjectileInaccuracy()
                    {
                        return super.getProjectileInaccuracy() * 0.5F;
                    }
                    protected float getProjectileVelocity()
                    {
                        return super.getProjectileVelocity() * 1.25F;
                    }
                }).dispense(source, stack);
            }
        });
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ACItems._Weapons.smokeBomb, new IBehaviorDispenseItem()
        {
            /**
             * Dispenses the specified ItemStack from a dispenser.
             */
            public ItemStack dispense(IBlockSource source, final ItemStack stack)
            {
                return (new BehaviorProjectileDispense()
                {
                    /**
                     * Return the projectile entity spawned by this dispense behavior.
                     */
                    protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn)
                    {
                    	EntitySmokeBombThrowable sbt = new EntitySmokeBombThrowable(worldIn, position.getX(), position.getY(), position.getZ());
                    	if(stackIn != null && stackIn.getItem() != null && stackIn.getItem() instanceof ItemSmokeBomb) {
                    		ItemSmokeBomb isb = (ItemSmokeBomb)stackIn.getItem();
                    		sbt.setColorDamage(isb.getDamage(stackIn));
                    	}
                        return sbt;
                    }
                    protected float getProjectileInaccuracy()
                    {
                        return super.getProjectileInaccuracy() * 0.5F;
                    }
                    protected float getProjectileVelocity()
                    {
                        return super.getProjectileVelocity() * 1.25F;
                    }
                }).dispense(source, stack);
            }
        });
	}
	
}
