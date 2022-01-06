package com.dretha.drethamod.blocks.fusion_blast_furnace;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class FusionBlastRecipeHandler {

    public static ArrayList<FusionBlastRecipe> RECIPES = new ArrayList<>();

    public static FusionBlastRecipe KUINKE_STEEL = new FusionBlastRecipe(1600, new ItemStack(InitItems.STEEL), new ItemStack(InitItems.KAGUNE_SHARD, 2), new ItemStack(InitItems.KUINKE_STEEL));
    public static FusionBlastRecipe STEEL = new FusionBlastRecipe(800, new ItemStack(Items.IRON_INGOT), new ItemStack(Item.getItemFromBlock(Blocks.COAL_BLOCK)), new ItemStack(InitItems.STEEL));
    public static FusionBlastRecipe STEEL_PLATE = new FusionBlastRecipe(800, new ItemStack(InitItems.IRON_PLATE), new ItemStack(Item.getItemFromBlock(Blocks.COAL_BLOCK)), new ItemStack(InitItems.STEEL_PLATE));
    public static FusionBlastRecipe Q_BULLETS = new FusionBlastRecipe(800, new ItemStack(InitItems.STEEL_BULLET, 16), new ItemStack(InitItems.KAGUNE_SHARD, 2), new ItemStack(InitItems.Q_BULLET, 16));

    public static FusionBlastRecipe findRecipe(FusionBlastInventory inventory) {
        Item slot0 = inventory.getStackInSlot(0).getItem();
        Item slot1 = inventory.getStackInSlot(1).getItem();
        int count0 = inventory.getStackInSlot(0).getCount();
        int count1 = inventory.getStackInSlot(1).getCount();
        for (FusionBlastRecipe recipe : RECIPES) {
            if ( (slot0==recipe.getIngredient1().getItem() && slot1==recipe.getIngredient2().getItem() && count0>=recipe.getIngredient1().getCount() && count1>=recipe.getIngredient2().getCount()) || (slot1==recipe.getIngredient1().getItem() && slot0==recipe.getIngredient2().getItem() && count0>=recipe.getIngredient2().getCount() && count1>=recipe.getIngredient1().getCount()) && !inventory.getStackInSlot(2).isEmpty() )
                return recipe;
        }
        return null;
    }
}
