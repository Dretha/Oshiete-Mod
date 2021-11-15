package com.dretha.drethamod.proxy;

import org.lwjgl.opengl.GL11;

import com.dretha.drethamod.client.gui.KakuganOverlay;
import com.dretha.drethamod.client.keybinds.KeybindsRegister;
import com.dretha.drethamod.entity.projectile.EntityRCShard;
import com.dretha.drethamod.entity.registry.InitEntities;
import com.dretha.drethamod.entity.render.RenderRCShard;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.handlers.EventsHandler;

import layers.LayersRegister;
import layers.kagune.LayerUkaku;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
