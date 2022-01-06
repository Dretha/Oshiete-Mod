package com.dretha.drethamod.client.geckolib.clothes.test_hat;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class KanekiBlueBlouseRender extends GeoArmorRenderer<ClothesArmor>
{
	public KanekiBlueBlouseRender()
	{
		super(new KanekiBlueBlouseModel());

		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorRightLeg";
		this.leftLegBone = "armorLeftLeg";
		this.rightBootBone = "armorRightBoot";
		this.leftBootBone = "armorLeftBoot";
	}
}
