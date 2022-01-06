package com.dretha.drethamod.client.geckolib.clothes.grandpa_hat;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GrandpaHatRender extends GeoArmorRenderer<GrandpaHatArmor>
{
    public GrandpaHatRender()
    {
        super(new GrandpaHatModel());

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
