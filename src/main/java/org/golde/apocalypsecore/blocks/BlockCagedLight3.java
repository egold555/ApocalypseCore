package org.golde.apocalypsecore.blocks;

import org.golde.apocalypsecore.blocks._core._ACBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCagedLight3 extends _ACBlock {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	protected static final AxisAlignedBB LAMP_AAB = new AxisAlignedBB(0.375D, 0.71875D, 0.375D, 0.625D, 1.0D, 0.625D);

	public BlockCagedLight3(EnumDyeColor color) {
		super("lamp_" + color.getName(), Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(2.0F);
		this.setLightLevel(1F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING) == EnumFacing.UP) {
			return LAMP_AAB.offset(0, -0.71875D, 0);
		}
		
		else if(state.getValue(FACING) == EnumFacing.EAST) {
			return LAMP_AAB.offset(-0.71875D /2, -0.375D, 0); //not perfect
		}
		
		else if(state.getValue(FACING) == EnumFacing.NORTH) {
			return LAMP_AAB.offset(0, -0.375D, 0.71875D /2); //not perfect
		}
		
		else if(state.getValue(FACING) == EnumFacing.WEST) {
			return LAMP_AAB.offset(0.71875D /2, -0.375D, 0); //not perfect
		}
		
		else if(state.getValue(FACING) == EnumFacing.SOUTH) {
			return LAMP_AAB.offset(0, -0.375D, -0.71875D /2); //not perfect
		}
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

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	 public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing enumfacing;

	        switch (meta & 7)
	        {
	            case 0:
	                enumfacing = EnumFacing.DOWN;
	                break;
	            case 1:
	                enumfacing = EnumFacing.EAST;
	                break;
	            case 2:
	                enumfacing = EnumFacing.WEST;
	                break;
	            case 3:
	                enumfacing = EnumFacing.SOUTH;
	                break;
	            case 4:
	                enumfacing = EnumFacing.NORTH;
	                break;
	            case 5:
	            default:
	                enumfacing = EnumFacing.UP;
	        }

	        return this.getDefaultState().withProperty(FACING, enumfacing);
	    }
	
	public int getMetaFromState(IBlockState state)
	{
		int i;

		switch ((EnumFacing)state.getValue(FACING))
		{
		case EAST:
			i = 1;
			break;
		case WEST:
			i = 2;
			break;
		case SOUTH:
			i = 3;
			break;
		case NORTH:
			i = 4;
			break;
		case UP:
		default:
			i = 5;
			break;
		case DOWN:
			i = 0;
		}


		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}


	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, facing);
	}

}
