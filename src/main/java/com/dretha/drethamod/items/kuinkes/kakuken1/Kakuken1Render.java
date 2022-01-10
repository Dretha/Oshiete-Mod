package com.dretha.drethamod.items.kuinkes.kakuken1;

import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Kakuken1Render extends GeoItemRenderer<KuinkeMeleeBase>
{
    public Kakuken1Render()
    {
        super(new Kakuken1Model());
    }
}
