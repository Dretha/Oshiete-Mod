package com.dretha.drethamod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class RenderingItem extends Item implements IHasModel{

	public RenderingItem(String name) {
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTab.CTAB);
        
        InitItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	
	
}
