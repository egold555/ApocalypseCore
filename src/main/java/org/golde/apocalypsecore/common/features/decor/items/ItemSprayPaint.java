package org.golde.apocalypsecore.common.features.decor.items;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.gui.ForgeGuiHandler;
import org.golde.apocalypsecore.common.items._ACItem;
import org.golde.apocalypsecore.common.items._ACItemColor;
import org.golde.apocalypsecore.common.utils.PaintUtil;
import org.golde.apocalypsecore.common.utils.griffiti.ThreadCreateGraffiti;
import org.golde.apocalypsecore.common.utils.griffiti.ThreadCreateGraffiti.GraffitiCreatedCallback;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import scala.actors.threadpool.Arrays;

public class ItemSprayPaint extends _ACItemColor {

	public ItemSprayPaint() {
		super("spray_paint");
		this.setHasSubtypes(true);
		setMaxStackSize(1);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i < 255; i++) {
				float hue = i / 255F;
				Color c = Color.getHSBColor(hue, 0.85f, 1);
				ItemStack is = new ItemStack(this);

				this.setColor(is, c);
				items.add(is);
			}
		}

	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		if(playerIn.isSneaking()) {

			playerIn.openGui(ApocalypseCore.instance, ForgeGuiHandler.GUI_INDEX_COLOR_PICKER, worldIn, 0,0,0);

			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {


		if(player.isSneaking()) {

			player.openGui(ApocalypseCore.instance, ForgeGuiHandler.GUI_INDEX_COLOR_PICKER, worldIn, 0,0,0);

			return EnumActionResult.FAIL;
		}

		if(worldIn.isRemote) {
			return EnumActionResult.PASS;
		}



		ItemStack is = player.getHeldItemMainhand();

		int colorMain = getColor(player.getHeldItemMainhand());
		int colorSub = getColor(player.getHeldItemOffhand());

		player.sendMessage(new TextComponentString(colorMain + " " + colorSub));

		ThreadCreateGraffiti.create("TEST", colorMain, colorSub, new GraffitiCreatedCallback() {

			@Override
			public void onFinish(HashMap<int[], int[][]> data) {

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

					PaintUtil.paintBlockServer(worldIn, newPos, facing.getOpposite(), (byte)1, datadraw);
					player.sendMessage(new TextComponentString("Finiahsed: " + Arrays.toString(offset)));;
				}

			}

		});

		return EnumActionResult.SUCCESS;

	}

}
