package org.golde.apocalypsecore.init;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.client.render.entity.RenderEntityBullet;
import org.golde.apocalypsecore.client.render.entity.RenderEntityGrapplingHook;
import org.golde.apocalypsecore.client.render.entity.RenderItemSmokebomb;
import org.golde.apocalypsecore.entity.EntityBullet;
import org.golde.apocalypsecore.entity.EntityGrapplingHook;
import org.golde.apocalypsecore.entity.EntitySmokeBombThrowable;
import org.golde.apocalypsecore.entity.EntitySmokeCloud;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID)
public class ACEntities {

	private static int id = -1;
	
	@SubscribeEvent
	public static void registerEntitys(final RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().register(
				EntityEntryBuilder.create()
				.entity(EntitySmokeBombThrowable.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "smokebomb"), id++)
				.name("smokebomb")
				.tracker(64, 1, true)
//				.egg(MapColor.BLUE.colorValue, MapColor.YELLOW.colorValue)
//				.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
				.build()
				);
		
		event.getRegistry().register(
				EntityEntryBuilder.create()
				.entity(EntitySmokeCloud.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "smokecloud"), id++)
				.name("smokecloud")
				.tracker(64, 1, true)
//				.egg(MapColor.BLACK.colorValue, MapColor.BLACK_STAINED_HARDENED_CLAY.colorValue)
//				.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
				.build()
				);
		
		event.getRegistry().register(
				EntityEntryBuilder.create()
				.entity(EntityGrapplingHook.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "grapplinghook"), id++)
				.name("grapplinghook")
				.tracker(64, 1, true)
//				.egg(MapColor.BLACK.colorValue, MapColor.BLACK_STAINED_HARDENED_CLAY.colorValue)
//				.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
				.build()
				);
		
		event.getRegistry().register(
				EntityEntryBuilder.create()
				.entity(EntityBullet.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "bullet"), id++)
				.name("bullet")
				.tracker(64, 10, true)
//				.egg(MapColor.BLUE.colorValue, MapColor.YELLOW.colorValue)
//				.spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
				.build()
				);

	}
	
	@SideOnly(Side.CLIENT)
    public static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeBombThrowable.class, RenderEntitySmokeBombThrowableFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, RenderBulletFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, RenderGrapplingHookFactory.INSTANCE);
		
	}
	
	@SideOnly(Side.CLIENT)
	public static class RenderEntitySmokeBombThrowableFactory implements IRenderFactory<EntitySmokeBombThrowable> {
        public final static RenderEntitySmokeBombThrowableFactory INSTANCE = new RenderEntitySmokeBombThrowableFactory();
    
        @Override
        public Render<EntitySmokeBombThrowable> createRenderFor(RenderManager manager)
        {
            return new RenderItemSmokebomb(manager, ACItems.smokeBomb);
        }
    }
	
	@SideOnly(Side.CLIENT)
	public static class RenderBulletFactory implements IRenderFactory<EntityBullet> {
        public final static RenderBulletFactory INSTANCE = new RenderBulletFactory();
    
        @Override
        public Render<EntityBullet> createRenderFor(RenderManager manager)
        {
            return new RenderEntityBullet(manager);
        }
    }
	
	@SideOnly(Side.CLIENT)
	public static class RenderGrapplingHookFactory implements IRenderFactory<EntityGrapplingHook> {
        public final static RenderGrapplingHookFactory INSTANCE = new RenderGrapplingHookFactory();
    
        @Override
        public Render<EntityGrapplingHook> createRenderFor(RenderManager manager)
        {
            return new RenderEntityGrapplingHook(manager);
        }
    }
	
}
