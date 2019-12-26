package org.golde.apocalypsecore.common.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MobSpawningEvents {

	@SubscribeEvent
	public static void livingSpawn(LivingSpawnEvent.CheckSpawn e) {
		
		if(!(e.getEntity() instanceof EntityLivingBase)) {
			return;
		}
		
		EntityLivingBase l = (EntityLivingBase)e.getEntity();
		
		if(!e.isSpawner() && !(l instanceof EntityZombie || l instanceof EntityVex || l instanceof AbstractIllager || l instanceof AbstractSkeleton || l instanceof EntityVillager)) {
			e.setResult(Result.DENY);
		}
	}
	
}
