package org.golde.apocalypsecore.client.gui;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.nio.IntBuffer;

import org.golde.apocalypsecore.common.ApocalypseCore;
import org.golde.apocalypsecore.common.features.decor.FeatureDecor;
import org.golde.apocalypsecore.common.items._ACItemColor;
import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.ACPacketColorGuiClosed;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

//Modified from https://github.com/Wyn-Price/ModJam5/blob/master/src/main/java/com/wynprice/modjam5/client/gui/GuiColorWheel.java

public class GuiColorWheel extends GuiScreen
{

	private Point currentColorWheel = new Point(-1, -1);
	
	private int prevColor = Color.WHITE.getRGB();
	private int noBrightnessColor = -1;
	
	
	@Override
	public void initGui() 
	{
		super.initGui();
		
		ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
		
		NBTTagCompound tag = stack.getOrCreateSubCompound("colorPicker");
		if(tag.hasKey("pointX", 99) && tag.hasKey("pointY", 99)) {
			this.currentColorWheel = new Point(Minecraft.getMinecraft().displayWidth / 2 + tag.getInteger("pointX"), Minecraft.getMinecraft().displayHeight / 2 + tag.getInteger("pointY"));
			this.prevColor = _ACItemColor.getColor(stack);
			
			barAmount = tag.getFloat("lightnessValue");
		} else {
			this.currentColorWheel = new Point(Minecraft.getMinecraft().displayWidth / 2, Minecraft.getMinecraft().displayHeight / 2);
			barAmount = 1f;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();		

		GlStateManager.enableAlpha();
		GlStateManager.disableLighting();
		
		
		int colorPre = getColorUnderMouse();
		

		Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(new ResourceLocation(ApocalypseCore.MODID, "textures/gui/color_picker/color_wheel.png"));
		this.drawModalRectWithCustomSizedTexture(this.width / 2 - 64, this.height / 2 - 64, 0, 0, 128, 128, 128, 128);

		int newColor = getColorUnderMouse();
	
		if(colorPre != newColor && Mouse.isButtonDown(0) && !lightnessBarClicked) {
			this.currentColorWheel = new Point(Mouse.getX(), Mouse.getY());
			this.prevColor = newColor;
			this.noBrightnessColor = newColor;
		}
		
		if(lightnessBarClicked || noBrightnessColor == -1) {
			IntBuffer intbuffer = BufferUtils.createIntBuffer(1);
	        int[] ints = new int[1];
	        GlStateManager.glReadPixels(currentColorWheel.x, currentColorWheel.y, 1, 1, 32993, 33639, intbuffer);
	        intbuffer.get(ints);
	        prevColor = ints[0];
	        if(noBrightnessColor == -1) {
	        	noBrightnessColor = prevColor;
	        }
	        
		}
				
		float[] hsbs = Color.RGBtoHSB((prevColor >> 16) & 0xFF, (prevColor >> 8) & 0xFF, prevColor & 0xFF, null);
		prevColor = Color.HSBtoRGB(hsbs[0], hsbs[1], barAmount);
		
		Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(new ResourceLocation(ApocalypseCore.MODID, "textures/gui/color_picker/color_wheel_border.png"));
		this.drawModalRectWithCustomSizedTexture(this.width / 2 - 65, this.height / 2 - 65, 0, 0, 130, 130, 130, 130);
		
		Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(new ResourceLocation(ApocalypseCore.MODID, "textures/gui/color_picker/color_wheel_cursor.png"));
		this.drawModalRectWithCustomSizedTexture(currentColorWheel.x * new ScaledResolution(this.mc).getScaledWidth() / this.mc.displayWidth - 4, 
				new ScaledResolution(this.mc).getScaledHeight() - currentColorWheel.y * new ScaledResolution(this.mc).getScaledHeight() / this.mc.displayHeight - 1 - 4, 0, 0, 8, 8, 8, 8);
		
		
		this.drawHorizontalGradient(this.width / 2 + 70, this.height / 5 * 4 + 20, this.width / 2 - 70, this.height / 5 * 4, 0xFF000000, noBrightnessColor);		
		
		int xPosition = (int) (this.width / 2 + 70 - (1 - barAmount) * 140);
		this.drawRect(xPosition - 3, this.height / 5 * 4 - 3, xPosition + 3, this.height / 5 * 4 + 23, 0xFF000000);
		
		drawBorder(this.width / 2 + 70, this.height / 5 * 4 + 20, this.width / 2 - 70, this.height / 5 * 4, 0xFF000000, 2);
		
		GlStateManager.scale(5, 5, 5);
		Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(_ACItemColor.setColor(new ItemStack(FeatureDecor.sprayPaint), prevColor), this.width / 80 * 3 - 8, this.height / 10 - 8);		
		GlStateManager.scale(-5, -5, -5);

		super.drawScreen(mouseX, mouseY, partialTicks);		

	}
	
	private boolean lightnessBarClicked = false;
	private float barAmount = 1f;
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseX >= this.width / 2 - 70 && mouseX <= this.width / 2 + 70 && mouseY >= this.height / 5 * 4 && mouseY <= this.height / 5 * 4 + 20) {
			lightnessBarClicked = true;
			barAmount = (mouseX - this.width / 2 + 70) / 140f;
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		lightnessBarClicked = false;
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		if(lightnessBarClicked) {
			barAmount = MathHelper.clamp((mouseX - this.width / 2 + 70) / 140f, 0f, 1f);
		}
	}
	
	@Override
	public void onGuiClosed() {
		System.out.println(prevColor);
		ACPacketHandler.sendToServer(new ACPacketColorGuiClosed(prevColor, new Point(currentColorWheel.x - Minecraft.getMinecraft().displayWidth / 2, currentColorWheel.y - Minecraft.getMinecraft().displayHeight / 2), barAmount));
		super.onGuiClosed();
	}
	
	public static int getColorUnderMouse()
	{
        IntBuffer intbuffer = BufferUtils.createIntBuffer(1);
        int[] ints = new int[1];
        GlStateManager.glReadPixels(Mouse.getX(), Mouse.getY(), 1, 1, 32993, 33639, intbuffer);
        intbuffer.get(ints);
        return ints[0];
	}
	
	public void drawHorizontalGradient(int left, int top, int right, int bottom, int startColor, int endColor)
    {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)this.zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)this.zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)this.zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)this.zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
	
	public void drawBorder(int left, int top, int right, int bottom, int color, int thickness) {
		thickness /= 2;
		GuiScreen.drawRect(left - thickness, top - thickness, right + thickness, top + thickness, color);
        GuiScreen.drawRect(left - thickness, bottom - thickness, right + thickness, bottom + thickness, color);
        GuiScreen.drawRect(left - thickness, top + thickness, left + thickness, bottom - thickness, color);
        GuiScreen.drawRect(right + thickness, top + thickness, right - thickness, bottom - thickness, color);
	}

}
