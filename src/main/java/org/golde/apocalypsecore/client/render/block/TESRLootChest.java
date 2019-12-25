package org.golde.apocalypsecore.client.render.block;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.blocks.chest.loot.TIleEntityLootChest;

import net.minecraft.util.ResourceLocation;

public class TESRLootChest extends _ACTESRSingleChest<TIleEntityLootChest>{

	private static final ResourceLocation ENDER_CHEST_TEXTURE = new ResourceLocation(ApocalypseCore.MODID, "textures/blocks/loot_chest.png");
	
	@Override
	protected ResourceLocation getTexture() {
		return ENDER_CHEST_TEXTURE;
	}

}
