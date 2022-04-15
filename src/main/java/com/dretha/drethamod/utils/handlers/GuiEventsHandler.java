package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.client.gui.GuiOshieteOptions;
import com.dretha.drethamod.client.gui.StartGui;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.DrethaMath;
import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(
        value = { Side.CLIENT, Side.SERVER },
        modid = Reference.MODID)
public class GuiEventsHandler extends GuiIngame
{
    public static final ResourceLocation ICONS_OSHIETE = new ResourceLocation(Reference.MODID ,"textures/gui/icons.png");

    public GuiEventsHandler(Minecraft mcIn) {
        super(mcIn);
    }

    // TODO сделать оверлей какуган
/*
    @SubscribeEvent
    public void onRenderKakuganOverlay(RenderGameOverlayEvent.Pre e)
    {
        PersonStats stats = PersonStats.getStats(mc.player);

        if (stats.isKakuganActive())
        {
            ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());

//            GlStateManager.enableAlpha();
//            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            mc.getTextureManager().bindTexture(PUMPKIN_BLUR_TEX_PATH);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(0.0D, scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
            bufferbuilder.pos(scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
            bufferbuilder.pos(scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
            bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
            tessellator.draw();

//            GlStateManager.disableAlpha();
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableAlpha();
        }
    }
*/
    //RC Overlay
    @SubscribeEvent
    public void onRenderRCOverlay(RenderGameOverlayEvent.Post e)
    {
        PersonStats stats = PersonStats.getStats(mc.player);

        if (stats.isGhoul() && !mc.player.isCreative()) {
            int textureY = stats.getRClevel() > stats.maxRClevel() * 0.2F && mc.player.getFoodStats().getFoodLevel()>6 ? 0 : 14;
            int x = e.getResolution().getScaledWidth() / 2 + 10;
            int y = e.getResolution().getScaledHeight() - 48;
            if (mc.player.isInsideOfMaterial(Material.WATER))
                y-=10;
            GlStateManager.enableAlpha();
            mc.getTextureManager().bindTexture(ICONS_OSHIETE);
            int barWidth = 81;
            int barHeight = 7;
            this.drawTexturedModalRect(x, y, 0, textureY, barWidth, barHeight);

            int barDynamicWidth = (int) DrethaMath.getNumberOfInterval(0, stats.maxRClevel(), 0, barWidth, stats.getRClevel());
            this.drawTexturedModalRect(x, y, 0, textureY+7, barDynamicWidth, barHeight);
            this.getFontRenderer().drawString(stats.getRClevel()+"", x + barWidth + 1, y-1, 9668012, true);

            GlStateManager.color(1, 1, 1, 1);
            this.mc.getTextureManager().bindTexture(ICONS);
        }
    }

    //Buttons on Gui Options in game
    @SubscribeEvent
    public void onInitCustomButtonForGuiOptions(GuiScreenEvent.InitGuiEvent.Post e)
    {
        int buttonWidth = 100;
        int buttonHeight = 20;
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (e.getGui() instanceof GuiOptions && player!=null && player.world!=null && player.isCreative())
        {
            e.getButtonList().add(new GuiButton(1489, e.getGui().width-buttonWidth-5, e.getGui().height-buttonHeight-5-5-buttonHeight, buttonWidth, buttonHeight, I18n.format("oshieteoptions.editorbutton.name")));
        }
        if (e.getGui() instanceof GuiOptions && player!=null && player.world!=null)
        {
            e.getButtonList().add(new GuiButton(1488, e.getGui().width-buttonWidth-5, e.getGui().height-buttonHeight-5, buttonWidth, buttonHeight, I18n.format("oshieteoptions.mainbutton.name") + "..."));
        }
    }
    @SubscribeEvent
    public void actionPerformedButtonForGuiOptions(GuiScreenEvent.ActionPerformedEvent.Post e)
    {
        if (e.getGui() instanceof GuiOptions && Minecraft.getMinecraft().player!=null && Minecraft.getMinecraft().player.world!=null)
        {
            if (e.getButton().id == 1488) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiOshieteOptions());
            }
            if (e.getButton().id == 1489) {
                Minecraft.getMinecraft().displayGuiScreen(new StartGui());
            }
        }
    }
}
