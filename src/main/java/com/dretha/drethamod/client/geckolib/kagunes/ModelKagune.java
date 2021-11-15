package com.dretha.drethamod.client.geckolib.kagunes;

import com.dretha.drethamod.reference.Reference;

import net.minecraft.util.ResourceLocation;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKagune extends AnimatedGeoModel<EntityKagune> {
	
	
	private String MODEL_LOCATION;
	private String TEXTURE_LOCATION;
	private String ANIMATION_LOCATION;
	
	
	public ModelKagune(String MODEL_LOCATION, String TEXTURE_LOCATION, String ANIMATION_LOCATION) {
		this.MODEL_LOCATION = MODEL_LOCATION;
		this.TEXTURE_LOCATION = TEXTURE_LOCATION;
		this.ANIMATION_LOCATION = ANIMATION_LOCATION;
	}
	
	
    @Override
    public ResourceLocation getModelLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, "geo/kagune/kagune202.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, "textures/entity/kagune/kagune202.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKagune object)
    {
        return new ResourceLocation(Reference.MODID, "animations/kagune/kagune202.animation.json");
    }
    
}
