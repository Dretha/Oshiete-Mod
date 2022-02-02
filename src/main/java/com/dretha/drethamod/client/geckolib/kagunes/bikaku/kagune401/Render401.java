package com.dretha.drethamod.client.geckolib.kagunes.bikaku.kagune401;

import com.dretha.drethamod.client.geckolib.kagunes.RendererKaguneBase;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Render401 extends RendererKaguneBase
{
    public Render401(final RenderManager renderManager)
    {
        super(renderManager, new Model401(null));
        this.shadowSize = 0.7F;
    }
}
