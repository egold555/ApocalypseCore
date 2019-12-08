package org.golde.apocalypsecore.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Bullet - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelBullet extends ModelBase {
    
	public ModelRenderer shape1;

    public ModelBullet() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 5, 6);
        this.shape1.setRotationPoint(-1.3F, 22.7F, -0.5F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
    { 
        this.shape1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
