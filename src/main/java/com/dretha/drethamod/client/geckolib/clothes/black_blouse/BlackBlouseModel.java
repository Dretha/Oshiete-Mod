package com.dretha.drethamod.client.geckolib.clothes.black_blouse;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlackBlouseModel extends AnimatedGeoModel<BlackBlouseArmor>
{
    @Override
    public ResourceLocation getModelLocation(BlackBlouseArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/black_tux.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlackBlouseArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/black_tux.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlackBlouseArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
