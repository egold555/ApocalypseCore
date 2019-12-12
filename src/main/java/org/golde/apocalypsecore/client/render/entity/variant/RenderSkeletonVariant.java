package org.golde.apocalypsecore.client.render.entity.variant;

import org.golde.apocalypsecore.client.render.entity.variant.VariantManager.RandomTextureType;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderSkeletonVariant extends RenderSkeleton {

	public RenderSkeletonVariant(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(AbstractSkeleton entity) {
		return VariantManager.getRandomTexture(entity, RandomTextureType.SKELETON);
	}

}
