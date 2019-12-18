package org.golde.apocalypsecore.item._core;

import org.golde.apocalypsecore.ACTabs;
import org.golde.apocalypsecore.ApocalypseCore;

import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Like the Sword class for weapons
public class _ACItemMeleeWeapon extends ItemSword implements _IACItem {

	private final float attackDamageIn;
	private final int enchantability;
	private final ItemStack repairItemstack;

	public _ACItemMeleeWeapon(String name, float attackDamage) {
		this(name, attackDamage, (ItemStack)null);
	}

	public _ACItemMeleeWeapon(String name, float attackDamage, ItemStack repair) {
		this(name, attackDamage, repair, 0);
	}

	public _ACItemMeleeWeapon(String name, float attackDamage, Item repair) {
		this(name, attackDamage, new ItemStack(repair));
	}

	public _ACItemMeleeWeapon(String name, float attackDamage, int enchantability) {
		this(name, attackDamage, (ItemStack)null, enchantability);
	}

	public _ACItemMeleeWeapon(String name, float attackDamage, Item repair, int enchantability) {
		this(name, attackDamage, new ItemStack(repair), enchantability);
	}
	
	public _ACItemMeleeWeapon(String name, float attackDamage, ItemStack repair, int enchantability) {
		super(ToolMaterial.WOOD); //we override all method that use the toolMaterial, so what we pass in doesnt much matter

		setRegistryName(name);
		setUnlocalizedName(ApocalypseCore.MODID + "." + name);
		if(shouldBeInCreatveTab()) {setCreativeTab(ACTabs.WEAPONS);}
		this.attackDamageIn = attackDamage;
		this.enchantability = enchantability;
		this.repairItemstack = repair;
		
	}

	@Override //no material override
	public float getAttackDamage()
	{
		return this.attackDamageIn;
	}

	@Override //Item class
	public boolean canHarvestBlock(IBlockState blockIn)
	{
		return false;
	}

	@Override //furnace fix
	public String getToolMaterialName() {
		return "NOT-A-BURNABLE-MATERIAL";
	}

	@Override //no material fix
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		if (repairItemstack != null && !repairItemstack.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(repairItemstack, repair, false)) return true;
		return super.getIsRepairable(toRepair, repair);
	}

	//Item class
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return 1.0f;
	}

	@Override
	public int getItemEnchantability() {
		return enchantability;
	}

	@Override //No material fix
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamageIn, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}

		return multimap;
	}

	//AC Item
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	//AC Item
	public _ACItemMeleeWeapon setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
