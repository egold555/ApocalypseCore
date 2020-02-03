package org.golde.apocalypsecore.common.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Feature {

	private static List<_IACBlock> ALL_BLOCKS = new ArrayList<_IACBlock>();
	private static List<_IACItem> ALL_ITEMS = new ArrayList<_IACItem>();
	private static HashMap<Feature, CreativeTabs> tabs = new HashMap<Feature, CreativeTabs>();
	private static HashMap<IItemColor, Item[]> colorMapItem = new HashMap<IItemColor, Item[]>();
	private static HashMap<IItemColor, Block[]> colorMapBlock = new HashMap<IItemColor, Block[]>();
	private static List<Fluid> ALL_FLUID = new ArrayList<Fluid>();
	
	public void registerBlocks() {};
	public void registerItems() {};
	public abstract ItemStack getTabIcon();
	@SideOnly(Side.CLIENT)
	public void bindTESR() {};
	public void registerItemColorHandlers() {};
	public void registerFluids() {};

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

	protected final void registerFluid(Fluid fluid) {
		this.ALL_FLUID.add(fluid);
	}

	protected final void registerItemColorHandler(IItemColor key, Item... item) {
		this.colorMapItem.put(key, item);
	}

	protected final void registerItemColorHandler(IItemColor key, Block... block) {
		this.colorMapBlock.put(key, block);
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

}
