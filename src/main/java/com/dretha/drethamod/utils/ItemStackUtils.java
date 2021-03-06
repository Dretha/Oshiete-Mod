package com.dretha.drethamod.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;

public class ItemStackUtils
{
    public static void writeListToNBT(ArrayList<ItemStack> list, NBTTagCompound compound)
    {
        compound.setInteger("listSize", list.size());
        for (int i=0; i<list.size(); i++)
        {
            ItemStack stack = list.get(i);
            compound.setString(i + "listItemDomain", stack.getItem().getRegistryName().getResourceDomain());
            compound.setString(i + "listItemPath", stack.getItem().getRegistryName().getResourcePath());
            compound.setInteger(i + "itemStackCount", stack.getCount());
            if (stack.hasTagCompound())
                compound.setTag(i + "tagItemStack", stack.getTagCompound());
        }
    }

    public static ArrayList<ItemStack> readListFromNBT(NBTTagCompound compound)
    {
        int size = compound.getInteger("listSize");

        ArrayList<ItemStack> list = new ArrayList<>();
        for (int i=0; i<size; i++)
        {
            String domain = compound.getString(i + "listItemDomain");
            String path = compound.getString(i + "listItemPath");
            int count = compound.getInteger(i + "itemStackCount");
            ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(domain, path)), count);

            if (compound.hasKey(i + "tagItemStack"))
                stack.setTagCompound((NBTTagCompound) compound.getTag(i + "tagItemStack"));

            list.add(stack);
        }
        return list;
    }

    public static boolean containJustOneTypeItem(ArrayList<ItemStack> list, Item item) {
        for (ItemStack stack : list) {
            if (stack.getItem()!=item && !stack.isEmpty() && stack.getItem()!= Items.AIR)
                return false;
        }
        return true;
    }

    public static void writeToNBT(ItemStack stack, NBTTagCompound compound, String tagName)
    {
        NBTTagCompound stackCompound = new NBTTagCompound();
        stackCompound.setString("stackDomain", stack.getItem().getRegistryName().getResourceDomain());
        stackCompound.setString("stackPath", stack.getItem().getRegistryName().getResourcePath());
        stackCompound.setInteger("stackCount", stack.getCount());
        if (stack.hasTagCompound())
            stackCompound.setTag("tagStack", stack.getTagCompound());
        compound.setTag(tagName, stackCompound);
    }

    public static ItemStack readFromNBT(NBTTagCompound compound, String tagName)
    {
        NBTTagCompound stackCompound = compound.getCompoundTag(tagName);
        String domain = stackCompound.getString("stackDomain");
        String path = stackCompound.getString("stackPath");
        int count = stackCompound.getInteger("stackCount");
        ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(domain, path)), count);

        if (stackCompound.hasKey("tagStack"))
            stack.setTagCompound((NBTTagCompound) stackCompound.getTag("tagStack"));

        return stack;
    }
}
