package com.dretha.drethamod.items;

import com.dretha.drethamod.init.InitItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTab {
	public static final CreativeTabs CTAB = new CreativeTabs("ctab") {
	    @Override
	    public ItemStack getTabIconItem() {
	        return new ItemStack(InitItems.HUMAN_MEAT);
	    }
	};
}
