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
        registerRecipe("crafting_fireclay");
        GameRegistry.addSmelting(InitItems.FIRE_CLAY, new ItemStack(InitItems.FIRECLAY_BAR), 0.1F);
        registerRecipe("crafting_fireclay_brick");
        registerRecipe("crafting_fusion_blast_furnace");
        registerRecipe("crafting_kuinke_steel_block");
        registerRecipe("crafting_kuinke_steel_from_block");
        registerRecipe("crafting_steel_block");
        registerRecipe("crafting_steel_from_block");
        registerRecipe("crafting_gunpowder");
        registerRecipe("crafting_iron_bullet");
        registerRecipe("crafting_steel_bullet");
        registerRecipe("crafting_steel_hammer");
        registerRecipe("crafting_iron_plate_from_steel_hammer");
        registerRecipe("crafting_shock_resistant_handle");
        registerRecipe("crafting_cleaver");
        registerRecipe("crafting_katana");
        registerRecipe("crafting_q_steel_bar");
        registerRecipe("crafting_q_steel_from_bar");
        registerRecipe("crafting_cudgel");
        registerRecipe("crafting_scythe");
        registerRecipe("crafting_knife");
        registerRecipe("crafting_cleaver1");
        registerRecipe("crafting_balsam");
        registerRecipe("crafting_kuinke_shard_from_steel");
        registerRecipe("crafting_kuinke_steel_ingot");
        registerRecipe("crafting_syringe");
        registerRecipe("crafting_syringe_from_blood");
        registerRecipe("crafting_blood_analyzer");
    }
// TODO сделать рецепт автомата и проверить остальные
    private static void registerRecipe(String name) {
        CraftingHelper.register(new ResourceLocation(Reference.MODID, name), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
    }
}
