package com.dretha.drethamod.client.geckolib.clothes.blue_blouse;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlueBlouseModel extends AnimatedGeoModel<BlueBlouseArmor>
{
    @Override
    public ResourceLocation getModelLocation(BlueBlouseArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/blue_blouse.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlueBlouseArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/blue_blouse.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlueBlouseArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
