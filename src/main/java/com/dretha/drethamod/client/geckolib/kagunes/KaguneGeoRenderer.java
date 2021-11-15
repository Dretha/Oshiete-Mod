package com.dretha.drethamod.client.geckolib.kagunes;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.example.client.model.entity.ExampleEntityModel;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KaguneGeoRenderer extends GeoEntityRenderer<GeoExampleEntity>
{
	
    public KaguneGeoRenderer(final RenderManager renderManager, String MODEL_LOCATION, String TEXTURE_LOCATION, String ANIMATION_LOCATION)
    {
        super(renderManager, (AnimatedGeoModel)new ModelKagune(MODEL_LOCATION, TEXTURE_LOCATION, ANIMATION_LOCATION));
        this.shadowSize = 0.7F; //change 0.7 to the desired shadow size.
    }
}
