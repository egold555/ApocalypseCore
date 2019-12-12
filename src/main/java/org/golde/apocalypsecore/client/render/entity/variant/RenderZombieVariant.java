package org.golde.apocalypsecore.client.render.entity.variant;

import org.golde.apocalypsecore.client.render.entity.variant.VariantManager.RandomTextureType;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombieVariant extends net.minecraft.client.renderer.entity.RenderZombie {

	public RenderZombieVariant(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityZombie entity) {
		return VariantManager.getRandomTexture(entity, RandomTextureType.ZOMBIE);
	}

}
