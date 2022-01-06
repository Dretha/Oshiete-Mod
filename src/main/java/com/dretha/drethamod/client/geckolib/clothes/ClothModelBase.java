package com.dretha.drethamod.client.geckolib.clothes;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClothModelBase<T extends IAnimatable> extends AnimatedGeoModel<T>
{
    private T t;
    private String MODEL_LOCATION;
    private String TEXTURE_LOCATION;
    private String ANIMATION_LOCATION;

    public ClothModelBase() {

    }


    @Override
    public ResourceLocation getModelLocation(T object)
    {
        return new ResourceLocation(Reference.MODID, "geo/clothes/blue_blouse.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object)
    {
        return new ResourceLocation(Reference.MODID, "textures/clothes/blue_blouse.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
    }
}
