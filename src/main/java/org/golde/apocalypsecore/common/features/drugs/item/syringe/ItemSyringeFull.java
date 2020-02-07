package org.golde.apocalypsecore.common.features.drugs.item.syringe;

import java.awt.Color;
import java.util.List;

import org.golde.apocalypsecore.common.features.drugs.FeatureDrugs;
import org.golde.apocalypsecore.common.items._ACItemColor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSyringeFull extends _ACItemColor {

	public ItemSyringeFull() {
		super("syringe_full");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setMaxStackSize(16);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i < 255; i++) {
				float hue = i / 255F;
				Color c = Color.getHSBColor(hue, 1, 1);
				ItemStack is = new ItemStack(this);
				//setItemStackName(is, "R: " + c.getRed() + " G: " + c.getGreen() + " B: " + c.getBlue());
				setColor(is, c);
				items.add(is);
			}

			//			for(String s : Drugs.DRUGS) {
			//				ItemStack is = new ItemStack(this);
			//				setItemStackColor(is, Drugs.getHashColor(s));
			//				setItemStackName(is, s);
			//				items.add(is);
			//			}
		}

	}


	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getTagCompound().hasKey("name")) {
			String name = stack.getTagCompound().getString("name");
			tooltip.add(name);
			//			tooltip.add("");
			//			tooltip.add("Symptoms");
			//			for(String s : Drugs.getSymptoms(name)) {
			//				tooltip.add("   - " + s);
			//			}
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);

		if (!player.capabilities.isCreativeMode)
		{
			itemstack.shrink(1);
		}

		if (itemstack == null || itemstack.isEmpty())
		{
			itemstack = new ItemStack(FeatureDrugs.syringeEmpty);
		}


		player.attackEntityFrom(FeatureDrugs.DAMAGE_SOURCE_INJECTION, 1.0F);
		worldIn.playSound(null, player.getPosition(), FeatureDrugs.SOUND_SYRINGE, SoundCategory.PLAYERS, 1, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	}

	public static void setItemStackName(ItemStack stack, String name)
	{
		NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		tag.setString("name", name);
		stack.setTagCompound(tag);
	}

}
