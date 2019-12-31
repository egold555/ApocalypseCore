package org.golde.apocalypsecore.common.features.misc.items;

import java.awt.Color;

import org.golde.apocalypsecore.common.items._ACItem;
import org.golde.apocalypsecore.common.utils.PaintUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemWrench extends _ACItem {

	public ItemWrench() {
		super("wrench");
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.isRemote) {
			return EnumActionResult.PASS;
		}

		byte scaleFactor = (byte)8;
		
		int[][] data = new int[128 / scaleFactor][128 / scaleFactor];
		
		for(int x = 0; x < data.length; x++) {
			for(int z = 0; z < data[x].length; z++) {
				data[x][z] = Color.HSBtoRGB((x * z) / 255F, 1, 0.5F);
			}
			
		}
		
		
		boolean success = PaintUtil.paintBlockServer(worldIn, pos, facing.getOpposite(), scaleFactor, data);
		
		
		player.sendMessage(new TextComponentString("Facing: " + facing.getName() + " succ: " + success));;
		
		return EnumActionResult.SUCCESS;
	}

}
