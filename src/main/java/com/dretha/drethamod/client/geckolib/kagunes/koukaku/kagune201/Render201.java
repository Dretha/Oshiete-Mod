package com.dretha.drethamod.client.geckolib.kagunes.koukaku.kagune201;

import com.dretha.drethamod.client.geckolib.kagunes.RendererKaguneBase;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Render201 extends RendererKaguneBase
{
    public Render201(final RenderManager renderManager)
    {
        super(renderManager, (AnimatedGeoModel) new Model201(null));
        this.shadowSize = 0.7F;
    }
}
