package com.dretha.drethamod.client.gui;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

public class GuiDocument extends GuiScreen {

    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(Reference.MODID,"textures/gui/document.png");
    protected final ArrayList<String> text;
    protected final int textColor = new Color(0, 0, 0).getRGB();

    public GuiDocument(ArrayList<String> text) {
        this.text = text;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int widthDocument = 174;
        int x = this.width/2 - widthDocument/2;
        this.drawTexturedModalRect(x, 2, 0, 0, widthDocument, 194);

        drawCenteredStringNotShadow(I18n.format("item.blood_test.name"), width/2, 7, textColor);

        for (int i=0; i<text.size(); i++)
            drawStringNotShadow(text.get(i), x+12, 18 + i*10, textColor, 3);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawCenteredStringNotShadow(String text, int x, int y, int color) {
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text)/2, y, color);
    }
    private void drawStringNotShadow(String text, int x, int y, int color) {
        fontRenderer.drawString(text, x, y, color);
    }
    private void drawCenteredStringNotShadow(String text, int x, int y, int color, int height) {
        int prevHeight = fontRenderer.FONT_HEIGHT;
        fontRenderer.FONT_HEIGHT = height;
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text)/2, y, color);
        fontRenderer.FONT_HEIGHT = prevHeight;
    }
    private void drawStringNotShadow(String text, int x, int y, int color, int height) {
        int prevHeight = fontRenderer.FONT_HEIGHT;
        fontRenderer.FONT_HEIGHT = height;
        fontRenderer.drawString(text, x, y, color);
        fontRenderer.FONT_HEIGHT = prevHeight;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
