package org.golde.apocalypsecore.common.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.common.blocks._ACBlockWithTE;
import org.golde.apocalypsecore.common.blocks._IACBlock;
import org.golde.apocalypsecore.common.features.building.FeatureBuilding;
import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.features.food.FeatureFood;
import org.golde.apocalypsecore.common.features.misc.FeatureMisc;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
import org.golde.apocalypsecore.common.items._IACItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class FeatureRegistration {

	private static List<Feature> features = new ArrayList<Feature>();
	
	static {
		features.add(new FeatureMisc());
		features.add(new FeatureBuilding());
		features.add(new FeatureFood());
		features.add(new FeatureWeapons());
		features.add(new FeatureDrugs());
	}
	/*
	  @EventHandler
    public void construction(FMLConstructionEvent event)
    {
        // Use forge universal bucket
        FluidRegistry.enableUniversalBucket();
    }

	 */
	public static void preInit() {
		for(Feature feat : features) {
			feat.registerFluids();
		}
		
		for(Fluid flu : Feature.getALL_FLUID()) {
			FluidRegistry.registerFluid(flu);
			FluidRegistry.addBucketForFluid(flu);
			System.err.println("===== REGISTERING FLUID: " + flu.getName());
		}
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		
//		for(Feature feat : features) {
//			feat.registerFluids();
//		}
//		
//		for(Fluid flu : Feature.getALL_FLUID()) {
//			System.err.println("===== REGISTERING FLUID: " + flu.getName());
//			FluidRegistry.registerFluid(flu);
//			FluidRegistry.addBucketForFluid(flu);
//		}
//		
		for(Feature f : features) {
			f.registerBlocks();
			
		}
		
		for(_IACBlock b : Feature.getALL_BLOCKS()) {
			event.getRegistry().register((Block)b);
		}
		registerTileEntities();
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		
		for(Feature f : features) {
			f.registerItems();
		}
		
		for(_IACBlock block : Feature.getALL_BLOCKS()) {
			if(block != null && block.shouldRegisterItem()) {
				
				event.getRegistry().register(getItem(block));
			}
		}

		for(_IACItem item : Feature.getALL_ITEMS()) {
			event.getRegistry().register((Item)item);
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public static final void initModels() {

		for(_IACBlock block : Feature.getALL_BLOCKS()) {
			if(block.shouldRegisterItem()) {
				block.initModel();
			}
		}
		
		for(_IACItem item : Feature.getALL_ITEMS()) {
			item.initModel();
		}

	}
	
	public static final void registerItemColorHandler(ItemColors colors) {
		for(Feature f : features) {
			f.registerItemColorHandlers();
		}
		
		for(IItemColor c : Feature.getColorMapItem().keySet()) {
			colors.registerItemColorHandler(c, Feature.getColorMapItem().get(c));
		}
		
		for(IItemColor c : Feature.getColorMapBlock().keySet()) {
			colors.registerItemColorHandler(c, Feature.getColorMapBlock().get(c));
		}
	}
	
	public static void bindTESR() {
		for(Feature f : features) {
			f.bindTESR();
		}
	}
	
	private static final void registerTileEntities() {
		for(_IACBlock block : Feature.getALL_BLOCKS()) {

			if(block == null) {
				System.err.println("Null block detected. Check assignmentss");
				continue;
			}

			if(block instanceof _ACBlockWithTE) {
				_ACBlockWithTE<?> blockTE = (_ACBlockWithTE<?>)block;
				GameRegistry.registerTileEntity(blockTE.getTileEntityClass(), blockTE.getRegistryName());
			}
		}
	}
	
	private static ItemBlock getItem(_IACBlock iblock) {
		return getItem((Block)iblock); //BAD but it works
	}
	
	private static ItemBlock getItem(Block block) {
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		return ib;
	}
	
}