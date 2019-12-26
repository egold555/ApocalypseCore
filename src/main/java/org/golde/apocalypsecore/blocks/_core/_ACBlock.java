package org.golde.apocalypsecore.blocks._core;


import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.init.ACTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class _ACBlock extends Block implements _IACBlock {

	public _ACBlock(String name) {
		this(name, Material.ROCK);
	}

	public _ACBlock(String name, Material mat) {
		super(mat);
		setUnlocalizedName(ApocalypseCore.MODID + "." + name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));	
	}

	protected final void makeLikeBedrock() {
		setBlockUnbreakable();
		setResistance(6000000.0F);
		disableStats();
	}

}
