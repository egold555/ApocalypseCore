package org.golde.apocalypsecore.common.features.misc.items;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.golde.apocalypsecore.common.items._ACItem;
import org.golde.apocalypsecore.common.utils.PaintUtil;
import org.golde.apocalypsecore.common.utils.ThreadDownloadUrlSpraypaint;
import org.golde.apocalypsecore.common.utils.ThreadDownloadUrlSpraypaint.ResourceLocationCallback;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

//		byte scaleFactor = (byte)8;
//		
//		int[][] data = new int[128 / scaleFactor][128 / scaleFactor];
//		
//		for(int x = 0; x < data.length; x++) {
//			for(int z = 0; z < data[x].length; z++) {
//				data[x][z] = Color.HSBtoRGB((x * z) / 255F, 1, 0.5F);
//			}
//			
//		}
		
		ThreadDownloadUrlSpraypaint.downloadAndSetTexture("http://localhost/websites/spray/?r=255&b=255", new ResourceLocationCallback() {
			
			@Override
			public void onTextureLoaded(BufferedImage bi) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDataParsed(int[][] data, int imgW, int imgH) {
				// TODO Auto-generated method stub
				//boolean success = PaintUtil.paintBlockServer(worldIn, pos, facing.getOpposite(), (byte)1, data);
				
				
				//player.sendMessage(new TextComponentString("Download finished"));;
			}
			
			@Override
			public void onSplit(HashMap<int[], int[][]> data) {
				
				for(int[] offset : data.keySet()) {
					
					BlockPos newPos = pos;
					
					switch(facing) {
					case DOWN:
						newPos = pos.add(-offset[0], 0, offset[1]);
						break;
					case EAST:
						//works
						newPos = pos.add(0, -offset[1], -offset[0]);
						break;
					case NORTH:
						//works
						newPos = pos.add(-offset[0], -offset[1], 0);
						break;
					case SOUTH:
						//works
						newPos = pos.add(offset[0], -offset[1], 0);
						break;
					case UP:
						newPos = pos.add(offset[0], 0, offset[1]);
						break;
					case WEST:
						//work
						newPos = pos.add(0, -offset[1], offset[0]);
						break;
					default:
						break;
					
					}
					
					
					int[][] datadraw = data.get(offset); 
					
					//worldIn.setBlockState(newPos, Blocks.COBBLESTONE.getDefaultState());
					PaintUtil.paintBlockServer(worldIn, newPos, facing.getOpposite(), (byte)1, datadraw);
					player.sendMessage(new TextComponentString("Download finished"));;
				}
				
			}
		});
		
		player.sendMessage(new TextComponentString("Facing: " + facing.getName()));
		
		
		return EnumActionResult.SUCCESS;
	}

}
