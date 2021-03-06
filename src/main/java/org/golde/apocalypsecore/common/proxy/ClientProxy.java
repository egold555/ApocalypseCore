package org.golde.apocalypsecore.common.proxy;

import org.golde.apocalypsecore.common.features.FeatureRegistration;
import org.golde.apocalypsecore.common.features.FeatureRegistrationClient;
import org.golde.apocalypsecore.common.init.ACEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	public static int particleCount = 0;
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
	@Override
	public void registerRenders() {
		//ACBlocks.bindTESR();
		ACEntities.registerRenders();
		FeatureRegistrationClient.regsterEntityRenderers();
		FeatureRegistrationClient.bindTESR();
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		//ACItems.initModels();
		//ACBlocks.initModels();
		FeatureRegistrationClient.initModels();
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
	
	@SubscribeEvent
    public static void itemColorHandlers(ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();
        FeatureRegistrationClient.registerItemColorHandler(colors);
        
    }
	
	@Override
	public EntityPlayer getPlayer() {
		return Minecraft.getMinecraft().player;
	}
	
}
