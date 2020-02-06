package org.golde.apocalypsecore.common.features.decor.items;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import org.golde.apocalypsecore.common.items._ACItem;
import org.golde.apocalypsecore.common.utils.PaintUtil;
import org.golde.apocalypsecore.common.utils.griffiti.ThreadCreateGraffiti;
import org.golde.apocalypsecore.common.utils.griffiti.ThreadCreateGraffiti.GraffitiCreatedCallback;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;

public class ItemSprayPaint extends _ACItem {

	public ItemSprayPaint() {
		super("spray_paint");
		this.setHasSubtypes(true);
		setMaxStackSize(1);
	}

	public static void setItemStackColor(ItemStack stack, Color color)
	{
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setInteger("color", color.getRGB());
		stack.setTagCompound(tag);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i < 255; i++) {
				float hue = i / 255F;
				Color c = Color.getHSBColor(hue, 0.85f, 1);
				ItemStack is = new ItemStack(this);

				setItemStackColor(is, c);
				items.add(is);
			}
		}

	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getTagCompound().hasKey("color")) {
			Color c = new Color(stack.getTagCompound().getInteger("color"));
			tooltip.add("R: " + c.getRed() + " G: " + c.getGreen() + " B: " + c.getBlue());
		}
	}

	public static int getColor(ItemStack is) {
		if(is == null || !(is.getItem() instanceof ItemSprayPaint)){
			return -1;
		}

		if(is.getTagCompound() == null || !is.getTagCompound().hasKey("color")) {
			return -1;
		}

		return is.getTagCompound().getInteger("color");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(worldIn.isRemote) {
			return EnumActionResult.PASS;
		}

		ItemStack is = player.getHeldItemMainhand();

		int colorMain = getColor(player.getHeldItemMainhand());
		int colorSub = getColor(player.getHeldItemOffhand());

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

					//worldIn.setBlockState(newPos, Blocks.COBBLESTONE.getDefaultState());
					PaintUtil.paintBlockServer(worldIn, newPos, facing.getOpposite(), (byte)1, datadraw);
					player.sendMessage(new TextComponentString("Finiahsed: " + Arrays.toString(offset)));;
				}

			}

		});

		return EnumActionResult.SUCCESS;

	}

}
