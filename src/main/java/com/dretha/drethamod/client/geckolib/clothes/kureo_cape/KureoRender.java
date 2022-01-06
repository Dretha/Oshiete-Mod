package com.dretha.drethamod.client.geckolib.clothes.kureo_cape;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class KureoRender extends GeoArmorRenderer<KureoArmor>
{
    public KureoRender()
    {
        super(new KureoModel());

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

