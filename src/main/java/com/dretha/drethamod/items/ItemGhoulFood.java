package com.dretha.drethamod.items;

import java.util.List;

import javax.annotation.Nullable;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IGhoulFood;
import com.dretha.drethamod.utils.interfaces.IHasModel;

import akka.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemGhoulFood extends ItemFood implements IHasModel, IGhoulFood{
	
	private String description;

	public ItemGhoulFood(String name, String description, int amount, float saturation) {
		super(amount, saturation, true);
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTab.CTAB);
        this.description = description;
        
        InitItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> desc, ITooltipFlag flagIn) {
	    desc.add(description);
	}

	
}
