package org.golde.apocalypsecore.client.render.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID, value = Side.CLIENT)
public class EntityGlowlyThing {
	
	private static HashSet<ScorePlayerTeam> colorTeams = new HashSet<>();

	private static Scoreboard scoreboard;
	private static HashSet<EntityLivingBase> glowCache = new HashSet<>();
	private static LinkedHashMap<EntityLivingBase, Team> teamCache = new LinkedHashMap<>();

	private static HashMap<EntityLivingBase, String> entityColor = new HashMap<EntityLivingBase, String>();

	private static boolean ready = false;

	@SubscribeEvent
	public static void init(EntityJoinWorldEvent event)
	{
		if (!ready && event.getEntity() == Minecraft.getMinecraft().player)
		{
			scoreboard = Minecraft.getMinecraft().world.getScoreboard();

			for(TextFormatting tf : TextFormatting.values()) {
				if(!tf.isFancyStyling()) {
					ScorePlayerTeam team = scoreboard.createTeam(getScoreboardName(tf));
					team.setPrefix(tf.toString());
					colorTeams.add(team);
				}
			}

			ready = true;
		}
	}

	@SubscribeEvent
	public static void reset(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
	{
		ready = false;
	}


	@SubscribeEvent
	public static void tick(TickEvent.WorldTickEvent event)
	{
		if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END)
		{
			for (EntityLivingBase livingBase : (HashSet<EntityLivingBase>) glowCache.clone())
			{
				livingBase.setGlowing(false);
				glowCache.remove(livingBase);
			}

			for (Object object : teamCache.entrySet().toArray())
			{
				Map.Entry<EntityLivingBase, ScorePlayerTeam> entry = (Map.Entry<EntityLivingBase, ScorePlayerTeam>) object;
				EntityLivingBase livingBase = entry.getKey();
				Team team = entry.getKey().getTeam();

				if (team != null) scoreboard.addPlayerToTeam(livingBase.getUniqueID().toString(), team.getName());

				teamCache.remove(livingBase);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public static void preRender(RenderLivingEvent.Pre event)
	{
		if (ready)
		{
			EntityLivingBase livingBase = event.getEntity();

			//Hard stops

			//Remove glow effect if cached
			if (glowCache.contains(livingBase))
			{
				glowCache.remove(livingBase);
				livingBase.setGlowing(false);
			}


			if (!event.isCanceled())
			{
				//Don't draw seen entities as invisible, because they've been SEEN
				livingBase.setInvisible(false);


				//Focused target glow effect
				Team team = livingBase.getTeam();
				if (team != null) teamCache.put(livingBase, team);

				//COLOR
				if(entityColor.containsKey(livingBase)) {
					//System.out.println(livingBase.getName() + " - " + entityColor.get(livingBase));
					scoreboard.addPlayerToTeam(livingBase.getUniqueID().toString(),entityColor.get(livingBase));
				}

			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public static void postRender(RenderLivingEvent.Post event)
	{
		if (ready)
		{
			EntityLivingBase livingBase = event.getEntity();


			//Focused target glowing effect
			Team team = livingBase.getTeam();
			if (colorTeams.contains(team))
			{
				scoreboard.removePlayerFromTeam(livingBase.getCachedUniqueIdString(), (ScorePlayerTeam) team);
				if (teamCache.containsKey(livingBase))
				{
					scoreboard.addPlayerToTeam(livingBase.getCachedUniqueIdString(), teamCache.get(livingBase).getName());
				}
				livingBase.setGlowing(false);
			}

			if (!event.isCanceled())
			{
				//IF SHOULD GLOW
				if(entityColor.containsKey(livingBase)) {
					setTempGlow(event);
				}

			}


			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.disableBlend();
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void entityJoin(EntityJoinWorldEvent event)
	{
		if(event.getWorld().isRemote) {
			Entity entity = event.getEntity();
			if (entity instanceof EntityLiving) {
				replaceLayers((EntityLiving)entity);
			}
		}
	}

	private static void replaceLayers(EntityLivingBase livingBase)
	{
		try {
			Render render = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(livingBase);
			if (render instanceof RenderLivingBase)
			{
				Field privateStringField = RenderLivingBase.class.getDeclaredField("layerRenderers");

				privateStringField.setAccessible(true);

				List<LayerRenderer> list = (List<LayerRenderer>) privateStringField.get((RenderLivingBase) render);

				for (LayerRenderer layer : list.toArray(new LayerRenderer[0]))
				{
					if (layer instanceof LayerSpiderEyes)
					{
						list.remove(layer);
						list.add(new LayerSpiderEyesEdit((RenderSpider) render));
					}
					else if (layer instanceof LayerEndermanEyes)
					{
						list.remove(layer);
						list.add(new LayerEndermanEyesEdit((RenderEnderman) render));
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	private static void setTempGlow(RenderLivingEvent.Post event)
	{
		EntityLivingBase entity = event.getEntity();
		entity.setGlowing(true);
		glowCache.add(entity);
	}

	private static String getScoreboardName(TextFormatting tf) {
		return "ac_" + tf.name().toLowerCase();
	}

	public static void setColor(EntityLivingBase entity, TextFormatting color) {
		if(!color.isFancyStyling()) {
			entityColor.put(entity, getScoreboardName(color));
		}
	}

	public static void removeGlow(EntityLivingBase entity) {
		entityColor.remove(entity);
	}

}
