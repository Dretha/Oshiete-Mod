package com.dretha.drethamod.layers;

import com.dretha.drethamod.layers.kagune.LayerKagune;
import com.dretha.drethamod.layers.kagune.LayerFlame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayersRegister {
	public static void register()
    {
		setLayer(new LayerKakugan());
        setLayer(new LayerFlame());
        setLayer(new LayerShard());
        setLayer(new LayerKagune());
        setLayer(new LayerMask());

        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")));
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(new LayerClothes(Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")));
    }

    private static void setLayer(final LayerRenderer layer)
    {
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(layer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(layer);
    }
}
