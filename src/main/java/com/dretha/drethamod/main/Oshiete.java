package com.dretha.drethamod.main;

import com.dretha.drethamod.client.geckolib.clothes.InitClothes;
import com.dretha.drethamod.entity.human.SkinHandler;
import com.dretha.drethamod.init.InitCraftings;
import com.dretha.drethamod.proxy.CommonProxy;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.server.*;
import com.dretha.drethamod.utils.handlers.EventsHandler;
import com.dretha.drethamod.utils.handlers.GuiEventsHandler;
import com.dretha.drethamod.utils.handlers.KeyEventsHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import software.bernie.geckolib3.GeckoLib;

import java.util.Random;


@Mod(modid=Reference.MODID, name=Reference.NAME, version = Reference.VERSION, 
acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSION)
public class Oshiete {
	//TODO ?????? ?????? https://forum.mcmodding.ru/resources/annotacii-konfiguracii.163/
	public static final String ID = "dm";
	public static int idpacket = 0;
	public static SimpleNetworkWrapper NETWORK;
	public static final Random random = new Random();
	
	@Instance
	public static Oshiete instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		MinecraftForge.EVENT_BUS.register(new GuiEventsHandler(Minecraft.getMinecraft()));
	    proxy.preInit(event);
	    GeckoLib.initialize();
	    SkinHandler.init();
		InitClothes.init();
		InitCraftings.init();

		//server
	    NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("Channel_0001");
	    NETWORK.registerMessage(KaguneImpactMessage.Handler.class, KaguneImpactMessage.class, idpacket++, Side.SERVER);
	    NETWORK.registerMessage(KillPlayerMessage.Handler.class, KillPlayerMessage.class, idpacket++, Side.SERVER);
	    NETWORK.registerMessage(SniffMessage.Handler.class, SniffMessage.class, idpacket++, Side.SERVER);
	    NETWORK.registerMessage(OpenClothesInventoryMessage.Handler.class, OpenClothesInventoryMessage.class, idpacket++, Side.SERVER);
		NETWORK.registerMessage(DoSomeEntityMessage.Handler.class, DoSomeEntityMessage.class, idpacket++, Side.SERVER);
		NETWORK.registerMessage(DropMessage.Handler.class, DropMessage.class, idpacket++, Side.SERVER);
		NETWORK.registerMessage(ForceThrustMessage.Handler.class, ForceThrustMessage.class, idpacket++, Side.SERVER);
		NETWORK.registerMessage(ServerGhoulPoisonedMessage.Handler.class, ServerGhoulPoisonedMessage.class, idpacket++, Side.SERVER);

		//client
		NETWORK.registerMessage(ReloadMessage.Handler.class, ReloadMessage.class, idpacket++, Side.CLIENT);
		NETWORK.registerMessage(ClientGhoulPoisonedMessage.Handler.class, ClientGhoulPoisonedMessage.class, idpacket++, Side.CLIENT);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    proxy.init(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	    proxy.postInit(event);
	}

/*
	@SideOnly(Side.CLIENT)
	@Mod.EventHandler
	public void registerRenderers(FMLPreInitializationEvent event)
	{
		InitClothes.init();
	}*/

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {

		proxy.serverStarting(event);
	}

}
