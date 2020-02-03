package org.golde.apocalypsecore.common.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.blocks._ACBlockFluidClassic;
import org.golde.apocalypsecore.common.blocks._IACBlock;
import org.golde.apocalypsecore.common.items._IACItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Feature {

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();
	private static HashMap<Feature, CreativeTabs> tabs = new HashMap<Feature, CreativeTabs>();
	private static HashMap<IItemColor, Item[]> colorMapItem = new HashMap<IItemColor, Item[]>();
	private static HashMap<IItemColor, Block[]> colorMapBlock = new HashMap<IItemColor, Block[]>();
	private static List<Fluid> ALL_FLUID = new ArrayList<Fluid>();
	private static List<EntityEntryBuilder> ALL_ENTITIES = new ArrayList<EntityEntryBuilder>();
	private static List<SoundEvent> ALL_SOUNDS = new ArrayList<SoundEvent>();
	
	public void registerBlocks() {};
	public void registerItems() {};
	public abstract ItemStack getTabIcon();
	@SideOnly(Side.CLIENT)
	public void bindTESR() {};
	
	@SideOnly(Side.CLIENT)
	public void registerItemColorHandlers() {};
	public void registerFluids() {};
	public void registerEntities() {};
	
	@SideOnly(Side.CLIENT)
	public void regsterEntityRenderers() {};
	
	public void registerSoundEffects() {};
	
	protected static int entityId = -1;

	public Feature() {
		if(getTabIcon() != null) {
			tabs.put(this, new CreativeTabs("acTab." + this.getClass().getSimpleName()) {

				@Override
				public ItemStack getTabIconItem() {
					return getTabIcon();
				}
				
				//Adding in fluid buckets to our creative tab
				@Override
				public void displayAllRelevantItems(NonNullList<ItemStack> listOfItemStacks) {
					super.displayAllRelevantItems(listOfItemStacks);

					for(ItemStack is : listOfItemStacks) {
						if(is != null && is.getItem() != null) {
							Item item = is.getItem();
							
							if(item instanceof ItemBlock) {
								Block block = Block.getBlockFromItem(item);
								if(block != null && block != Blocks.AIR) {
									
									if(block instanceof _ACBlockFluidClassic) {
										_ACBlockFluidClassic fluidBlock =  (_ACBlockFluidClassic)block;
										
										//add a bucket to the gui
										listOfItemStacks.add(FluidUtil.getFilledBucket(new FluidStack(fluidBlock.getFluid(), Fluid.BUCKET_VOLUME)));
										
										
										//remove the fluid block tile thing
										listOfItemStacks.remove(is);
									}
									
								}
							}
						}
					}
				}
			});
		}
	}

	protected final void registerBlock(_IACBlock block) {
		if(getTabIcon() != null && block.shouldBeInCreatveTab()) {
			//shitty
			((Block)block).setCreativeTab(tabs.get(this));
		}

		ALL_BLOCKS.add(block);
	}

	protected final void registerItem(_IACItem item) {
		if(getTabIcon() != null && item.shouldBeInCreatveTab()) {
			((Item)item).setCreativeTab(tabs.get(this));
		}
		ALL_ITEMS.add(item);
	}

	protected static final void registerFluid(Fluid fluid) {
		ALL_FLUID.add(fluid);
	}

	protected static final void registerItemColorHandler(IItemColor key, Item... item) {
		colorMapItem.put(key, item);
	}

	protected static final void registerItemColorHandler(IItemColor key, Block... block) {
		colorMapBlock.put(key, block);
	}
	
	protected static final void registerEntity(EntityEntryBuilder builder) {
		ALL_ENTITIES.add(builder);
	}

	protected static final SoundEvent registerSoundEvent(String name){
		ResourceLocation res = new ResourceLocation(ApocalypseCore.MODID, name);
		SoundEvent evt = new SoundEvent(res).setRegistryName(res);
		if(evt == null || res == null) {
			ApocalypseCore.logger.error("Sound '" + name + "' was null");
		}
		else {
			ALL_SOUNDS.add(evt);
		}
		return evt;
	}

	public static final List<_IACBlock> getALL_BLOCKS() {
		return ALL_BLOCKS;
	}

	public static final List<_IACItem> getALL_ITEMS() {
		return ALL_ITEMS;
	}

	public static final HashMap<IItemColor, Block[]> getColorMapBlock() {
		return colorMapBlock;
	}

	public static final HashMap<IItemColor, Item[]> getColorMapItem() {
		return colorMapItem;
	}
	
	public static final List<Fluid> getALL_FLUID() {
		return ALL_FLUID;
	}
	
	public static List<EntityEntryBuilder> getALL_ENTITIES() {
		return ALL_ENTITIES;
	}
	
	public static List<SoundEvent> getALL_SOUNDS() {
		return ALL_SOUNDS;
	}

}
