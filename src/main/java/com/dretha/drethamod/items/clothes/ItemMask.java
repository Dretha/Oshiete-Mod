package com.dretha.drethamod.items.clothes;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.items.ModCreativeTabs;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemMask extends Item implements IHasModel{

	public ItemMask(String name) {
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.MASKS);
        InitItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
}
