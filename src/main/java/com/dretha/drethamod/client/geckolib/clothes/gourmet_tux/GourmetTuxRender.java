package com.dretha.drethamod.client.geckolib.clothes.gourmet_tux;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GourmetTuxRender extends GeoArmorRenderer<GourmetTuxArmor>
{
    public GourmetTuxRender()
    {
        super(new GourmetTuxModel());

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

