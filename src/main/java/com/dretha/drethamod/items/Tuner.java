package com.dretha.drethamod.items;

import com.dretha.drethamod.init.InitItems;
import com.dretha.drethamod.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class Tuner extends Item implements IHasModel{

	ITune itune;
	
	public Tuner(String name, ITune itune) {
		setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.GENERAL);
        this.itune = itune;
        
        InitItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation((Item)this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		itune.tune(player);
		
		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumKeeper.LEGENDARY;
	}


}
