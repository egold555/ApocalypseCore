package org.golde.apocalypsecore.client.render.entity.variant;

import org.golde.apocalypsecore.client.render.entity.variant.VariantManager.RandomTextureType;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPigZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombiePigVariant extends RenderPigZombie {

	public RenderZombiePigVariant(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityPigZombie entity) {
		return VariantManager.getRandomTexture(entity, RandomTextureType.ZOMBIE_PIG);
	}

}
