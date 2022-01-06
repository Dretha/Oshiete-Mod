package com.dretha.drethamod.init;

import com.dretha.drethamod.items.firearm.assault_rifle_hk33.HK33Render;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.kuinkes.kakuken1.Kakuken1Render;
import com.dretha.drethamod.items.kuinkes.kakuken3.Kakuken3Render;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class InitCustomRenderers {
    public static void init()
    {
        //HK33
        ModelLoader.setCustomModelResourceLocation(InitItems.HK33, 0, new ModelResourceLocation(Reference.MODID + ":assault_rifle_hk33.item", "inventory"));
        InitItems.HK33.setTileEntityItemStackRenderer(new HK33Render());

        //kakuken1
        ModelLoader.setCustomModelResourceLocation(InitItems.KAKUKEN1, 0, new ModelResourceLocation(Reference.MODID + ":kakuken.item", "inventory"));
        InitItems.KAKUKEN1.setTileEntityItemStackRenderer(new Kakuken1Render());

        //kakuken3
        ModelLoader.setCustomModelResourceLocation(InitItems.KAKUKEN3, 0, new ModelResourceLocation(Reference.MODID + ":kakuken.item", "inventory"));
        InitItems.KAKUKEN3.setTileEntityItemStackRenderer(new Kakuken3Render());

    }
}
