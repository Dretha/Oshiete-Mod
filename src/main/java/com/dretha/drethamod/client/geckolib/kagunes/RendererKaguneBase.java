package com.dretha.drethamod.client.geckolib.kagunes;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public abstract class RendererKaguneBase extends GeoEntityRenderer<EntityKagune> implements Cloneable{
	
	public GeoEntityRenderer cloneRenderer() throws CloneNotSupportedException {
		return (GeoEntityRenderer) super.clone();
	}
	
	public RendererKaguneBase(final RenderManager renderManager, AnimatedGeoModel model)
    {
        super(renderManager, model);
        this.shadowSize = 0.7F;
    }
}
