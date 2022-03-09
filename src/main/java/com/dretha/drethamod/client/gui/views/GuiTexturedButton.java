package com.dretha.drethamod.client.gui.views;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiTexturedButton extends GuiButton
{
    public static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/widgets.png");
    protected final int textureXOffset;
    protected final int textureYOffset;

    public GuiTexturedButton(int buttonId, int x, int y, int textureXOffset, int textureYOffset, int width)
    {
        super(buttonId, x, y, width, width, "");
        this.textureXOffset = textureXOffset;
        this.textureYOffset = textureYOffset;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(GuiTexturedButton.BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            int textureX = textureXOffset;
            int textureY = textureYOffset;
            boolean cursorOnButton = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (cursorOnButton) {
                textureY += height;
                hovered=true;
            } else
                hovered=false;

            this.drawTexturedModalRect(this.x, this.y, textureX, textureY, this.width, this.height);
        }
    }
}
