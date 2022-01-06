package com.dretha.drethamod.client.geckolib.kagunes.ukaku.kagune101;

import com.dretha.drethamod.client.geckolib.kagunes.EntityKagune;
import com.dretha.drethamod.client.geckolib.kagunes.ModelKaguneBase;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class Model101 extends ModelKaguneBase{
	
	private String MODEL_LOCATION = "geo/kagune/kagune101.geo.json";
	private String TEXTURE_LOCATION = "textures/entity/kagune/kagune10101.png";
	private String ANIMATION_LOCATION = "animations/kagune/kagune101.animation.json";
	
	public Model101(String TEXTURE_LOCATION) {
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
