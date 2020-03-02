package org.golde.apocalypsecore.common.features.misc.client.render.entity;

import java.util.HashMap;
import java.util.UUID;

import org.golde.apocalypsecore.common.features.misc.client.render.PlayerSkinGetter;
import org.golde.apocalypsecore.common.features.misc.entity.EntityDeadBody;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderDeadBody extends RenderBiped<EntityDeadBody> {

	public RenderDeadBody(RenderManager renderManager) {
		super(renderManager, new ModelZombie(), 0F);
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
		{
			protected void initArmor()
			{
				this.modelLeggings = new ModelZombie(0.5F, true);
				this.modelArmor = new ModelZombie(1.0F, true);
			}
		};
		this.addLayer(layerbipedarmor);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDeadBody entity) {

		UUID dbUUid = entity.getDbUuid();

		if(dbUUid != null) {

			PlayerSkinGetter getter = new PlayerSkinGetter(dbUUid);

			
			
			return getter.getLocationSkin();

		}
		return super.getEntityTexture(entity);
	}

}
