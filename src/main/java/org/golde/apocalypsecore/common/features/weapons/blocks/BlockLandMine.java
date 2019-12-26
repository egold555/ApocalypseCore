package org.golde.apocalypsecore.features.weapons.blocks;

import java.util.List;

import org.golde.apocalypsecore.blocks._core._ACBlockPreasurePlate;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLandMine extends _ACBlockPreasurePlate {

	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public BlockLandMine() {
		super("land_mine");

	}

	@Override
	protected void playClickOnSound(World worldIn, BlockPos pos) {
		worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 6, true);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		//none
	}

	@Override
	protected void playClickOffSound(World worldIn, BlockPos pos) {
		//it never clicks off
	}

	@Override
	protected int computeRedstoneStrength(World worldIn, BlockPos pos) {

		if(worldIn.getBlockState(pos).getValue(ACTIVATED)) {
			return 15;
		}

		AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
		List <? extends Entity > list = worldIn.<Entity>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

		if (!list.isEmpty())
		{
			for (Entity entity : list)
			{
				if (!entity.doesEntityNotTriggerPressurePlate())
				{
					return 15;
				}
			}
		}

		return 0;
	}

	@Override
	protected int getRedstoneStrength(IBlockState state)
	{
		return ((Boolean)state.getValue(ACTIVATED)).booleanValue() ? 15 : 0;
	}

	//set activated if redstone signal is greater then 0
	@Override
	protected IBlockState setRedstoneStrength(IBlockState state, int strength) {
		return state.withProperty(ACTIVATED, (strength > 0));
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(ACTIVATED, Boolean.valueOf(meta == 1));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Boolean)state.getValue(ACTIVATED)).booleanValue() ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {ACTIVATED});
	}

}
