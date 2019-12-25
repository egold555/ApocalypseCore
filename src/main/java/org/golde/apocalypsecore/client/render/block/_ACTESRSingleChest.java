package org.golde.apocalypsecore.client.render.block;

import org.golde.apocalypsecore.ApocalypseCore;
import org.golde.apocalypsecore.blocks._core._ACTESingleChest;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class _ACTESRSingleChest<T extends _ACTESingleChest> extends _ACTESR<T>{

	 private static final _ACTESingleChest DUMMY_TE = new _ACTESingleChest();
	
    private final ModelChest modelChest = new ModelChest();

    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	
    	if(te == null) {
    		te = (T) DUMMY_TE;
    	}
    	
    	super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        int i = 0;
        if(te != null && te.getWorld() != null && te.getPos() != null) {
        	i = te.getBlockMetadata();
        }
        

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(getTexture());
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        int j = 0;

        if (i == 2)
        {
            j = 180;
        }

        if (i == 3)
        {
            j = 0;
        }

        if (i == 4)
        {
            j = 90;
        }

        if (i == 5)
        {
            j = -90;
        }

        GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        
        float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
        
        
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        this.modelChest.chestLid.rotateAngleX = -(f * ((float)Math.PI / 2F));
        this.modelChest.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
    
    protected abstract ResourceLocation getTexture();
	
}
