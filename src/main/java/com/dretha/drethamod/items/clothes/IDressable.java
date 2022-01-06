package com.dretha.drethamod.items.clothes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public interface IDressable {
    @Nonnull ItemStack[] getArmors();
    int getSlot();
    ResourceLocation getTexture(boolean hood);
}
