package com.dretha.drethamod.client.gui.views;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class GuiSlider extends GuiButton
{
    protected float currentValue;
    protected float sliderValue;
    protected boolean dragging;
    public final String name;
    protected final boolean isFloat;
    public final float valueMin;
    public final float valueMax;
    public final float valueStep;

    public GuiSlider(int buttonId, int x, int y, String name, boolean isFloat, float valueMin, float valueMax, float valueStep, float defaultValue)
    {
        this(buttonId, x, y, 150, 20, name, isFloat, valueMin, valueMax, valueStep, defaultValue);
    }
    public GuiSlider(int buttonId, int x, int y, int width, int height, String name, boolean isFloat, float valueMin, float valueMax, float valueStep, float defaultValue)
    {
        super(buttonId, x, y, width, height, name);
        this.sliderValue = normalizeValue(defaultValue);
        this.currentValue = defaultValue;
        this.name = name;
        this.isFloat = isFloat;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.valueStep = valueStep;
        updateDisplayString();
        setCurrentValue(defaultValue);
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.sliderValue = MathHelper.clamp(this.sliderValue, 0, 1);
                this.currentValue = denormalizeValue(sliderValue);
                // установка опции
                updateDisplayString();
            }

            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp(this.sliderValue, 0, 1);
            this.currentValue = denormalizeValue(sliderValue);
            // установка опции
            updateDisplayString();
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void updateDisplayString() {
        if (isFloat)
            this.displayString = name + ": " + currentValue;
        else
            this.displayString = name + ": " + (int) currentValue;
    }

    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }

    public float normalizeValue(float value)
    {
        return MathHelper.clamp((this.snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
    }

    public float denormalizeValue(float value)
    {
        return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp(value, 0.0F, 1.0F));
    }

    public float snapToStepClamp(float value)
    {
        value = this.snapToStep(value);
        return MathHelper.clamp(value, this.valueMin, this.valueMax);
    }

    private float snapToStep(float value)
    {
        if (this.valueStep > 0.0F)
        {
            value = this.valueStep * (float)Math.round(value / this.valueStep);
        }

        return value;
    }

    public float getParameterValue() {
        return currentValue;
    }

    public void setCurrentValue(float value) {
        currentValue = value;
        sliderValue = normalizeValue(value);
    }
}
