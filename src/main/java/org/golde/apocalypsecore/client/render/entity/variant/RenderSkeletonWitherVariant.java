package org.golde.apocalypsecore.client.render.entity.variant;

import org.golde.apocalypsecore.client.render.entity.variant.VariantManager.RandomTextureType;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWitherSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderSkeletonWitherVariant extends RenderWitherSkeleton {

	public RenderSkeletonWitherVariant(RenderManager p_i47188_1_) {
		super(p_i47188_1_);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(AbstractSkeleton entity) {
		return VariantManager.getRandomTexture(entity, RandomTextureType.SKELETON_WITHER);
	}

}
