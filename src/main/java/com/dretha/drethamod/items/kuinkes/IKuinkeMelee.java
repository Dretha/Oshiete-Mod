package com.dretha.drethamod.items.kuinkes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IKuinkeMelee {
    int getBlockValue(ItemStack stack);
    int getDamageValue(ItemStack stack);
    float getSpeedAttack();
    void playImpact(ItemStack stack, EntityLivingBase base, World world);
}
