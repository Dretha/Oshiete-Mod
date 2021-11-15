package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.capability.CapaProvider;
import com.dretha.drethamod.entity.passive.EntityHuman;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.reference.Reference;
import com.dretha.drethamod.utils.interfaces.IHasModel;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegisterHandler {
	@SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));
    }
	
	@SubscribeEvent
    public static void onRegistryModel(ModelRegistryEvent event) {
		for (Item item: InitItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
    }
	
	//@SubscribeEvent
   // public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
   //     System.out.println("AttachCapabilitiesEvent");
   //     if (event.getObject() instanceof EntityPlayer)
    //        event.addCapability(new ResourceLocation(Reference.MODID, "CapRC"), new ProviderRC());
    //}
	
	/*public static final ResourceLocation PLAYER_CAP = new ResourceLocation(Reference.MODID, "mana");
	 
	 @SubscribeEvent
	 public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	 {
	 if (!(event.getObject() instanceof EntityPlayer)) return;

	 event.addCapability(PLAYER_CAP, new CapaProvider());
	 }*/
}
