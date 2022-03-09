package com.dretha.drethamod.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

import java.util.List;

public class LayersRegister {
	public static void register()
    {
        List<LayerRenderer<AbstractClientPlayer>> layerRenderersDefault = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").layerRenderers;
        List<LayerRenderer<AbstractClientPlayer>> layerRenderersSlim = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").layerRenderers;
        LayerRenderer<AbstractClientPlayer> layerHeldItemDefault = null;
        LayerRenderer<AbstractClientPlayer> layerHeldItemSlim = null;

        for (LayerRenderer layerRenderer : layerRenderersDefault)
            if (layerRenderer instanceof LayerHeldItem) {
                layerRenderersDefault.remove(layerRenderer);
                layerHeldItemDefault = layerRenderer;
                break;
            }
        for (LayerRenderer layerRenderer : layerRenderersSlim)
            if (layerRenderer instanceof LayerHeldItem) {
                layerRenderersSlim.remove(layerRenderer);
                layerHeldItemSlim = layerRenderer;
                break;
            }
        
		setLayer(new LayerKakugan());
        setLayer(new LayerShard());
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")));
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")));
        
        layerRenderersDefault.add(layerHeldItemDefault);
        layerRenderersSlim.add(layerHeldItemSlim);
        setLayer(new LayerKagune());
    }

    private static void setLayer(final LayerRenderer layer)
    {
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(layer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(layer);
    }
}
