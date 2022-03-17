package com.dretha.drethamod.client.gui;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.client.gui.views.GuiSlider;
import com.dretha.drethamod.client.gui.views.GuiTexturedButton;
import com.dretha.drethamod.client.gui.views.HelpToast;
import com.dretha.drethamod.init.InitSounds;
import com.dretha.drethamod.utils.Enumerator;
import com.dretha.drethamod.utils.enums.GhoulType;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.pojo.SimpleColor;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class StartGui extends GuiScreen {

    public ISound opening;
    protected int guiLeft;
    protected int guiTop;
    protected int xSize = 176;
    protected int ySize = 166;
    HelpToast originHelp = new HelpToast(I18n.format("helptoast.origin"));
    HelpToast ukakuHelp = new HelpToast(I18n.format("helptoast.ukaku"));
    HelpToast koukakuHelp = new HelpToast(I18n.format("helptoast.koukaku"));
    HelpToast rinkakuHelp = new HelpToast(I18n.format("helptoast.rinkaku"));
    HelpToast bikakuHelp = new HelpToast(I18n.format("helptoast.bikaku"));
    HelpToast randomColorHelp = new HelpToast(I18n.format("helptoast.randomcolor"));

    protected Origin origin = Origin.HUMAN;
    protected Enumerator modelIdEnumerator = new Enumerator(1, 4);
    protected ColorPattern colorPattern = ColorPattern.NONE;

    PersonStats stats;
    EntityPlayer player;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (buttonList.size()<9) return;
        this.drawBackground(0);
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        Color color = new Color(stats.getRed(), stats.getGreen(), stats.getBlue());
        this.drawGradientRect(sliderRed().x + sliderRed().width + 5, sliderRed().y, sliderRed().x + sliderRed().width + 5 + sliderRed().height, sliderRed().y + sliderRed().height, color.getRGB(), color.getRGB());
        this.drawCenteredString(this.fontRenderer, I18n.format("startgui.title"), this.width / 2, 25, 16777215);
        int i = this.guiLeft;
        int j = this.guiTop;
        int scale = 52;
        drawEntityOnScreen(this.width / 4, buttonList.get(8).y - 16, scale, (float)(i + 51) - mouseX, (float)(j + 75 - 50) - mouseY, this.mc.player);

        if (player.ticksExisted%3==0) {
            GuiSlider sliderRed = (GuiSlider) buttonList.get(4);
            GuiSlider sliderGreen = (GuiSlider) buttonList.get(5);
            GuiSlider sliderBlue = (GuiSlider) buttonList.get(6);
            stats.setRed((int) sliderRed.getParameterValue());
            stats.setGreen((int) sliderGreen.getParameterValue());
            stats.setBlue((int) sliderBlue.getParameterValue());
            sliderRed.updateDisplayString();
            sliderGreen.updateDisplayString();
            sliderBlue.updateDisplayString();
        }

        if (buttonList.get(0).isMouseOver())
            originHelp.drawToast(mouseX, mouseY);
        else if (buttonList.get(1).isMouseOver())
        {
            switch (stats.getGhoulType()) {
                case UKAKU: {
                    ukakuHelp.drawToast(mouseX, mouseY);
                    break;
                }
                case KOUKAKU: {
                    koukakuHelp.drawToast(mouseX, mouseY);
                    break;
                }
                case RINKAKU: {
                    rinkakuHelp.drawToast(mouseX, mouseY);
                    break;
                }
                case BIKAKU: {
                    bikakuHelp.drawToast(mouseX, mouseY);
                    break;
                }
            }
        }
        else if (buttonList.get(7).isMouseOver())
            randomColorHelp.drawToast(mouseX, mouseY);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch (button.id) {
            case 0: {
                origin = origin.change();
                button.displayString = origin.description;

                if (origin==Origin.HUMAN) {
                    stats.becomeHuman(player);
                    updateButtonGhoulType();
                    buttonList.get(2).displayString = I18n.format("startgui.buttonmodel") + stats.getGhoulType().description;
                }
                else {
                    stats.becomeGhoul(stats.getGhoulType().change(), player);
                    stats.releaseKagune(player);
                    updateButtonGhoulType();
                    updateButtonModel();
                }

                break;
            }
            case 1: {
                if (origin!=Origin.HUMAN) {
                    GhoulType ghoulType = stats.getGhoulType().change();
                    if (ghoulType==GhoulType.NONE)
                        ghoulType = ghoulType.change();
                    stats.becomeGhoul(ghoulType, player);
                    stats.releaseKagune(player);
                }
                updateButtonGhoulType();
                break;
            }
            case 2: {
                if (origin!=Origin.HUMAN) {
                    modelIdEnumerator.forward();
                    updateButtonModel();
                }
                break;
            }
            case 3: {
                colorPattern = colorPattern.change();
                updateButtonColorPattern();
                if (colorPattern!=ColorPattern.NONE) {
                    sliderRed().setCurrentValue(colorPattern.color.red);
                    sliderGreen().setCurrentValue(colorPattern.color.green);
                    sliderBlue().setCurrentValue(colorPattern.color.blue);
                }
                break;
            }
            case 7: {
                SimpleColor simpleColor = SimpleColor.randomColor();
                sliderRed().setCurrentValue(simpleColor.red);
                sliderGreen().setCurrentValue(simpleColor.green);
                sliderBlue().setCurrentValue(simpleColor.blue);
                break;
            }
            case 8: {
                closeGui();
                break;
            }
        }

        if (button instanceof GuiSlider) {
            colorPattern = ColorPattern.NONE;
            updateButtonColorPattern();
        }
    }

    private GuiSlider sliderRed() {
        return (GuiSlider) buttonList.get(4);
    }
    private GuiSlider sliderGreen() {
        return (GuiSlider) buttonList.get(5);
    }
    private GuiSlider sliderBlue() {
        return (GuiSlider) buttonList.get(6);
    }

    @Override
    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        player = EventsHandler.getPlayerMP(Minecraft.getMinecraft().player);
        stats = player.getCapability(CapaProvider.PLAYER_CAP, null).personStats();

        if (stats.isGhoul())
            origin=Origin.GHOUL;
        else
            origin=Origin.HUMAN;

        buttonList.add(new GuiButton(0, this.width / 2 + 5, this.height / 6 + 24, origin.description));
        buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 6 + 24*2, I18n.format("startgui.buttontype") + stats.getGhoulType().description));
        buttonList.add(new GuiButton(2, this.width / 2 + 5, this.height / 6 + 24*3, I18n.format("startgui.buttonmodel") + modelIdEnumerator.number()));
        buttonList.add(new GuiButton(3, this.width / 2 + 5, this.height / 6 + 24*4, ""));
        buttonList.add(new GuiSlider(4, this.width / 2 + 5, this.height / 6 + 24*5, I18n.format("startgui.colorred"), false, 0.0F, 255.0F, 1.0F, stats.getRed()));
        buttonList.add(new GuiSlider(5, this.width / 2 + 5, this.height / 6 + 24*6, I18n.format("startgui.colorgreen"), false, 0.0F, 255.0F, 1.0F, stats.getGreen()));
        buttonList.add(new GuiSlider(6, this.width / 2 + 5, this.height / 6 + 24*7, I18n.format("startgui.colorblue"), false, 0.0F, 255.0F, 1.0F, stats.getBlue()));
        buttonList.add(new GuiTexturedButton(7, sliderRed().x + sliderRed().width + 30, sliderRed().y, 0, 0, 20));
        buttonList.add(new GuiButton(8, this.width / 4 - 120/2, sliderBlue().y, 120, 20,I18n.format("startgui.start") + "!"));

        if (!player.isCreative())
            player.setEntityInvulnerable(true);

        if (player.getCapability(CapaProvider.PLAYER_CAP, null).isCopyrightMode())
            opening = PositionedSoundRecord.getMasterRecord(InitSounds.opening_piano, 1.0F);
        else
            opening = PositionedSoundRecord.getMasterRecord(InitSounds.opening, 1.0F);
        soundHandler().stopSounds();
        soundHandler().playSound(opening);

        buttonList.get(0).displayString = origin.description;
        updateButtonGhoulType();
        updateButtonColorPattern();

        sliderRed().setCurrentValue(stats.getRed());
        sliderGreen().setCurrentValue(stats.getGreen());
        sliderBlue().setCurrentValue(stats.getBlue());
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        player.setEntityInvulnerable(false);

        if (soundHandler().isSoundPlaying(opening))
            soundHandler().stopSound(opening);

        if (stats.isKaguneActive() && !player.isCreative())
            stats.admitKagune(player);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void updateButtonGhoulType() {
        buttonList.get(1).displayString = I18n.format("startgui.buttontype") + stats.getGhoulType().description;
    }

    private void updateButtonModel() {
        buttonList.get(2).displayString = I18n.format("startgui.buttonmodel") + modelIdEnumerator.number();
    }

    private void updateButtonColorPattern() {
        buttonList.get(3).displayString = I18n.format("colorpattern") + ": " + colorPattern.name;
    }

    protected enum Origin {
        GHOUL(I18n.format("startgui.origin.ghoul")),
        HUMAN(I18n.format("startgui.origin.human"));

        public final String description;

        Origin(String description) {
            this.description = description;
        }

        public Origin change() {
            int index = this.ordinal();
            if (index+1>=Origin.values().length)
                index=-1;
            return Arrays.asList(Origin.values()).get(index+1);
        }
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 200.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan(mouseX / 40.0F) * 20.0F;
        ent.rotationYaw = (float)Math.atan(mouseX / 40.0F) * 40.0F;
        ent.rotationPitch = -((float)Math.atan(mouseY / 40.0F)) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public enum ColorPattern {
        NONE(GhoulType.NONE.description, new SimpleColor(255, 255, 255)),
        KANEKI(I18n.format("colorpattern.kaneki"), new SimpleColor(191, 0, 0)),
        NAKI(I18n.format("colorpattern.naki"), new SimpleColor(19, 162, 155)),
        NISHIKI(I18n.format("colorpattern.nishiki"), new SimpleColor(0, 223, 232)),


        ;

        public final String name;
        public final SimpleColor color;

        ColorPattern(String name, SimpleColor color) {
            this.name = name;
            this.color = color;
        }

        public ColorPattern change() {
            ColorPattern result;
            try {
                result = ColorPattern.values()[this.ordinal() + 1];
            } catch (IndexOutOfBoundsException e) {
                result = ColorPattern.values()[0];
            }
            return result;
        }
    }

    private void closeGui() {
        this.mc.displayGuiScreen(null);

        if (this.mc.currentScreen == null)
        {
            this.mc.setIngameFocus();
        }
    }

    public static SoundHandler soundHandler() {
        return Minecraft.getMinecraft().getSoundHandler();
    }
}
