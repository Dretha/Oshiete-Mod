package com.dretha.drethamod.client.geckolib.kagunes;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public interface INeedAClone {
	public GeoEntityRenderer getClone(RenderManager manager);
}
