package org.golde.apocalypsecore.common.features.weapons;

import org.golde.apocalypsecore.common.features.FeatureClient;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderEntityGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderEntityMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.client.render.entity.RenderItemSmokebomb;
import org.golde.apocalypsecore.common.features.weapons.entity.EntityGrapplingHook;
import org.golde.apocalypsecore.common.features.weapons.entity.EntityMolotovCocktail;
import org.golde.apocalypsecore.common.features.weapons.entity.EntitySmokeBombThrowable;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureWeaponsClient extends FeatureClient {

	@Override
	public void regsterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySmokeBombThrowable.class, RenderEntitySmokeBombThrowableFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, RenderGrapplingHookFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityMolotovCocktail.class, RenderEntityMolotovCocktailFactory.INSTANCE);
	}

	@SideOnly(Side.CLIENT)
	public static class RenderEntitySmokeBombThrowableFactory implements IRenderFactory<EntitySmokeBombThrowable> {
		public final static RenderEntitySmokeBombThrowableFactory INSTANCE = new RenderEntitySmokeBombThrowableFactory();

		@Override
		public Render<EntitySmokeBombThrowable> createRenderFor(RenderManager manager)
		{
			return new RenderItemSmokebomb(manager, FeatureWeapons.smokeBomb);
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

	@SideOnly(Side.CLIENT)
	public static class RenderEntityMolotovCocktailFactory implements IRenderFactory<EntityMolotovCocktail> {
		public final static RenderEntityMolotovCocktailFactory INSTANCE = new RenderEntityMolotovCocktailFactory();

		@Override
		public Render<EntityMolotovCocktail> createRenderFor(RenderManager manager)
		{
			return new RenderEntityMolotovCocktail(manager, FeatureWeapons.molotovCocktail);
		}
	}
	
}
