package org.golde.apocalypsecore.common.features.misc.items.kit;

import java.util.List;

import org.golde.apocalypsecore.common.items._ACItem;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKit extends _ACItem {

	public ItemKit() {
		super("kit");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack is = playerIn.getHeldItem(handIn);
		
		if(worldIn.isRemote) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, is);
		}
		
		
		
//		if(is.getTagCompound() == null) {
//			is.setTagCompound(new NBTTagCompound());
//		}
//		
//		is.getTagCompound().setString("type", "TEST");
		
		String kit = getKit(is);
		
		if(kit == null) {
			playerIn.sendMessage(new TextComponentString(ChatFormatting.RED + "No kit found."));
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, is);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		String kit = getKit(stack);
		
		if(kit == null) {
			kit = ChatFormatting.RED + "Invalid";
		}
		tooltip.add(ChatFormatting.GRAY + "Kit: " + kit);
	}
	
	private String getKit(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("type")) {
			return stack.getTagCompound().getString("type");
		}
		return null;
	}

}
