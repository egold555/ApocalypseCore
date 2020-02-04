package org.golde.apocalypsecore.common.features.misc.items.kit;

import java.util.List;

import org.golde.apocalypsecore.common.features.misc.FeatureMisc;
import org.golde.apocalypsecore.common.items._ACItem;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiConfigEntries.ChatColorEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKit extends _ACItem {

	public ItemKit() {
		super("kit");
		this.setHasSubtypes(true);
	}
	
	public static final AbstractKit[] kits;
	
	static {
		kits = new AbstractKit[] {
				new KitStarter()
		};
	}
	
	private static final String TAG_TYPE = "type";
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack is = playerIn.getHeldItem(handIn);
		
		if(worldIn.isRemote) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, is);
		}
		
		AbstractKit kit = getFromName(getKitNameFromTag(is));
		
		if(kit == null) {
			playerIn.sendMessage(new TextComponentString(ChatFormatting.RED + "No kit found."));
			worldIn.playSound(null, playerIn.getPosition(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.PLAYERS, 1, 1);
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, is);
		}
		else {
			worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_HORSE_ARMOR, SoundCategory.PLAYERS, 1, 1);
			playerIn.sendMessage(new TextComponentString(ChatFormatting.GREEN + "Kit " + ChatFormatting.GRAY + kit.getName() + ChatFormatting.GREEN + " equipped."));
			kit.go(playerIn);
			is.shrink(1);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		super.getSubItems(tab, items);
		if(isInCreativeTab(tab)) {
			for(AbstractKit kit : kits) {
				items.add(get(kit));
			}
		}
		
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		
		String name = getKitNameFromTag(stack);
		
		if(name == null) {
			name = "Invalid";
		}
		
		return name + " " + super.getItemStackDisplayName(stack);
	}
	
	public static ItemStack get(String name) {
		return get(name, 1);
	}
	
	public static ItemStack get(String name, int amount) {
		return get(getFromName(name), amount);
	}
	
	public static ItemStack get(AbstractKit kit) {
		return get(kit, 1);
	}
	
	public static ItemStack get(AbstractKit kit, int amount) {
		
		ItemStack item = new ItemStack(FeatureMisc.kit, amount);

		if(kit != null) {
			if(item.getTagCompound() == null) {
				item.setTagCompound(new NBTTagCompound());
			}
			item.getTagCompound().setString(TAG_TYPE, kit.getName());
		}
		
		return item;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Rick click to redeem.");
	}
	
	private String getKitNameFromTag(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey(TAG_TYPE)) {
			return stack.getTagCompound().getString(TAG_TYPE);
		}
		return null;
	}
	
	public static AbstractKit getFromName(String name) {
		if(name == null || name.isEmpty()) {
			return null;
		}
		for(AbstractKit kit : kits) {
			if(kit.getName().equalsIgnoreCase(name)) {
				return kit;
			}
		}
		
		return null;
	}

}
