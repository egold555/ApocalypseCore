package org.golde.apocalypsecore.common.utils;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import ichttt.mods.mcpaint.MCPaint;
import ichttt.mods.mcpaint.common.EventHandler;
import ichttt.mods.mcpaint.common.MCPaintUtil;
import ichttt.mods.mcpaint.common.block.BlockCanvas;
import ichttt.mods.mcpaint.common.block.TileEntityCanvas;
import ichttt.mods.mcpaint.networking.MessageClearSide;
import ichttt.mods.mcpaint.networking.MessagePaintData;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class PaintUtil {

	public static boolean paintBlockServer(World world, BlockPos pos, EnumFacing facing, byte scale, int[][] data) {
		
		IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockCanvas) {
            TileEntityCanvas canvas = (TileEntityCanvas) Objects.requireNonNull(world.getTileEntity(pos));
            //We need to cache getBlockFaceShape as the method takes a world as an argument
            if (canvas.isSideBlockedForPaint(facing)) return false;
            paintDataServer(canvas, pos, facing, scale, data);
            return true;
        }

        //TODO substates for props... we need 5, we have 4... state.isFullCube = this.getDefaultState().isOpaqueCube
        if (state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID && state.getMaterial().isOpaque() && state.isFullBlock() == state.isFullCube() &&
                state.isFullCube() == state.isBlockNormalCube() && state.getRenderType() == EnumBlockRenderType.MODEL && !state.getBlock().hasTileEntity(state)) {
            Set<EnumFacing> disallowedFaces = EnumSet.noneOf(EnumFacing.class);
            for (EnumFacing testFacing : EnumFacing.VALUES) {
                if (state.getBlockFaceShape(world, pos, testFacing) != BlockFaceShape.SOLID)
                    disallowedFaces.add(testFacing);
            }

            if (state.getMaterial().getCanBurn())
                world.setBlockState(pos, EventHandler.CANVAS_WOOD.getStateFrom(state));
            else if (state.getMaterial().isToolNotRequired())
                world.setBlockState(pos, EventHandler.CANVAS_GROUND.getStateFrom(state));
            else
                world.setBlockState(pos, EventHandler.CANVAS_ROCK.getStateFrom(state));
            try {
            	 TileEntityCanvas canvas = (TileEntityCanvas) Objects.requireNonNull(world.getTileEntity(pos));
                 canvas.setInitialData(state, disallowedFaces);
                 canvas.markDirty();
                 
//                 for(EnumFacing f : EnumFacing.VALUES) {
//                 	paintDataServer(canvas, pos, f, scale, data);
//                 }
                 paintDataServer(canvas, pos, facing, scale, data);

                 return true;
            }
            catch(NullPointerException e) {
            	/**/
            	return false;
            }
        }
        return false;
    
		
	}
	
	private static void paintDataServer(TileEntityCanvas canvas, BlockPos pos, EnumFacing facing, byte scale, int[][] data) {

        
        if (data == null)
            canvas.removePaint(facing);
        else
            canvas.getPaintFor(facing).setData(scale, data, canvas, facing);
        canvas.markDirty();
        NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(canvas.getWorld().provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), -1);
        if (data == null) {
            MCPaint.NETWORKING.sendToAllTracking(new MessageClearSide(pos, facing), point);
        } else {
            MessagePaintData.createAndSend(pos, facing, scale, data, messagePaintData -> MCPaint.NETWORKING.sendToAllTracking(messagePaintData, point));
        }
        
	}
	
}
