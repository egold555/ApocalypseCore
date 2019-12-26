package org.golde.apocalypsecore.blocks._core;

import org.golde.apocalypsecore.ApocalypseCore;

import net.minecraft.block.BlockBasePressurePlate;
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

public abstract class _ACBlockPreasurePlate extends BlockBasePressurePlate implements _IACBlock {

	protected _ACBlockPreasurePlate(String name) {
		this(name, Material.ROCK);
	}
	
	protected _ACBlockPreasurePlate(String name, Material materialIn) {
		super(materialIn);
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
