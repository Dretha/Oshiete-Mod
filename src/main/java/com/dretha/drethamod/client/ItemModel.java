package com.dretha.drethamod.client;

import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ItemModel extends AnimatedGeoModel<KuinkeMeleeBase> {

    private String MODEL = "kakuken";
    private String TEXTURE = "kakuken1";
    private String ANIMATION = "katana";

    public ItemModel() {}

    public ItemModel(String model, String texture, String animation) {
        MODEL = model;
        TEXTURE = texture;
        ANIMATION = animation;
    }

    @Override
    public ResourceLocation getModelLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "geo/kuinkies/" + MODEL + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "textures/geckoitems/" + MODEL + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KuinkeMeleeBase animatable)
    {
        return new ResourceLocation(Reference.MODID, "animations/kuinkies/" + MODEL + ".animation.json");
    }
}
