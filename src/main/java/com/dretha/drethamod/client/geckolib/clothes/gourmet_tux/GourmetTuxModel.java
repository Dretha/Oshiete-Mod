package com.dretha.drethamod.client.geckolib.clothes.gourmet_tux;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GourmetTuxModel extends AnimatedGeoModel<GourmetTuxArmor>
{
    @Override
    public ResourceLocation getModelLocation(GourmetTuxArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/blue_blouse.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GourmetTuxArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/blue_blouse.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GourmetTuxArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
