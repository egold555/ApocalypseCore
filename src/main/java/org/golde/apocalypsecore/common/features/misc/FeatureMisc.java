package org.golde.apocalypsecore.common.features.misc;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.blocks._ACBlock;
import org.golde.apocalypsecore.common.blocks._ACBlockFluidClassic;
import org.golde.apocalypsecore.common.features.Feature;
import org.golde.apocalypsecore.common.features.misc.blocks.lootchest.BlockLootChest;
import org.golde.apocalypsecore.common.features.misc.blocks.lootchest.TIleEntityLootChest;
import org.golde.apocalypsecore.common.features.misc.client.render.block.TESRLootChest2;
import org.golde.apocalypsecore.common.features.misc.client.render.entity.RenderFallingLootCrate;
import org.golde.apocalypsecore.common.features.misc.entity.EntityFallingLootCrate;
import org.golde.apocalypsecore.common.features.misc.items.ItemWrench;
import org.golde.apocalypsecore.common.items._ACItem;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FeatureMisc extends Feature {

	public static _ACBlock exampleBlock;
	public static _ACItem exampleItem;
	public static ItemWrench wrench;
	public static BlockLootChest lootChest;

//	public static Fluid fluidGasoline;
//	public static _ACBlockFluidClassic blockFluidGasoline;

	@Override
	public void registerFluids() {
//		fluidGasoline = new Fluid(
//				"gasoline",
//				new ResourceLocation(ApocalypseCore.MODID + ":blocks/fluid/gasoline_still"),
//				new ResourceLocation(ApocalypseCore.MODID + ":blocks/fluid/gasoline_flow")
//				).setDensity(789).setViscosity(1200);
//		registerFluid(fluidGasoline);
	}


	@Override
	public void registerBlocks() {
		//registerBlock(blockFluidGasoline = new _ACBlockFluidClassic("fluid_gasoline", fluidGasoline).setFlammability(60, 200));
		registerBlock(exampleBlock = new _ACBlock("example_block"));
		registerBlock(lootChest = new BlockLootChest());
	}

	@Override
	public void registerItems() {
		registerItem(exampleItem = new _ACItem("example_item"));
		registerItem(wrench = new ItemWrench());
	}

	@Override
	public void registerEntities() {

		registerEntity(EntityEntryBuilder.create()
				.entity(EntityFallingLootCrate.class)
				.id(new ResourceLocation(ApocalypseCore.MODID, "fallinglootcrate"), entityId++)
				.name("fallinglootcrate")
				.tracker(64, 1, true));
	}
	
	@Override
	public void regsterEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingLootCrate.class, RenderEntityFallingLootCrateFactory.INSTANCE);
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

	@Override
	public ItemStack getTabIcon() {
		return new ItemStack(wrench);
	}

	@Override
	public void bindTESR() {
		ClientRegistry.bindTileEntitySpecialRenderer(TIleEntityLootChest.class, new TESRLootChest2());
	}

}
