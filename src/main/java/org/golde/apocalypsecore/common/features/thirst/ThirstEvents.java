package org.golde.apocalypsecore.common.features.thirst;

import org.golde.apocalypsecore.client.render.GuiRenderBar;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ThirstEvents {

	private static int thirstToSet;
	
	@SubscribeEvent
	public static void onTick(PlayerTickEvent e) {
		if(e.side == Side.SERVER) {
			PlayerContainer handler = PlayerContainer.getPlayer(e.player);
			if (handler != null) {
				if (!e.player.capabilities.isCreativeMode) {
					handler.getStats().onTick();
				}
			} else {
				PlayerContainer.addPlayer(e.player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			PlayerContainer.addPlayer(event.player);
		}
	}
	
	@SubscribeEvent
	public static void onLogout(PlayerLoggedOutEvent event) {
		PlayerContainer.ALL_PLAYERS.remove(event.player.getDisplayNameString());
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderBar(RenderGameOverlayEvent event) {
		int width = event.getResolution().getScaledWidth();
	    int height = event.getResolution().getScaledHeight();
		if(event.getType() != null) {
			switch(event.getType()) {
			case FOOD: {
				if (!Minecraft.getMinecraft().player.isRidingHorse()) {
					GuiRenderBar.renderThirst(width, height);
				}
				break;
			}
			case AIR: {
				event.setCanceled(true);
			    GuiRenderBar.left_height = GuiIngameForge.left_height;
			    GuiRenderBar.right_height = GuiIngameForge.right_height;
				GuiRenderBar.renderAir(width, height);
				break;
			}
			case ARMOR: {
				event.setCanceled(true);
				GuiRenderBar.renderArmor(width, height);
				break;
			}
			default: break;
		}
		}
	}
	
	@SubscribeEvent
	public static void onAttack(AttackEntityEvent attack) {
		PlayerContainer player = PlayerContainer.getPlayer(attack.getEntityPlayer());
		if ((player != null) && (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)) {
			player.addExhaustion(0.5f);
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent hurt) {
		if (hurt.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) hurt.getEntityLiving();
			PlayerContainer.getPlayer(player).addExhaustion(0.4f);
		}
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		if(player != null) {
			if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				player.addExhaustion(0.03f);
			}
		}
		event.setResult(Result.DEFAULT);
	}
	
	@SubscribeEvent
	public static void playedCloned(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			if(event.isWasDeath()) {
				PlayerContainer.respawnPlayer(event.getEntityPlayer());
			}
		}
	}
	
	@SubscribeEvent
	public static void onSleep(PlayerSleepInBedEvent event) {
		if(event.getEntityPlayer().world.isRemote) return;
		PlayerContainer playerContainer = PlayerContainer.getPlayer(event.getEntityPlayer());
		EntityPlayer player = playerContainer.getContainerPlayer();
		
		//Only run the code below if the difficulty allows it!
		if(!playerContainer.getStats().isThirstAllowedByDifficulty()) return;
		if(ThirstConstants.DISABLE_THIRST_LOSS_FROM_SLEEP) return;
		
		int dayLength = 24000;
		int thirstInterval = 2000;
		int worldTime = (int) (event.getEntityPlayer().world.getWorldTime() % dayLength);
		int sleepingTime = dayLength - worldTime;
		int newThirst = playerContainer.getStats().thirstLevel - (sleepingTime / thirstInterval);
		
		if(!player.world.isDaytime()) {
			if (newThirst <= 8) {
				player.sendMessage(new TextComponentTranslation("thirstmod.toothirsty", new Object[0]));
				event.setResult(SleepResult.OTHER_PROBLEM);
			} else {
				thirstToSet = newThirst;
			}
		}
	}
	
	@SubscribeEvent
	public static void wakeUp(PlayerWakeUpEvent event) {
		if(ThirstConstants.DISABLE_THIRST_LOSS_FROM_SLEEP) return;
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			PlayerContainer player = PlayerContainer.getPlayer(event.getEntityPlayer());
			player.stats.setStats(thirstToSet, player.stats.thirstSaturation);
			thirstToSet = 0;
		}
	}
	
	@SubscribeEvent 
	public static void onFinishUsingItem(LivingEntityUseItemEvent.Finish event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			
			/*
			 * BUGS:
			 * Death message isnt correct.
			 */
			
			if(event.getEntityLiving() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)event.getEntityLiving();
				String id = event.getItem().getUnlocalizedName();
				for(IDrinkable d: DrinkRegistry.getDrinks()) {
					
					if(ItemStack.areItemStacksEqual(d.getItem(), event.getItem())) {
						PlayerContainer playCon = PlayerContainer.getPlayer(player);
						playCon.addStats(d.getReplenish(), d.getSaturation());
						break;
					}
				}
			}
			
			
		}
	}
	
}
