package com.dretha.drethamod.items.firearm.assault_rifle_hk33;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HK33Model extends AnimatedGeoModel<HK33Item>
{
    @Override
    public ResourceLocation getModelLocation(HK33Item animatable)
    {
        return new ResourceLocation(Reference.MODID, "geo/assault_rifle_hk33.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HK33Item animatable)
    {
        return new ResourceLocation(Reference.MODID, "textures/geckoitems/assault_rifle_hk33.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HK33Item animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/assault_rifle_hk33.animation.json");
    }
}
