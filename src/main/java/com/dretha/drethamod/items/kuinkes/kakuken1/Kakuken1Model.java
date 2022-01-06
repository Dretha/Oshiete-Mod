package com.dretha.drethamod.items.kuinkes.kakuken1;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Kakuken1Model extends AnimatedGeoModel<Kakuken>
{
    @Override
    public ResourceLocation getModelLocation(Kakuken animatable)
    {
        return new ResourceLocation(Reference.MODID, "geo/kuinkies/kakuken.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Kakuken animatable)
    {
        return new ResourceLocation(Reference.MODID, "textures/geckoitems/kakuken1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Kakuken animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/kuinkies/kakuken.animation.json");
    }
}
