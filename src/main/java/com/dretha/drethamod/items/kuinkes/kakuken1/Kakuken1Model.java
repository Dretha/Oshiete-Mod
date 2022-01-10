package com.dretha.drethamod.items.kuinkes.kakuken1;

import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Kakuken1Model extends AnimatedGeoModel<KuinkeMeleeBase>
{
    @Override
    public ResourceLocation getModelLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "geo/kuinkies/kakuken.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "textures/geckoitems/kakuken1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/kuinkies/katana.animation.json");
    }
}
