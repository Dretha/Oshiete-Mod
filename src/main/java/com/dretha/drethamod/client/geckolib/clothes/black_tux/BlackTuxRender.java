package com.dretha.drethamod.client.geckolib.clothes.black_tux;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BlackTuxRender extends GeoArmorRenderer<BlackTuxArmor>
{
    public BlackTuxRender()
    {
        super(new BlackTuxModel());

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
