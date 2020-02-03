package org.golde.apocalypsecore.common.features.misc.event;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class NoMobBurning {

	private static String[] mobList = new String[]{ };
	private static String[] modList = new String[]{"minecraft", "ac"};
	
	@SubscribeEvent
	public static void entityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer)
			return;
		String fullId = EntityList.getKey(event.getEntity()).toString();
		String id[] = fullId.split(":");
		if (Arrays.asList(mobList).contains(fullId) || Arrays.asList(modList).contains(id[0])) {
			World world = event.getEntity().getEntityWorld();
			boolean day = world.isDaytime();
			BlockPos blockpos = event.getEntity().getPosition();
			if (world.canSeeSky(blockpos))
				if (day) {
					if (event.getEntity().isBurning() && !event.getEntity().isInLava()) {
						event.getEntity().extinguish();
					}
				}
		}
	}
	
}
