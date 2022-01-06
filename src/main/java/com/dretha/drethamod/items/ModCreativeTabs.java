package com.dretha.drethamod.items;

import com.dretha.drethamod.init.InitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
	public static final CreativeTabs GENERAL = new CreativeTabs("general") {
	    @Override
	    public ItemStack getTabIconItem() {
	        return new ItemStack(InitItems.HUMAN_MEAT);
	    }
	};
	public static final CreativeTabs MASKS = new CreativeTabs("masks") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(InitItems.NORO_MASK);
		}
	};
	public static final CreativeTabs CLOTHES = new CreativeTabs("clothes") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(InitItems.BLACK_TUX);
		}
	};
	public static final CreativeTabs WEAPON = new CreativeTabs("weapon") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(InitItems.HK33);
		}
	};
	public static final CreativeTabs TOOLS = new CreativeTabs("tools") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(InitItems.HUMAN_BLOOD_BOTTLE);
		}
	};
}
