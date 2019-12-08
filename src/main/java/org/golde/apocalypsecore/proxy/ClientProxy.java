package org.golde.apocalypsecore.proxy;

import org.golde.apocalypsecore.client.render.particle.ParticleGasSmoke;
import org.golde.apocalypsecore.init.ACBlocks;
import org.golde.apocalypsecore.init.ACEntities;
import org.golde.apocalypsecore.init.ACItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	private int particleCount = 0;
	
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
		ACBlocks.bindTESR();
		ACEntities.registerRenders();
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ACItems.initModels();
		ACBlocks.initModels();
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
	
	@Override
    public void makeSmoke(World world, double x, double y, double z, int color, int amount, int radX, int radY) {

        float b = (color & 0xFF) / 255F;
        float g = (color >> 8 & 0xFF) / 255F;
        float r = (color >> 16 & 0xFF) / 255F;
        
        for (int i = 0; i < amount; i++) {
            particleCount += world.rand.nextInt(3);
            if (particleCount % (Minecraft.getMinecraft().gameSettings.particleSetting == 0 ? 1 : 2 * Minecraft.getMinecraft().gameSettings.particleSetting) == 0) {
                double posX = x + world.rand.nextGaussian() * radX % radX;
                double posY = y + world.rand.nextGaussian() * radY % radY;
                double posZ = z + world.rand.nextGaussian() * radX % radX;
                ParticleGasSmoke particle = new ParticleGasSmoke(world, posX, posY, posZ, r, g, b, (float) (55 + 20 * world.rand.nextGaussian()));

                Minecraft.getMinecraft().effectRenderer.addEffect(particle); 
            }
        }
    }
	
}
