package com.dretha.drethamod.client.gui.views;

import net.minecraft.client.gui.GuiButton;

public class ExpandedGuiButton extends GuiButton {
    public ExpandedGuiButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    public ExpandedGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public boolean cursorOnButton(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }
}
