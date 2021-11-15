package com.dretha.drethamod.init;

import java.util.ArrayList;
import java.util.List;

import com.dretha.drethamod.items.ItemGhoulFood;
import com.dretha.drethamod.items.RenderingItem;
import com.dretha.drethamod.items.ItemTabletCreative;
import com.dretha.drethamod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitItems {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//items
	public static final Item KAKUGAN2 = new RenderingItem("kakugan2");
	
	//public static final Item TABLET_HUMAN = new ItemTabletCreative("tablet_human", "Will make you human again.", 0);
	public static final Item TABLET_UKAKU = new ItemTabletCreative("tablet_ukaku", "", 1);
	public static final Item TABLET_KOUKAKU = new ItemTabletCreative("tablet_koukaku", "", 2);
	public static final Item TABLET_RINKAKU = new ItemTabletCreative("tablet_rinkaku", "", 3);
	public static final Item TABLET_BIKAKU = new ItemTabletCreative("tablet_bikaku", "", 4);
	
	public static final Item HUMAN_MEAT = new ItemGhoulFood("human_meat", "\"human, human, human, child, human...\"", 8, 10);
	public static final Item HUMAN_EYE = new ItemGhoulFood("human_eye", "Uta approves.", 2, 12);
	public static final Item HUMAN_BLOOD_BOTTLE = new ItemGhoulFood("human_blood_bottle", "This is not wine.", 3, 8);
	
	public static final Item KAGUNE_UKAKU = new RenderingItem("kagune_ukaku");
}
