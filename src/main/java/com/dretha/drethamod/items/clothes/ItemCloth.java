package com.dretha.drethamod.items.clothes;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

public class ItemCloth extends Item implements IHasModel, IDressable {

    private final ItemStack[] ARMORS;
    private final int slot;

    public ItemCloth(String name, int slot, @Nonnull ItemStack... stacks) {
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.CLOTHES);

        this.ARMORS = stacks;
        this.slot = slot;

        InitItems.ITEMS.add(this);
    }

    public @Nonnull ItemStack[] getArmors() {
        return ARMORS;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public ResourceLocation getTexture(boolean hood) {
        return null;
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
