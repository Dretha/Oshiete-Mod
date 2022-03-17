package com.dretha.drethamod.client.gui;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.client.gui.views.BoolGuiButton;
import com.dretha.drethamod.client.gui.views.GuiSlider;
import com.dretha.drethamod.client.gui.views.HelpToast;
import com.dretha.drethamod.entity.human.SkinHandler;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class GuiOshieteOptions extends GuiScreen
{
    protected String title = I18n.format("oshieteoptions.mainbutton.name");

    protected PersonStats stats;
    protected EntityPlayer player;
    protected ICapaHandler capa;

    HelpToast offsetXhelp = new HelpToast(I18n.format("helptoast.offsetx"));
    HelpToast copyrightHelp = new HelpToast(I18n.format("helptoast.copyright"));
    HelpToast darkeningHelp = new HelpToast(I18n.format("helptoast.darkening"));

    @Override
    public void initGui() {
        player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
        capa = player.getCapability(CapaProvider.PLAYER_CAP, null);
        stats = capa.personStats();

        this.buttonList.add(new GuiSlider(0, this.width / 2 - 155, this.height / 6, I18n.format("oshieteoptions.cameraslider.name"), true, -1F, 1F, 0.05F, capa.getCameraOffset()));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 5+32+5, this.height / 6, 113, 20, I18n.format("oshieteoptions.buttonchangekakugan.name")));
        this.buttonList.add(new BoolGuiButton(2, this.width / 2 + 5, this.height / 6 + 50, I18n.format("oshieteoptions.copyright"), capa.isCopyrightMode()));
        this.buttonList.add(new BoolGuiButton(3, this.width / 2 - 155, this.height / 6 + 25, I18n.format("oshieteoptions.darkening"), capa.isDarkeningKagune()));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);

        super.drawScreen(mouseX, mouseY, partialTicks);

        mc.getTextureManager().bindTexture(mc.player.getLocationSkin());
        this.drawTexturedModalRect(this.width / 2 + 5, this.height / 6, 32, 32, 32, 32);
        mc.getTextureManager().bindTexture(stats.getKakuganResource());
        this.drawTexturedModalRect(this.width / 2 + 5, this.height / 6, 32, 32, 32, 32);

        GuiSlider sliderOffset = (GuiSlider) buttonList.get(0);
        capa.setCameraOffset(sliderOffset.getParameterValue());
        sliderOffset.updateDisplayString();

        if (buttonList.get(0).isMouseOver()) {
            offsetXhelp.drawToast(mouseX, mouseY);
        }
        if (buttonList.get(2).isMouseOver()) {
            copyrightHelp.drawToast(mouseX, mouseY);
        }
        if (buttonList.get(3).isMouseOver()) {
            darkeningHelp.drawToast(mouseX, mouseY);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: {
                stats.setKakuganResource(SkinHandler.changeKakugan(stats.getKakuganResource()));
                break;
            }
            case 2: {
                capa.setCopyrightMode(((BoolGuiButton)button).getValue());
                break;
            }
            case 3: {
                capa.setDarkeningKagune(((BoolGuiButton)button).getValue());
                break;
            }
        }
    }
}
