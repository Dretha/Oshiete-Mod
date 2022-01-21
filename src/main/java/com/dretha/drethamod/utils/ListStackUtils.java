package com.dretha.drethamod.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class ListStackUtils
{
    public static void writeToNBT(ArrayList<ItemStack> list, NBTTagCompound compound)
    {
        compound.setInteger("listSize", list.size());
        for (int i=0; i<list.size(); i++)
        {
            compound.setString(i + "listItemDomain", list.get(i).getItem().getRegistryName().getResourceDomain());
            compound.setString(i + "listItemPath", list.get(i).getItem().getRegistryName().getResourcePath());
            compound.setInteger(i + "itemStackCount", list.get(i).getCount());
        }
    }

    public static ArrayList<ItemStack> readFromNBT(NBTTagCompound compound)
    {
        int size = compound.getInteger("listSize");

        ArrayList<ItemStack> list = new ArrayList<>();
        for (int i=0; i<size; i++)
        {
            String domain = compound.getString(i + "listItemDomain");
            String path = compound.getString(i + "listItemPath");
            int count = compound.getInteger(i + "itemStackCount");
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(domain, path));
            list.add(new ItemStack(item, count));
        }
        return list;
    }
}
