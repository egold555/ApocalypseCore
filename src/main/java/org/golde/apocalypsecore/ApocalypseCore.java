package org.golde.apocalypsecore;

import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.golde.apocalypsecore.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ApocalypseCore.MODID, name = ApocalypseCore.MODNAME, version = ApocalypseCore.VERSION, dependencies = "", useMetadata = true)
public class ApocalypseCore {

	public static final String MODID = "ac";
    public static final String MODNAME = "ApocalypsCore"; //Apocalypses
    public static final String VERSION = "1.0.0";
    
    @SidedProxy(modId = ApocalypseCore.MODID, clientSide = "org.golde.apocalypsecore.proxy.ClientProxy", serverSide = "org.golde.apocalypsecore.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ApocalypseCore.MODID)
    public static ApocalypseCore instance;
    
    public static Logger logger;
    public static Random RANDOM;
    
    public static ACTab tab;
    public static ACTabCD tabCD;
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	RANDOM = new Random();
        logger = event.getModLog();
        proxy.preInit(event);
        proxy.registerRenders();
        
        tab = new ACTab();
        tabCD = new ACTabCD();
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
    
    public static class ACTab extends CreativeTabs {

		public ACTab() {
			super("acTab");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.ARROW);
		}
    	
    }
    
    public static class ACTabCD extends CreativeTabs {

		public ACTabCD() {
			super("acTabCD");
		}

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.BOW);
		}
    	
    }
    
}
