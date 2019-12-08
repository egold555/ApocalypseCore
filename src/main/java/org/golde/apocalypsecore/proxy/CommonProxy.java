package org.golde.apocalypsecore.proxy;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.gui.ForgeGuiHandler;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {

	}

	public void init(FMLInitializationEvent e) {
		//idk this needs to be called server side. COnfusing cause guis on client side but its forge who knows
		NetworkRegistry.INSTANCE.registerGuiHandler(ApocalypseCore.instance, new ForgeGuiHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public void serverStarting(FMLServerStartingEvent e) {

	}

	public void registerRenders() {/*Client Only Method*/}

	public boolean isClient() {
		return false;
	}

	public void makeSmoke(World world, double x, double y, double z, int color, int amount, int radX, int radY) {

	}

}
