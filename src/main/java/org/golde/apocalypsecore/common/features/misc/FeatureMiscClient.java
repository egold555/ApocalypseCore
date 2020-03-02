package org.golde.apocalypsecore.common.features.misc;

import org.golde.apocalypsecore.client.render.item.LayerStaticColor;
import org.golde.apocalypsecore.common.features.FeatureClient;
import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.features.misc.blocks.lootchest.TIleEntityLootChest;
import org.golde.apocalypsecore.common.features.misc.client.render.block.TESRLootChest2;
import org.golde.apocalypsecore.common.features.misc.client.render.entity.RenderDeadBody;
import org.golde.apocalypsecore.common.features.misc.client.render.entity.RenderFallingLootCrate;
import org.golde.apocalypsecore.common.features.misc.entity.EntityDeadBody;
import org.golde.apocalypsecore.common.features.misc.entity.EntityFallingLootCrate;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureMiscClient extends FeatureClient {

	@Override
	public void regsterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingLootCrate.class, RenderEntityFallingLootCrateFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(EntityDeadBody.class, RenderEntityDeadBodyFactory.INSTANCE);
	}
	
	@SideOnly(Side.CLIENT)
	public static class RenderEntityFallingLootCrateFactory implements IRenderFactory<EntityFallingLootCrate> {
        public final static RenderEntityFallingLootCrateFactory INSTANCE = new RenderEntityFallingLootCrateFactory();
    
        @Override
        public Render<EntityFallingLootCrate> createRenderFor(RenderManager manager)
        {
            return new RenderFallingLootCrate(manager);
        }
    }
	
	@SideOnly(Side.CLIENT)
	public static class RenderEntityDeadBodyFactory implements IRenderFactory<EntityDeadBody> {
        public final static RenderEntityDeadBodyFactory INSTANCE = new RenderEntityDeadBodyFactory();
    
        @Override
        public Render<EntityDeadBody> createRenderFor(RenderManager manager)
        {
            return new RenderDeadBody(manager);
        }
    }
	
	@Override
	public void bindTESR() {
		ClientRegistry.bindTileEntitySpecialRenderer(TIleEntityLootChest.class, new TESRLootChest2());
	}
	
}
