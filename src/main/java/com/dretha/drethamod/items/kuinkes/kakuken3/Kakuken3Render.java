package com.dretha.drethamod.items.kuinkes.kakuken3;

import com.dretha.drethamod.items.kuinkes.kakuken1.Kakuken;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Kakuken3Render extends GeoItemRenderer<Kakuken>
{
    public Kakuken3Render()
    {
        super(new Kakuken3Model());
    }
}
