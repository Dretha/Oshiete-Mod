package com.dretha.drethamod.blocks.fusion_blast_furnace;

import net.minecraft.item.ItemStack;

public class FusionBlastRecipe {

    private final int cookTime;
    private final ItemStack ingredient1;
    private final ItemStack ingredient2;
    private final ItemStack result;

    public FusionBlastRecipe(int cookTime, ItemStack ingredient1, ItemStack ingredient2, ItemStack result) {
        this.cookTime = cookTime;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.result = result;

        FusionBlastRecipeHandler.RECIPES.add(this);
    }

    public int getCookTime() {
        return cookTime;
    }
    public ItemStack getIngredient1() {
        return ingredient1;
    }
    public ItemStack getIngredient2() {
        return ingredient2;
    }
    public ItemStack getResult() {
        return result;
    }
}
