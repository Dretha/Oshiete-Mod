package com.dretha.drethamod.proxy;


import java.util.Arrays;

import com.dretha.drethamod.capability.CapaHandler;
import com.dretha.drethamod.capability.CapaStorage;
import com.dretha.drethamod.capability.ICapaHandler;
import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.entity.registry.InitEntities;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.handlers.GhoulAbilityEventsHandler;
import com.dretha.drethamod.utils.handlers.KaguneEventsHandler;
import com.dretha.drethamod.utils.handlers.KeyEventsHandler;
import com.dretha.drethamod.utils.handlers.RegisterHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void preInit(FMLPreInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		MinecraftForge.EVENT_BUS.register(new KaguneEventsHandler());
		MinecraftForge.EVENT_BUS.register(new GhoulAbilityEventsHandler());
		MinecraftForge.EVENT_BUS.register(new KeyEventsHandler());
		CapabilityManager.INSTANCE.register(ICapaHandler.class, new CapaStorage(), CapaHandler.class);
		InitEntities.init();
    }

    public void init(FMLInitializationEvent event)
    {
  
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
