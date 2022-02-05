package com.dretha.drethamod.client.geckolib.kagunes;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderKagune extends GeoEntityRenderer<EntityKagune> {
    public RenderKagune(final RenderManager renderManager, AnimatedGeoModel model)
    {
        super(renderManager, model);
        this.shadowSize = 0.7F;
    }
}
