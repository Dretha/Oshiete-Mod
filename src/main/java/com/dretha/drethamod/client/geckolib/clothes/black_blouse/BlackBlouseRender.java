package com.dretha.drethamod.client.geckolib.clothes.black_blouse;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BlackBlouseRender extends GeoArmorRenderer<BlackBlouseArmor>
{
    public BlackBlouseRender()
    {
        super(new BlackBlouseModel());

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
