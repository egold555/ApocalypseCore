package org.golde.apocalypsecore.common.features;

import java.util.ArrayList;
import java.util.List;

import org.golde.apocalypsecore.common.blocks._ACBlockWithTE;
import org.golde.apocalypsecore.common.blocks._IACBlock;
import org.golde.apocalypsecore.common.features.decor.FeatureDecor;
import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.features.food.FeatureFood;
import org.golde.apocalypsecore.common.features.misc.FeatureMisc;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;
import org.golde.apocalypsecore.common.items._IACItem;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class FeatureRegistration {

	private static List<Feature> features = new ArrayList<Feature>();
	
	static {
		features.add(new FeatureMisc());
		features.add(new FeatureDecor());
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
	
	public static void serverStarting(FMLServerStartingEvent e) {
		for(Feature feat : features) {
			feat.registerSeverCommands();
		}
		
		for(CommandBase cmdBase : Feature.getALL_COMMANDS()) {
			e.registerServerCommand(cmdBase);
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
	
	
	
	@SubscribeEvent
	public static void registerEntitys(final RegistryEvent.Register<EntityEntry> event) {
		
		for(Feature f : features) {
			f.registerEntities();
		}
		
		for(EntityEntryBuilder builder : Feature.getALL_ENTITIES()) {
			event.getRegistry().register(builder.build());
		}
	}
	
	
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for(Feature f : features) {
			f.registerSoundEffects();
		}
		
		for(SoundEvent se : Feature.getALL_SOUNDS()) {
			event.getRegistry().register(se);
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
