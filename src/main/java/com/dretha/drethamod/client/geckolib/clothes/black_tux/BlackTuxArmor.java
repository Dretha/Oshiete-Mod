package com.dretha.drethamod.client.geckolib.clothes.black_tux;

import com.dretha.drethamod.client.geckolib.clothes.ClothArmorBase;
import com.dretha.drethamod.items.ModCreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class BlackTuxArmor extends ClothArmorBase {
    public BlackTuxArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot slot, String name) {
        super(materialIn, renderIndexIn, slot, name);
        setCreativeTab(ModCreativeTabs.CLOTHES);
    }
}
