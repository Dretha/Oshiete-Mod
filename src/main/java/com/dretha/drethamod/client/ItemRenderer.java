package com.dretha.drethamod.client;

import com.dretha.drethamod.items.kuinkes.KuinkeMeleeBase;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ItemRenderer extends GeoItemRenderer<KuinkeMeleeBase>
{
    public ItemRenderer()
    {
        super(new ItemModel());
    }
    public ItemRenderer(String model, String texture, String animation)
    {
        super(new ItemModel(model, texture, animation));
    }
    public ItemRenderer(String name)
    {
        super(new ItemModel(name, name, name));
    }
}

