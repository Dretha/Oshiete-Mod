package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKagune extends AnimatedGeoModel<EntityKagune>{

    protected String MODEL_LOCATION = "geo/kagune/kagune3012.geo.json";
    protected String TEXTURE_LOCATION = "textures/entity/kagune/kagune301011.png";
    protected String ANIMATION_LOCATION = "animations/kagune/kagune3012.animation.json";

    public ModelKagune(String MODEL_LOCATION, String TEXTURE_LOCATION, String ANIMATION_LOCATION) {
        this.MODEL_LOCATION = MODEL_LOCATION;
        this.TEXTURE_LOCATION = TEXTURE_LOCATION;
        this.ANIMATION_LOCATION = ANIMATION_LOCATION;
    }
    public ModelKagune(String TEXTURE_LOCATION) {
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
