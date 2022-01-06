package com.dretha.drethamod.items.kuinkes.kakuken3;

import com.dretha.drethamod.items.kuinkes.kakuken1.Kakuken;
import com.dretha.drethamod.items.kuinkes.kakuken1.Kakuken1Model;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class Kakuken3Model extends Kakuken1Model {
    @Override
    public ResourceLocation getTextureLocation(Kakuken animatable)
    {
        return new ResourceLocation(Reference.MODID, "textures/geckoitems/kakuken_sasaki.png");
    }
}
