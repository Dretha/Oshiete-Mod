package com.dretha.drethamod.items.kuinkes;

import com.dretha.drethamod.utils.enums.GhoulType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IKuinkeMelee {
    int getBlockValue(ItemStack stack);
    int getDamageValue(ItemStack stack);
    GhoulType getType(ItemStack stack);
    int getSpeedValue(ItemStack stack);
    void playImpact(ItemStack stack, EntityLivingBase base, World world);
}
