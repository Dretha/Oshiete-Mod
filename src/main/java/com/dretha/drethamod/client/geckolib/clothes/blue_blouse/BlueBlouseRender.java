package com.dretha.drethamod.client.geckolib.clothes.blue_blouse;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BlueBlouseRender extends GeoArmorRenderer<BlueBlouseArmor>
{
    public BlueBlouseRender()
    {
        super(new BlueBlouseModel());

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
