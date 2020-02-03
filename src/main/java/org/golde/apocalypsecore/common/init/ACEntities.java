package org.golde.apocalypsecore.common.init;

import org.golde.apocalypsecore.client.render.entity.variant.VariantManager;
import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.misc.client.render.entity.RenderFallingLootCrate;
import org.golde.apocalypsecore.common.features.misc.entity.EntityFallingLootCrate;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = ApocalypseCore.MODID)
public class ACEntities {


	
	@SideOnly(Side.CLIENT)
    public static void registerRenders() {
		
		//VariantManager.registerRenders();
	}
	
	
	
	
	
}
