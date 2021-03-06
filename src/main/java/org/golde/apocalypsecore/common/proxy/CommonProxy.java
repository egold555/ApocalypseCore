package org.golde.apocalypsecore.common.proxy;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.command.ACCommandParticle;
import org.golde.apocalypsecore.common.command.ACCommandTest;
import org.golde.apocalypsecore.common.features.FeatureRegistration;
import org.golde.apocalypsecore.common.gui.ForgeGuiHandler;
import org.golde.apocalypsecore.common.init.ACDispenser;
import org.golde.apocalypsecore.common.network.ACPacketHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ACPacketHandler.preInit();
		FeatureRegistration.preInit();
	}

	public void init(FMLInitializationEvent e) {
		//idk this needs to be called server side. COnfusing cause guis on client side but its forge who knows
		NetworkRegistry.INSTANCE.registerGuiHandler(ApocalypseCore.instance, new ForgeGuiHandler());
		ACDispenser.init();
	}

	public void postInit(FMLPostInitializationEvent e) {
		
		ItemStack water = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
		
		
	}

	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new ACCommandParticle());
		e.registerServerCommand(new ACCommandTest());
		FeatureRegistration.serverStarting(e);
	}

	public void registerRenders() {/*Client Only Method*/}

	public boolean isClient() {
		return false;
	}

	public EntityPlayer getPlayer() {
		return null;
	}
	
}
