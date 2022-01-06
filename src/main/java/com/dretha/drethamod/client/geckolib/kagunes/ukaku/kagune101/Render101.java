package com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.INeedAClone;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class Render101 extends GeoEntityRenderer<EntityKagune> implements INeedAClone {
    public Render101(final RenderManager renderManager)
    {
        super(renderManager, (AnimatedGeoModel) new Model101(null));
        this.shadowSize = 0.7F;
    }

	@Override
	public GeoEntityRenderer getClone(RenderManager manager) {
		return new Render101(manager);
	}
}
