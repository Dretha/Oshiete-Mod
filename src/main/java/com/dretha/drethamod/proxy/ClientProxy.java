package com.dretha.drethamod.proxy;

import com.dretha.drethamod.client.geckolib.clothes.InitClothes;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.init.InitBlocks;
import com.dretha.drethamod.init.InitEntities;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.layers.LayersRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	
	
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
    
    //for obj
    public void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
    
    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
    
    
    
    
    //Initialization
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        InitEntities.initModels();
        OBJLoader.INSTANCE.addDomain(Reference.MODID);
        KeybindsRegister.register();


    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        LayersRegister.register();
        //MinecraftForge.EVENT_BUS.register(new KakuganOverlay(Minecraft.getMinecraft()));
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
        
    
}
