package org.golde.apocalypsecore.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import org.golde.apocalypsecore.client.model.fluid.FluidStateMapper;
import org.golde.apocalypsecore.common.ApocalypseCore;

public class _ACBlockFluidClassic extends BlockFluidClassic implements _IACBlock 
{
	private int flammability = 0;
	private int fireSpread = 0;
	private PotionEffect[] potionEffects;

	private final Fluid theFluid;
	
	public _ACBlockFluidClassic(String name, Fluid fluid)
	{
		super(fluid, Material.WATER);
		setUnlocalizedName(ApocalypseCore.MODID + "." + name);
		setRegistryName(name);
		setHardness(100.0F);
		setLightOpacity(3);
		theFluid = fluid;
	}

	public _ACBlockFluidClassic setFlammability(int flammability, int fireSpread)
	{
		this.flammability = flammability;
		this.fireSpread = fireSpread;
		return this;
	}
	public _ACBlockFluidClassic setPotionEffects(PotionEffect... potionEffects)
	{
		this.potionEffects = potionEffects;
		return this;
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return this.flammability;
	}
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return fireSpread;
	}
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return this.flammability>0;
	}


	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		super.onEntityCollidedWithBlock(world, pos, state, entity);
		if(potionEffects!=null && entity instanceof EntityLivingBase)
		{
			for(PotionEffect effect : potionEffects) {
				if(effect!=null) {
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(effect));
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		Item item = Item.getItemFromBlock(this);
		FluidStateMapper mapper = new FluidStateMapper(theFluid);
		if(item != null)
		{
			ModelLoader.registerItemVariants(item);
			ModelLoader.setCustomMeshDefinition(item, mapper);
		}
		ModelLoader.setCustomStateMapper(this, mapper);
	}

}
