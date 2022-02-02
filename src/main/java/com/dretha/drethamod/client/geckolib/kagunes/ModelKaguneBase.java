package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKaguneBase extends AnimatedGeoModel<EntityKagune>{

    protected final String MODEL_LOCATION = "geo/kagune/kagune301.geo.json";
    protected String TEXTURE_LOCATION = "textures/entity/kagune/kagune30101.png";
    protected final String ANIMATION_LOCATION = "animations/kagune/kagune301.animation.json";

    public ModelKaguneBase(String TEXTURE_LOCATION) {
        if (TEXTURE_LOCATION!=null)
            this.TEXTURE_LOCATION = TEXTURE_LOCATION;
    }

    @Override
    public ResourceLocation getModelLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, this.MODEL_LOCATION);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, this.TEXTURE_LOCATION);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, this.ANIMATION_LOCATION);
    }
    

}
