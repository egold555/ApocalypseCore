package org.golde.apocalypsecore.client.gui;

import java.awt.Point;
import java.io.IOException;

import org.golde.apocalypsecore.common.network.ACPacketHandler;
import org.golde.apocalypsecore.common.network.packets.ACPacketColorGuiClosed;
import org.golde.apocalypsecore.common.network.packets.ACPacketGraffitiText;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;

public class GuiGraffitiText extends GuiScreen {

	private GuiTextField nameEdit;

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.format("gui.ok")));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.format("gui.cancel")));
		this.nameEdit = new GuiTextField(2, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
		this.nameEdit.setFocused(true);
		//this.nameEdit.setText(s);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		
		super.onGuiClosed();
	}

	@Override
	public void updateScreen()
	{
		this.nameEdit.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		this.nameEdit.textboxKeyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.nameEdit.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 0)
            {
            	ACPacketHandler.sendToServer(new ACPacketGraffitiText(nameEdit.getText()));
            }
            else {
            	Minecraft.getMinecraft().displayGuiScreen(null);
            }
        }
    }

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawCenteredString(this.fontRenderer, I18n.format("selectWorld.edit.title"), this.width / 2, 20, 16777215);
		this.drawString(this.fontRenderer, I18n.format("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
		this.nameEdit.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}


}
