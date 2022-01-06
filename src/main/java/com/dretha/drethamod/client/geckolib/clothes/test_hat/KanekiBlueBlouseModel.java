package com.dretha.drethamod.client.geckolib.clothes.test_hat;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KanekiBlueBlouseModel extends AnimatedGeoModel<ClothesArmor>
{
	@Override
	public ResourceLocation getModelLocation(ClothesArmor object)
	{
		return new ResourceLocation(Reference.MODID, "geo/clothes/armortest.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ClothesArmor object)
	{
		return new ResourceLocation(Reference.MODID, "textures/clothes/armortest.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(ClothesArmor animatable)
	{
		return new ResourceLocation(Reference.MODID, "animations/clothes/armortest.animation.json");
	}
}

