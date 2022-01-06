package com.dretha.drethamod.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class EnumKeeper {
    //rarity
    public static final EnumRarity LEGENDARY = EnumHelper.addRarity("legendary", TextFormatting.GOLD, "Legendary");
    public static final EnumRarity Q_STEEL_RARITY = EnumHelper.addRarity("q_steel", TextFormatting.BLUE, "Q-Steel");
    public static final EnumRarity FIREARM = EnumHelper.addRarity("firearm", TextFormatting.GRAY, "Firearm");
    public static final EnumRarity LOW_SATIATION = EnumHelper.addRarity("low_satiation", TextFormatting.DARK_RED, "Low Satiation");
    public static final EnumRarity HIGH_SATIATION = EnumHelper.addRarity("high_satiation", TextFormatting.RED, "High Satiation");

    //tool material
    public static final Item.ToolMaterial STEEL = EnumHelper.addToolMaterial("dm:steel", 2, 640, 7.0F, 3.0F, 14);
    public static final Item.ToolMaterial Q_STEEL = EnumHelper.addToolMaterial("dm:q_steel", 2, 1664, 9.0F, 4.0F, 14);
}
