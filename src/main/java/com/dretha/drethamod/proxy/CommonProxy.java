package com.dretha.drethamod.proxy;


import com.dretha.drethamod.capability.InitCapabilities;
import com.dretha.drethamod.client.gui.GuiHandler;
import com.dretha.drethamod.init.InitBlocks;
import com.dretha.drethamod.init.InitEntities;
import com.dretha.drethamod.main.Oshiete;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.handlers.GhoulAbilityEventsHandler;
import com.dretha.drethamod.utils.handlers.KeyEventsHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void preInit(FMLPreInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		MinecraftForge.EVENT_BUS.register(new GhoulAbilityEventsHandler());
		MinecraftForge.EVENT_BUS.register(new KeyEventsHandler());
		InitCapabilities.init();
		InitEntities.init();

		InitBlocks.init();
    }

    public void init(FMLInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(Oshiete.instance, new GuiHandler());
		InitBlocks.initRender();
		//NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler1());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
