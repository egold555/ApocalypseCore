package org.golde.apocalypsecore.blocks;

import org.golde.apocalypsecore.blocks._core._ACBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCagedLight extends _ACBlock {

	protected static final AxisAlignedBB LAMP_AAB = new AxisAlignedBB(0.375D, 0.71875D, 0.375D, 0.625D, 1.0D, 0.625D);

	public BlockCagedLight() {
		super("lamp", Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(2.0F);
		this.setLightLevel(1F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return LAMP_AAB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

}
