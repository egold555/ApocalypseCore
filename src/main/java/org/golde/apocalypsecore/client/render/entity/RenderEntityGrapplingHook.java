package org.golde.apocalypsecore.client.render.entity;

import java.util.UUID;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.entity.EntityGrapplingHook;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
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

	public void doRender(EntityGrapplingHook hook, double x, double y, double z, float entityYaw, float particleTicks) {
		this.bindEntityTexture(hook);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.rotate(hook.prevRotationYaw + (hook.rotationYaw - hook.prevRotationYaw) * particleTicks - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(hook.prevRotationPitch + (hook.rotationPitch - hook.prevRotationPitch) * particleTicks, 0.0F, 0.0F, 1.0F);
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
		float f11 = (float) hook.hookShake - particleTicks;

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

		//super
		this.renderLeash(hook, x, y, z, entityYaw, particleTicks);
		super.doRender(hook, x, y, z, entityYaw, particleTicks);
	}

	protected ResourceLocation getEntityTexture(EntityGrapplingHook p_180550_1_) {
		return hookTexture;
	}

	private double interpolateValue(double start, double end, double pct)
	{
		return start + (end - start) * pct;
	}

	protected void renderLeash(EntityGrapplingHook entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks)
	{
		Entity entity = null;
		String shootUUID = entityLivingIn.getDataManager().get(EntityGrapplingHook.SHOOT_UUID);
		if(shootUUID != null && shootUUID.length() > 0) {
			try {
				entity = entityLivingIn.world.getPlayerEntityByUUID(UUID.fromString(shootUUID));
			}
			catch(Exception e) {

			}
		}


		if (entity != null)
		{
			y = y - (1.6D - (double)entityLivingIn.height) * 0.5D;
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			double d0 = this.interpolateValue((double)entity.prevRotationYaw, (double)entity.rotationYaw, (double)(partialTicks * 0.5F)) * 0.01745329238474369D;
			double d1 = this.interpolateValue((double)entity.prevRotationPitch, (double)entity.rotationPitch, (double)(partialTicks * 0.5F)) * 0.01745329238474369D;
			double d2 = Math.cos(d0);
			double d3 = Math.sin(d0);
			double d4 = Math.sin(d1);

			if (entity instanceof EntityHanging)
			{
				d2 = 0.0D;
				d3 = 0.0D;
				d4 = -1.0D;
			}

			double d5 = Math.cos(d1);
			double d6 = this.interpolateValue(entity.prevPosX, entity.posX, (double)partialTicks) - d2 * 0.7D - d3 * 0.5D * d5;
			double d7 = this.interpolateValue(entity.prevPosY + (double)entity.getEyeHeight() * 0.7D, entity.posY + (double)entity.getEyeHeight() * 0.7D, (double)partialTicks) - d4 * 0.5D - 0.25D;
			double d8 = this.interpolateValue(entity.prevPosZ, entity.posZ, (double)partialTicks) - d3 * 0.7D + d2 * 0.5D * d5;
			double d9 = this.interpolateValue(0, 0, (double)partialTicks) * 0.01745329238474369D + (Math.PI / 2D);
			d2 = Math.cos(d9) * (double)entityLivingIn.width * 0.4D;
			d3 = Math.sin(d9) * (double)entityLivingIn.width * 0.4D;
			double d10 = this.interpolateValue(entityLivingIn.prevPosX, entityLivingIn.posX, (double)partialTicks) + d2;
			double d11 = this.interpolateValue(entityLivingIn.prevPosY, entityLivingIn.posY, (double)partialTicks);
			double d12 = this.interpolateValue(entityLivingIn.prevPosZ, entityLivingIn.posZ, (double)partialTicks) + d3;
			x = x + d2;
			z = z + d3;
			double d13 = (double)((float)(d6 - d10));
			double d14 = (double)((float)(d7 - d11));
			double d15 = (double)((float)(d8 - d12));
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			int i = 24;
			double d16 = 0.025D;
			bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

			for (int j = 0; j <= 24; ++j)
			{
				float f = 0.5F;
				float f1 = 0.4F;
				float f2 = 0.3F;

				if (j % 2 == 0)
				{
					f *= 0.7F;
					f1 *= 0.7F;
					f2 *= 0.7F;
				}

				float f3 = (float)j / 24.0F;
				bufferbuilder.pos(x + d13 * (double)f3 + 0.0D, y + d14 * (double)(f3 * f3 + f3) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F), z + d15 * (double)f3).color(f, f1, f2, 1.0F).endVertex();
				bufferbuilder.pos(x + d13 * (double)f3 + 0.025D, y + d14 * (double)(f3 * f3 + f3) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F) + 0.025D, z + d15 * (double)f3).color(f, f1, f2, 1.0F).endVertex();
			}

			tessellator.draw();
			bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

			for (int k = 0; k <= 24; ++k)
			{
				float f4 = 0.5F;
				float f5 = 0.4F;
				float f6 = 0.3F;

				if (k % 2 == 0)
				{
					f4 *= 0.7F;
					f5 *= 0.7F;
					f6 *= 0.7F;
				}

				float f7 = (float)k / 24.0F;
				bufferbuilder.pos(x + d13 * (double)f7 + 0.0D, y + d14 * (double)(f7 * f7 + f7) * 0.5D + (double)((24.0F - (float)k) / 18.0F + 0.125F) + 0.025D, z + d15 * (double)f7).color(f4, f5, f6, 1.0F).endVertex();
				bufferbuilder.pos(x + d13 * (double)f7 + 0.025D, y + d14 * (double)(f7 * f7 + f7) * 0.5D + (double)((24.0F - (float)k) / 18.0F + 0.125F), z + d15 * (double)f7 + 0.025D).color(f4, f5, f6, 1.0F).endVertex();
			}

			tessellator.draw();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
			GlStateManager.enableCull();
		}
	}

}
