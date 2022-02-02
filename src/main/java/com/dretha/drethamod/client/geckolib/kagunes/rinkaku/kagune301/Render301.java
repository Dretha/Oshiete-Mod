package com.dretha.drethamod.client.geckolib.kagunes.rinkaku.kagune301;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.INeedAClone;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class Render301 extends GeoEntityRenderer<EntityKagune> implements INeedAClone {
    public Render301(final RenderManager renderManager)
    {
        super(renderManager, new Model301(null));
        this.shadowSize = 0.7F;
    }

	@Override
	public GeoEntityRenderer getClone(RenderManager manager) {
		return new Render301(manager);
	}
}
