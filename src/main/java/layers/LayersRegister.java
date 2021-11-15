package layers;

import layers.kagune.LayerKoukaku;
import layers.kagune.LayerUkaku;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class LayersRegister {
	public static void register()
    {
		setLayer(new LayerKakugan());
        setLayer(new LayerUkaku());
        setLayer(new LayerShard());
        setLayer(new LayerKoukaku());
    }

    private static void setLayer(final LayerRenderer layer)
    {
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(layer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(layer);
    }
}
