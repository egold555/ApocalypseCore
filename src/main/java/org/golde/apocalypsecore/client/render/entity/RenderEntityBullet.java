package org.golde.apocalypsecore.client.render.entity;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.client.model.entity.ModelBullet;
import org.golde.apocalypsecore.entity.EntityBullet;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityBullet extends Render<EntityBullet>
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ApocalypseCore.MODID, "textures/entity/bullet.png");
    private final ModelBullet model = new ModelBullet();
 
    public RenderEntityBullet(RenderManager manager) 
    {
		super(manager);
	}
 
    @Override
    public void doRender(EntityBullet entity, double x, double y, double z, float entityYaw, float partialTicks) {
        
    	//System.out.println("Rendering bullet");
    	
    	GL11.glPushMatrix();
        bindTexture(TEXTURES);
        
        GL11.glTranslated(x, y - 1.3, z);
        model.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }
    
    @Override
    public ResourceLocation getEntityTexture(EntityBullet entity)
    {
        return TEXTURES;
    }
}
