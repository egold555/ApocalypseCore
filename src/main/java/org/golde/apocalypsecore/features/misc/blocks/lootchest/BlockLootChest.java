package org.golde.apocalypsecore.features.misc.blocks.lootchest;

import org.golde.apocalypsecore.blocks._core._ACBlockSingleChest;
import org.golde.apocalypsecore.blocks._core._ACTESingleChest;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;

public class BlockLootChest extends _ACBlockSingleChest<TIleEntityLootChest> {

	public BlockLootChest() {
		super("loot_chest", Material.IRON);
		this.setLightLevel(1.0F);
	}
	
	@Override
	public void onClickChest(_ACTESingleChest telc, boolean isOpenAlready) {
		if(!telc.isOpen()) {
			telc.openChest();
		}
		
	}

	@Override
	public Class<TIleEntityLootChest> getTileEntityClass() {
		return TIleEntityLootChest.class;
	}

	@Override
	public TIleEntityLootChest createTileEntity(World world, IBlockState state) {
		return new TIleEntityLootChest();
	}

}
