package com.dretha.drethamod.items;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class RenderingItem extends Item implements IHasModel{

	public RenderingItem(String name) {
		setRegistryName(name);
        setUnlocalizedName(name);
        
        InitItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	
	
}
