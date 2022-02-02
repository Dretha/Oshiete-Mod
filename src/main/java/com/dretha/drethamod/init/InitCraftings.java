package com.dretha.drethamod.init;

import com.dretha.drethamod.reference.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitCraftings {
    public static void init() {
        GameRegistry.addSmelting(Items.BRICK, new ItemStack(InitItems.CHAMOTTE, 2), 0.1F);
        registerRecipes("crafting_fireclay");
        GameRegistry.addSmelting(InitItems.FIRE_CLAY, new ItemStack(InitItems.FIRECLAY_BAR), 0.1F);
        registerRecipes("crafting_fireclay_brick");
        registerRecipes("crafting_fusion_blast_furnace");
        registerRecipes("crafting_kuinke_steel_block");
        registerRecipes("crafting_kuinke_steel_from_block");
        registerRecipes("crafting_steel_block");
        registerRecipes("crafting_steel_from_block");
        registerRecipes("crafting_gunpowder");
        registerRecipes("crafting_iron_bullet");
        registerRecipes("crafting_steel_bullet");
        registerRecipes("crafting_steel_hammer");
        registerRecipes("crafting_iron_plate_from_steel_hammer");
        registerRecipes("crafting_shock_resistant_handle");
        registerRecipes("crafting_cleaver");
        registerRecipes("crafting_katana");
        registerRecipes("crafting_q_steel_bar");
        registerRecipes("crafting_q_steel_from_bar");
        registerRecipes("crafting_cudgel");
        registerRecipes("crafting_scythe");
        registerRecipes("crafting_knife");
        registerRecipes("crafting_cleaver1");
        registerRecipes("crafting_balsam");
        registerRecipes("crafting_kuinke_shard_from_steel");
        registerRecipes("crafting_kuinke_steel_ingot");
    }

    private static void registerRecipes(String name) {
        CraftingHelper.register(new ResourceLocation(Reference.MODID, name), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
    }
}
