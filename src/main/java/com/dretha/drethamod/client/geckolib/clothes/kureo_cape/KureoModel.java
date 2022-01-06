package com.dretha.drethamod.client.geckolib.clothes.kureo_cape;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KureoModel extends AnimatedGeoModel<KureoArmor>
{
    @Override
    public ResourceLocation getModelLocation(KureoArmor object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/kureo_cape.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KureoArmor object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/kureo_cape.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KureoArmor animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
