package com.dretha.drethamod.utils.handlers;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
	
	public static final ResourceLocation HUMAN = LootTableList.register(new ResourceLocation(Reference.MODID, "human"));

}
