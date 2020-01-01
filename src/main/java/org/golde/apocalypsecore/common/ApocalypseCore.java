package org.golde.apocalypsecore.common;

import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.golde.apocalypsecore.common.features.thirst.PlayerContainer;
import org.golde.apocalypsecore.common.proxy.CommonProxy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ApocalypseCore.MODID, name = ApocalypseCore.MODNAME, version = ApocalypseCore.VERSION, dependencies = "", useMetadata = true)
public class ApocalypseCore {

	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	public static final String MODID = "ac";
    public static final String MODNAME = "ApocalypsCore"; //Apocalypses
    public static final String VERSION = "1.0.0";
    
    @SidedProxy(modId = ApocalypseCore.MODID, clientSide = "org.golde.apocalypsecore.common.proxy.ClientProxy", serverSide = "org.golde.apocalypsecore.common.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ApocalypseCore.MODID)
    public static ApocalypseCore instance;
    
    public static Logger logger;
    public static Random RANDOM;
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RANDOM = new Random();
        logger = event.getModLog();
        proxy.preInit(event);
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
    	proxy.serverStarting(e);
    }
    
    @Mod.EventHandler
	public static void serverClosed(FMLServerStoppedEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			PlayerContainer.ALL_PLAYERS.clear();
		}
	}
	
    
}
