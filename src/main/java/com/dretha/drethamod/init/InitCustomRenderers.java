package com.dretha.drethamod.init;

import com.dretha.drethamod.items.firearm.assault_rifle_hk33.HK33Render;
import com.dretha.drethamod.client.ItemRenderer;
import com.dretha.drethamod.items.kuinkes.kakuken1.Kakuken1Render;
import com.dretha.drethamod.items.kuinkes.kakuken3.Kakuken3Render;
import com.dretha.drethamod.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class InitCustomRenderers {
    public static void init()
    {
        //HK33
        ModelLoader.setCustomModelResourceLocation(InitItems.HK33, 0, new ModelResourceLocation(Reference.MODID + ":assault_rifle_hk33.item", "inventory"));
        InitItems.HK33.setTileEntityItemStackRenderer(new HK33Render());

        //kakuken1
        ModelLoader.setCustomModelResourceLocation(InitItems.KATANA, 0, new ModelResourceLocation(Reference.MODID + ":kakuken.item", "inventory"));
        InitItems.KATANA.setTileEntityItemStackRenderer(new Kakuken1Render());

        //kakuken3
        ModelLoader.setCustomModelResourceLocation(InitItems.KAKUKEN_SASAKI, 0, new ModelResourceLocation(Reference.MODID + ":kakuken.item", "inventory"));
        InitItems.KAKUKEN_SASAKI.setTileEntityItemStackRenderer(new Kakuken3Render());

        registerItemRender("knife", InitItems.KNIFE);
        registerItemRender("cleaver", InitItems.CLEAVER);
        registerItemRender("scythe", InitItems.SCYTHE);
    }

    private static void registerItemRender(String name, Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":"+ name + ".item", "inventory"));
        item.setTileEntityItemStackRenderer(new ItemRenderer(name));
    }
}
