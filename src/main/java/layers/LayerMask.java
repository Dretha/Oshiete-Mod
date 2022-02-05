package com.dretha.drethamod.layers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LayerMask implements LayerRenderer<EntityPlayer> {

    @Override
    public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        ICapaHandler capa = EventsHandler.getCapaMP(player);

        Item mask = capa.getInventory().getStackInSlot(0).getItem();

        if (mask != Items.AIR) {

            RenderPlayer renderPlayer = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(((AbstractClientPlayer)player).getSkinType());
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            renderPlayer.getMainModel().bipedHead.postRender(0.0625F);

            GlStateManager.translate(0, (player.isSneaking() ? 0.2F : 0.0F) + 0.1875F, -0.045F);
            GlStateManager.rotate(180, 0, 0, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(mask), player, ItemCameraTransforms.TransformType.HEAD, true);

            GlStateManager.popMatrix();

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
