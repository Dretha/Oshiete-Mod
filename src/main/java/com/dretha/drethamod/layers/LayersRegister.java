package com.dretha.drethamod.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayersRegister {
	public static void register()
    {
		setLayer(new LayerKakugan());
        setLayer(new LayerFlame());
        setLayer(new LayerShard());
        setLayer(new LayerKagune());

        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")));
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")));
    }

    private static void setLayer(final LayerRenderer layer)
    {
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(layer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(layer);
    }
}
