package org.golde.apocalypsecore.common.features.weapons.items;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.common.features.weapons.entity.EntityGrapplingHook;
import org.golde.apocalypsecore.common.items._ACItem;
import org.golde.apocalypsecore.common.items._ACItemBowAnimation;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGrapplingHook extends _ACItem {

	private boolean isShot = false;
	
	public ItemGrapplingHook() {
		super("grappling_hook");
		setMaxStackSize(1);
		setMaxDamage(99);
		this.addPropertyOverride(new ResourceLocation("shot"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				}
				
				return isShot ? 1.0F : 0.0F;
			}
		});
	}
	
	

    public ActionResult onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        worldIn.playSound((EntityPlayer) null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (ItemGrapplingHook.itemRand.nextFloat() * 0.4F + 0.8F));
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("HookInfo")) {
            Iterator hook = worldIn.getEntities(EntityGrapplingHook.class, new Predicate() {
                public boolean func_180094_a(Entity p_180094_1_) {
                    return p_180094_1_ instanceof EntityGrapplingHook;
                }

                public boolean apply(Object p_apply_1_) {
                    return this.func_180094_a((Entity) p_apply_1_);
                }
            }).iterator();

            while (hook.hasNext()) {
                EntityGrapplingHook nbt = (EntityGrapplingHook) hook.next();

                if (nbt.getUniqueID().toString().equals(stack.getTagCompound().getCompoundTag("HookInfo").getString("HookID"))) {
                    nbt.setDead();
                }
            }
        }

        if (!this.isShot) {
            EntityGrapplingHook hook1 = new EntityGrapplingHook(worldIn, player, 1.5F);
           
            NBTTagCompound nbt1 = new NBTTagCompound();

            nbt1.setString("HookID", hook1.getUniqueID().toString());
            stack.setTagInfo("HookInfo", nbt1);
            
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(hook1);
                this.isShot = true;
            }

            stack.damageItem(1, player);
        } else if (!worldIn.isRemote) {
            this.isShot = false;
        }

        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("HookInfo")) {
            Iterator iterator = player.world.getEntities(EntityGrapplingHook.class, new Predicate() {
                public boolean func_180094_a(Entity entity) {
                    return entity instanceof EntityGrapplingHook;
                }

                public boolean apply(Object obj) {
                    return this.func_180094_a((Entity) obj);
                }
            }).iterator();

            while (iterator.hasNext()) {
                EntityGrapplingHook hook = (EntityGrapplingHook) iterator.next();

                if (hook.getUniqueID().toString().equals(stack.getTagCompound().getCompoundTag("HookInfo").getString("HookID"))) {
                    hook.setDead();
                    this.isShot = false;
                }
            }
        }

        return super.onDroppedByPlayer(stack, player);
    }

}
