package com.dretha.drethamod.items.kuinkes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IColdSteel {
    void playImpact(ItemStack stack, EntityLivingBase base, World world);
}
