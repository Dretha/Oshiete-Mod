package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.init.InitCustomRenderers;
import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		InitCustomRenderers.init();
	}
}
