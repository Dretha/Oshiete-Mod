package com.dretha.drethamod.client.gui.views;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;

public class HelpToast extends Gui {

    protected final ArrayList<String> text;
    protected static final int lineLength = 35;

    public HelpToast(String text) {
        this.text = breakIntoLines(text);
    }

    public void drawToast(int x, int y)
    {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledRes.getScaledWidth();
        int height = scaledRes.getScaledHeight();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int i = 0;

        for (String s : text)
        {
            int j = fontRenderer.getStringWidth(s);

            if (j > i)
            {
                i = j;
            }
        }

        int l1 = x + 12;
        int i2 = y - 12;
        int k = 8;

        if (text.size() > 1)
        {
            k += 2 + (text.size() - 1) * 10;
        }

        if (l1 + i > width)
        {
            l1 -= 28 + i;
        }

        if (i2 + k + 6 > height)
        {
            i2 = height - k - 6;
        }

        this.zLevel = 300.0F;
        this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
        this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
        this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
        this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
        this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
        this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
        this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

        for (int k1 = 0; k1 < text.size(); ++k1)
        {
            String s1 = text.get(k1);
            fontRenderer.drawStringWithShadow(s1, (float)l1, (float)i2, -1);

            if (k1 == 0)
            {
                i2 += 2;
            }

            i2 += 10;
        }

        this.zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

    protected ArrayList<String> breakIntoLines(String longString) {
        longString = " " + longString;
        ArrayList<String> list = new ArrayList<>();
        StringBuilder longStringBuilder = new StringBuilder(longString);

        int lines = (int)(Math.ceil((double)longString.length()/(double)lineLength))+1;
        lines = Math.max(1, lines);

        for (int line=0; line<lines; line++) {
            for (int i = 0; i < longStringBuilder.length(); i++) {
                if (i>lineLength && longStringBuilder.charAt(i)==' ')
                {
                    list.add(longStringBuilder.substring(0, i));
                    longStringBuilder.delete(0, i);
                    break;
                }
            }
            if (longStringBuilder.length()<=lineLength) {
                list.add(longStringBuilder.toString());
                break;
            }
            if (!longStringBuilder.toString().equals(" ") && !longStringBuilder.toString().equals("") && line+1==lines) {
                list.add(longStringBuilder.toString());
                break;
            }
        }

        if (list.size()>1)
            list.add(0, " " + I18n.format("helptoast.prompt") + ":");
        return list;
    }
}
