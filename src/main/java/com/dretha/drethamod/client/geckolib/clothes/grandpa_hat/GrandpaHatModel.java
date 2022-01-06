package com.dretha.drethamod.client.geckolib.clothes.grandpa_hat;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrandpaHatModel extends AnimatedGeoModel<GrandpaHatArmor>
{
    @Override
    public ResourceLocation getModelLocation(GrandpaHatArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/grandpa_hat.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrandpaHatArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/grandpa_hat.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrandpaHatArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/grandpa_hat.animation.json");
    }
}
