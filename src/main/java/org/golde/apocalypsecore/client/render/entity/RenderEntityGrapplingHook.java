package org.golde.apocalypsecore.client.render.entity;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.entity.EntityGrapplingHook;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityGrapplingHook extends Render<EntityGrapplingHook> {

	private static final ResourceLocation hookTexture = new ResourceLocation(ApocalypseCore.MODID, "textures/entities/grappling_hook.png");

    public RenderEntityGrapplingHook(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void doRender(EntityGrapplingHook hook, double p_180551_2_, double p_180551_4_, double p_180551_6_, float p_180551_8_, float p_180551_9_) {
        this.bindEntityTexture(hook);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate((float) p_180551_2_, (float) p_180551_4_, (float) p_180551_6_);
        GlStateManager.rotate(hook.prevRotationYaw + (hook.rotationYaw - hook.prevRotationYaw) * p_180551_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(hook.prevRotationPitch + (hook.rotationPitch - hook.prevRotationPitch) * p_180551_9_, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float) (0 + b0 * 10) / 32.0F;
        float f5 = (float) (7 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = (float) (7 + b0 * 10) / 32.0F;
        float f8 = (float) (7 + b0 * 10) / 32.0F;
        float f9 = (float) (14 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;

        GlStateManager.enableRescaleNormal();
        float f11 = (float) hook.hookShake - p_180551_9_;

        if (f11 > 0.0F) {
            float i = -MathHelper.sin(f11 * 3.0F) * f11;

            GlStateManager.rotate(i, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(f10, f10, f10);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(5.5D, -3.0D, -3.0D).tex((double) f6, (double) f8).endVertex();
        bufferbuilder.pos(5.5D, -3.0D, 3.0D).tex((double) f7, (double) f8).endVertex();
        bufferbuilder.pos(5.5D, 3.0D, 3.0D).tex((double) f7, (double) f9).endVertex();
        bufferbuilder.pos(5.5D, 3.0D, -3.0D).tex((double) f6, (double) f9).endVertex();
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(5.5D, 2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
        bufferbuilder.pos(5.5D, 2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
        bufferbuilder.pos(5.5D, -2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
        bufferbuilder.pos(5.5D, -2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
        tessellator.draw();

        for (int i = 0; i < 4; ++i) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(-8.0D, -3.0D, 0.0D).tex((double) f2, (double) f4).endVertex();
            bufferbuilder.pos(8.0D, -3.0D, 0.0D).tex((double) f3, (double) f4).endVertex();
            bufferbuilder.pos(8.0D, 3.0D, 0.0D).tex((double) f3, (double) f5).endVertex();
            bufferbuilder.pos(-8.0D, 3.0D, 0.0D).tex((double) f2, (double) f5).endVertex();
            tessellator.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        super.doRender(hook, p_180551_2_, p_180551_4_, p_180551_6_, p_180551_8_, p_180551_9_);
    }

    protected ResourceLocation getEntityTexture(EntityGrapplingHook p_180550_1_) {
        return hookTexture;
    }
	
}
