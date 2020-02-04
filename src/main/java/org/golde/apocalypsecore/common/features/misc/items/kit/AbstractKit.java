package org.golde.apocalypsecore.common.features.misc.items.kit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class AbstractKit {
	
	public abstract String getName();

	public ItemStack getHelmet() {return ItemStack.EMPTY;}
	public ItemStack getChestplate() {return ItemStack.EMPTY;}
	public ItemStack getLeggings() {return ItemStack.EMPTY;}
	public ItemStack getBoots() {return ItemStack.EMPTY;}
	
	public ItemStack[] getItems() {return new ItemStack[] {ItemStack.EMPTY};};
	
	private List<ItemStack> thingsThatDontFit = new ArrayList<ItemStack>();
	
	public void go(EntityPlayer p) {
		
		if(p.world.isRemote) {
			return;
		}
		
		InventoryPlayer i = p.inventory;
		
		tryFillArmor(i);
		tryToAddThingThatDontFitToInventory(i);
		tryFillInventory(i);
		dropEverythingThatDoesNotFit(i);
	}
	
	private void tryFillInventory(InventoryPlayer i) {
		for(ItemStack is : getItems()) {
			if(is == null || is.isEmpty()) {
				continue;
			}
			boolean success = i.addItemStackToInventory(is);
			if(!success) {
				thingsThatDontFit.add(is);
			}
		}
	}
	
	private void tryFillArmor(InventoryPlayer i) {
		
		//try to add the armor
		if(armorEmpty(i, 0)) {
			i.armorInventory.set(0, getBoots());
		}
		else {
			thingsThatDontFit.add(getBoots());
		}
		
		if(armorEmpty(i, 1)) {
			i.armorInventory.set(1, getLeggings());
		}
		else {
			thingsThatDontFit.add(getLeggings());
		}
		
		if(armorEmpty(i, 2)) {
			i.armorInventory.set(2, getChestplate());
		}
		else {
			thingsThatDontFit.add(getChestplate());
		}
		
		if(armorEmpty(i, 3)) {
			i.armorInventory.set(3, getHelmet());
		}
		else {
			thingsThatDontFit.add(getHelmet());
		}
		
		
	}
	
	private void tryToAddThingThatDontFitToInventory(InventoryPlayer i) {
		List<ItemStack> temp = new ArrayList<ItemStack>();
		for(ItemStack is : thingsThatDontFit) {
			if(is == null || is.isEmpty()) {
				continue;
			}
			boolean success = i.addItemStackToInventory(is);
			if(!success) {
				temp.add(is);
			}
		}
		
		thingsThatDontFit.clear();
		thingsThatDontFit.addAll(temp);
	}
	
	private void dropEverythingThatDoesNotFit(InventoryPlayer i) {
		
		EntityPlayer player = i.player;
		World world = player.world;
		
		if(thingsThatDontFit.size() > 0) {
			player.sendMessage(new TextComponentString(ChatFormatting.RED + "Not every item count fit in your inventory. Remaining items have been dropped."));
		}
		
		for(ItemStack is : thingsThatDontFit) {
			EntityItem entityitem = new EntityItem(world, player.posX, player.posY + 0.5, player.posZ, is);
            entityitem.setPickupDelay(40);
            entityitem.motionX = 0;
            entityitem.motionZ = 0;

            world.spawnEntity(entityitem);
		}
		
		thingsThatDontFit.clear();
		
	}
	
	private boolean armorEmpty(InventoryPlayer i, int id) {
		return (i.armorInventory.get(id) == null || i.armorInventory.get(id).isEmpty());
	}
}
