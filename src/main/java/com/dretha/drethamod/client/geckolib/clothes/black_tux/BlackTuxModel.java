package com.dretha.drethamod.client.geckolib.clothes.black_tux;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlackTuxModel extends AnimatedGeoModel<BlackTuxArmor>
{
    @Override
    public ResourceLocation getModelLocation(BlackTuxArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/black_tux.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlackTuxArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/black_tux.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlackTuxArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
