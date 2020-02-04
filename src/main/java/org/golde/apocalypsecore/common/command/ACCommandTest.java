package org.golde.apocalypsecore.common.command;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.golde.apocalypsecore.common.features.building.FeatureBuilding;
import org.golde.apocalypsecore.common.features.misc.entity.EntityFallingLootCrate;
import org.golde.apocalypsecore.common.features.weapons.FeatureWeapons;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class ACCommandTest extends CommandBase {

	@Override
	public String getName() {
		return "actest";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		server.getPlayerList().sendMessage(new TextComponentString("global chat message"));
		
		if(sender instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)sender;
			
			applied(p.inventory);
		}

		

	}
	
	private void applied(InventoryPlayer ip) {
	
		ItemStack helmet = armor(Items.IRON_HELMET, "Helmet");
		ItemStack chestplate = armor(Items.IRON_CHESTPLATE, "Chestplate");
		ItemStack leggings = armor(Items.IRON_LEGGINGS, "Leggings");
		ItemStack boots = armor(Items.IRON_BOOTS, "Boots");
		
		ItemStack pickaxe = tools(Items.IRON_PICKAXE, "Pickaxe");
		ItemStack axe = tools(Items.IRON_AXE, "Axe");
		ItemStack spade = tools(Items.IRON_SHOVEL, "Shovel");
		
		ItemStack sword = new ItemStack(Items.IRON_SWORD);
		sword.addEnchantment(Enchantments.UNBREAKING, 1);
		sword.addEnchantment(Enchantments.SHARPNESS, 1);
		sword.setStackDisplayName(ChatFormatting.LIGHT_PURPLE + "Starter Sword");
		
		ip.armorInventory.set(0, boots);
		ip.armorInventory.set(1, leggings);
		ip.armorInventory.set(2, chestplate);
		ip.armorInventory.set(3, helmet);
		
		ip.addItemStackToInventory(pickaxe);
		ip.addItemStackToInventory(axe);
		ip.addItemStackToInventory(spade);
		ip.addItemStackToInventory(sword);
		
		ip.addItemStackToInventory(new ItemStack(Items.COOKED_CHICKEN, 32));
		ip.addItemStackToInventory(new ItemStack(Blocks.TORCH, 64));
		ip.addItemStackToInventory(new ItemStack(FeatureWeapons.grapplingHook));
		ip.addItemStackToInventory(new ItemStack(FeatureBuilding.barbedWire, 32));
		
	}
	
	private ItemStack armor(Item item, String name) {
		ItemStack helmet = new ItemStack(item);
		helmet.addEnchantment(Enchantments.UNBREAKING, 1);
		helmet.addEnchantment(Enchantments.PROTECTION, 1);
		helmet.setStackDisplayName(ChatFormatting.LIGHT_PURPLE + "Starter " + name);
		return helmet;
	}
	
	private ItemStack tools(Item item, String name) {
		ItemStack helmet = new ItemStack(item);
		helmet.addEnchantment(Enchantments.UNBREAKING, 1);
		helmet.addEnchantment(Enchantments.EFFICIENCY, 1);
		helmet.setStackDisplayName(ChatFormatting.LIGHT_PURPLE + "Starter " + name);
		return helmet;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
	{
		return Collections.<String>emptyList();
	}

}
