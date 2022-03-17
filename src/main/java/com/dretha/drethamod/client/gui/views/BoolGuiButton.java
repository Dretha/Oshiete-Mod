package com.dretha.drethamod.client.gui.views;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class BoolGuiButton extends GuiButton
{
    protected final String description;
    protected boolean currentValue;

    public BoolGuiButton(int buttonId, int x, int y, String description, boolean defaultValue) {
        this(buttonId, x, y, 150, 20, description, defaultValue);
    }
    public BoolGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String description, boolean defaultValue) {
        super(buttonId, x, y, widthIn, heightIn, description);
        this.description = description;
        currentValue = defaultValue;
        updateDisplayString();
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        boolean mousePressed = super.mousePressed(mc, mouseX, mouseY);
        if (mousePressed) {
            currentValue = !currentValue;
            updateDisplayString();
        }
        return mousePressed;
    }

    protected void updateDisplayString() {
        this.displayString = description + ": " + getStringValue();
    }
    protected String getStringValue() {
        if (currentValue)
            return I18n.format("options.on");
        else
            return I18n.format("options.off");
    }

    public boolean getValue() {
        return currentValue;
    }
}
